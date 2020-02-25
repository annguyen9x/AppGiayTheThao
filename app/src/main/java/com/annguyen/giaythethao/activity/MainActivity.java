package com.annguyen.giaythethao.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.annguyen.giaythethao.R;
import com.annguyen.giaythethao.adapter.LoaiSPAdapter;
import com.annguyen.giaythethao.adapter.SanPhamAdapter;
import com.annguyen.giaythethao.model.GioHang;
import com.annguyen.giaythethao.model.KhachHangModel;
import com.annguyen.giaythethao.model.LoaiSP;
import com.annguyen.giaythethao.model.SanPham;
import com.annguyen.giaythethao.ultil.CheckConnection;
import com.annguyen.giaythethao.ultil.Server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private TextView textViewTitle;
    private RecyclerView recyclerViewGroupSP;
    private NavigationView navigationView;
    private ListView listViewOfNavigationView;
    private DrawerLayout drawerLayoutAnShoe;
    private LinearLayout linearLayoutLogo;


    private ArrayList<LoaiSP> arrLoaiSP;
    private LoaiSPAdapter loaiSPAdapter;
    private ArrayList<SanPham> arrSanPham;
    private SanPhamAdapter sanPhamAdapter;

    public static ArrayList<GioHang> arrayListGioHang;
    public static HashMap<String, KhachHangModel> mapKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        widget();//Ánh xạ view

        if( CheckConnection.haveNetworkConnection(getApplicationContext()) ) { //Kiểm tra kết nối mạng
            setActionBar();//Thanh ActionBar
            setViewFlipper();//Slide Show
            getDataLoaiSP();//Đọc dữ liệu Json từ Url mà PHP truy vấn, để gán cho arrLoaiSP trong NavigationView
            getDataSanPham();//Đọc dữ liệu từ JSon từ URL mà PHP truy vấn, để gán cho arrSP trong RecycleView
            catchOnItemListView();//Bắt sự kiện khi click vào các Item ở NavigationView
        }else {
            CheckConnection.showToast(MainActivity.this, "Kiểm tra kết nối mạng !");
            finish();//Thoát màn hình
        }
    }

    //Hàm gắn Menu icon Giỏ hàng
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giohang, menu);
        getMenuInflater().inflate(R.menu.menu_account, menu);
        //return super.onCreateOptionsMenu(menu);//Đều đúng
        return true;//Đều đúng
    }

    //Hàm bắt sự kiện khi click Icon Menu GioHang, KhachHang
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuGioHang:
                startActivity(new Intent(getApplicationContext(), com.annguyen.giaythethao.activity.GioHang.class));
                break;
            case R.id.menuAccount:
                if( mapKhachHang != null && mapKhachHang.size() > 0){//Đã đăng nhập
                    startActivity(new Intent(getApplicationContext(), com.annguyen.giaythethao.activity.TaiKhoanKhachHang.class));
                }
                else if( mapKhachHang != null && mapKhachHang.size() == 0 ) {//Chưa đăng nhập
                    startActivity(new Intent(getApplicationContext(), com.annguyen.giaythethao.activity.DangNhap.class));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void widget(){
        drawerLayoutAnShoe = findViewById(R.id.drawerLayoutAnShoe);
        toolbar = findViewById(R.id.toolbarTrangChu);
        viewFlipper = findViewById(R.id.viewFlipper);
        textViewTitle = findViewById(R.id.textViewTitle);
        recyclerViewGroupSP = findViewById(R.id.recyclerViewGroupSP);
        navigationView = findViewById(R.id.navigationView);
        listViewOfNavigationView = findViewById(R.id.listViewOfNaView);
        linearLayoutLogo = findViewById(R.id.linearLayoutLogo);

        //Tắt menu của DrawerLayout khi click Icon Logo
        linearLayoutLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutAnShoe.closeDrawer(GravityCompat.START);
            }
        });

        setListViewOfNavigationView();//(set Adapter)- Gán dữ liệu cho ListView of NavigationView

        setRecyclerViewGroupSP();//set Adapter, set Kiểu hiển thị cho recyclerViewGroupSP

        //Giỏ hàng
        if( arrayListGioHang != null ){

        }else{
            arrayListGioHang = new ArrayList<GioHang>();
        }

        //Tai khoan
        if( mapKhachHang != null ){

        }else{
            mapKhachHang = new HashMap<>();
        }
    }

    private void setListViewOfNavigationView() {
        arrLoaiSP = new ArrayList<>();
        //Gán thêm một số nội dung khác đối tượng "LoaiSP" cho phần Menu của NavigationView
        arrLoaiSP.add(0,new LoaiSP(0,"Trang chủ",
                "https://icon-library.net/images/homepage-icon-png/homepage-icon-png-29.jpg"));
        loaiSPAdapter = new LoaiSPAdapter(arrLoaiSP,getApplicationContext());

        //Set Adapter cho ListView phần bị ẩn Left
        listViewOfNavigationView.setAdapter(loaiSPAdapter);
    }


    private void setRecyclerViewGroupSP() {
        arrSanPham  = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(getApplicationContext(), arrSanPham);

        recyclerViewGroupSP.setHasFixedSize(true);//Cho phép chỉnh sửa recyclerViewGroupSP
        //recyclerViewGroupSP Hiển thị dạng GridLayoutManager có 2 cột
        recyclerViewGroupSP.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewGroupSP.setAdapter(sanPhamAdapter);//Set Adapter cho recyclerViewGroupSP
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutAnShoe.openDrawer(GravityCompat.START);
            }
        });
    }

    private void setViewFlipper() {
        //Set ViewFlipper tự chạy 2s mỗi lần
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
    }

    private void getDataLoaiSP() {
        //Đọc nội dung URL
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Server.urlLoaiSP,
                //Lắng nghe sự kiện trả về mảng JSONArray
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null ){
                            int idLoaiSP = 0;
                            String tenLoaiSP= "";
                            String   hinhAnhLoaiSP="";

                            //Lấy kết quả mảng Json trả về gán cho các thuộc tính của arrLoaiSP để tạo Addapter
                            for (int i = 0; i < response.length(); i++){
                                try {

                                    JSONObject jsonObject = (JSONObject) response.get(i);//Lấy ra 1 Object vì response đang trả về JSONArray

                                    idLoaiSP = jsonObject.getInt("id");
                                    tenLoaiSP = jsonObject.getString("tenLoaiSP");
                                    hinhAnhLoaiSP = jsonObject.getString("hinhAnhLoaiSP");
                                    arrLoaiSP.add(new LoaiSP(idLoaiSP,tenLoaiSP,hinhAnhLoaiSP));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            //Update lại Adapter sau khi thêm một Object vào arrLoaiSP
                            loaiSPAdapter.notifyDataSetChanged();
                            //Gán thêm phần Liên Hệ, Thông Tin cho phần menu của NavigationView
                            arrLoaiSP.add(4,new LoaiSP(0,"Liên hệ",
                                    "https://cdn2.iconfinder.com/data/icons/unigrid-phantom-devices-vol-2/60/017_090_check_approve_contact_call_phone_mobile-512.png"));
                            arrLoaiSP.add(5, new LoaiSP(0,"Thông tin",
                                    "https://cdn0.iconfinder.com/data/icons/small-n-flat/24/678110-sign-info-512.png"));
                        }
//                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                //Nếu sự kiện trả về là lỗi thì đưa ra thông báo
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());//Thực hiện các p/thuc gửi lên Server
        requestQueue.add(jsonArrayRequest);//Cho nó thực hiện yêu cầu lấy dữ liệu JSON về
    }


    private void getDataSanPham() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.urlSanPhamMoiNhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null ){
                    int id = 0;
                    String ten="";
                    Double gia = 0.0D;
                    int sl = 0;
                    String hinhAnh ="";
                    String moTa= "";
                    String gTinh = "";
                    String mauSac = "";
                    int size = 0;
                    int idLoaiSP = 0;

                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = (JSONObject) response.get(i); ;//Lấy ra 1 Object vì response đang trả về JSONArray

                            id = jsonObject.getInt("id");
                            ten = jsonObject.getString("tenSP");
                            gia = jsonObject.getDouble("gia");
                            sl = jsonObject.getInt("sl");
                            hinhAnh = jsonObject.getString("hinhAnh");
                            moTa= jsonObject.getString("moTa");
                            gTinh = jsonObject.getString("gioiTinh");
                            mauSac = jsonObject.getString("mauSac");
                            size = jsonObject.getInt("size");
                            idLoaiSP = jsonObject.getInt("idLoaiSP");

                            arrSanPham.add(new SanPham(id,ten, gia, sl, hinhAnh, moTa, gTinh,mauSac, size,idLoaiSP));//Thêm dl vào mảng sp

                            //Update lại Adapter sau khi thêm một Object vào arrSanPham
                            sanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CheckConnection.showToast(getApplicationContext(), "Lỗi khi get data table SanPham !!!");
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());//Thực hiện các p/thuc gửi lên Server
        requestQueue.add(jsonArrayRequest);//Cho nó thực hiện yêu cầu lấy dữ liệu JSON về
    }

    private void catchOnItemListView() {
        listViewOfNavigationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //Kiểm tra có Internet mới load
                        if(CheckConnection.haveNetworkConnection(MainActivity.this)){
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.showToast(getApplicationContext(), "Vui lòng kiểm tra kết nối internet !");
                            drawerLayoutAnShoe.closeDrawer(GravityCompat.START);//Đóng drawerLayout, đóng thanh NavigationView sau khi click, không đóng Activity hiện tại
                        }
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, GiayNikeActivity.class);
                            intent.putExtra("idLoaiSP", arrLoaiSP.get(position).getIDLoai());
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast(getApplicationContext(), "Vui lòng kiểm tra kết nối internet !");
                            drawerLayoutAnShoe.closeDrawer(GravityCompat.START);
                        }
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, GiayVansActivity.class);
                            intent.putExtra("idLoaiSP",arrLoaiSP.get(position).getIDLoai());
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast(getApplicationContext(), "Vui lòng kiểm tra kết nối internet !");
                            drawerLayoutAnShoe.closeDrawer(GravityCompat.START);
                        }
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, GiayAdidassActivity.class);
                            intent.putExtra("idLoaiSP", arrLoaiSP.get(position).getIDLoai());
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast(getApplicationContext(), "Vui lòng kiểm tra kết nối internet !");
                            drawerLayoutAnShoe.closeDrawer(GravityCompat.START);
                        }
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast(getApplicationContext(), "Vui lòng kiểm tra kết nối internet !");
                            drawerLayoutAnShoe.closeDrawer(GravityCompat.START);
                        }
                        break;
                    case 5:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast(getApplicationContext(), "Vui lòng kiểm tra kết nối internet !");
                            drawerLayoutAnShoe.closeDrawer(GravityCompat.START);
                        }
                        break;
                }
            }
        });
    }

}
