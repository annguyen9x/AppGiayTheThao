package com.annguyen.giaythethao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.annguyen.giaythethao.R;
import com.annguyen.giaythethao.adapter.GiayNikeAdapter;
import com.annguyen.giaythethao.model.SanPham;
import com.annguyen.giaythethao.ultil.CheckConnection;
import com.annguyen.giaythethao.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GiayNikeActivity extends AppCompatActivity {
    private Toolbar toolbarGiayNike;
    private ListView listViewGiayNike;
    private ArrayList<SanPham> arrayListSP;
    private GiayNikeAdapter giayNikeAdapter;
    private int idLoaiSP=0;
    private int page = 1;

    View progressbarFooterView;
    boolean isLoading = false;//Đánh dấu đang load data, ko load tiếp nữa
    boolean limitData = false;///Đánh dấu đang còn dữ liệu trong bảng
    private MyHandler myHandler;//Cài đặt luồng (Thread) load data song song luồng chính

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giay_nike);

        widget();

        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            getIDLoaiSP();//Lấy IDLoaiSP do Intent truyền sang
            setMyActionBar();
            setDataSanPhamOfArrayListSP(page);

            //hiển thị nút Load khi tải dl, và sự kiện chuyển màn hình trang chi tiết sản phẩm
            loadingDataIconInListView();
        }
    }

    //Hàm gắn Menu icon Giỏ hàng
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giohang, menu);
        //return super.onCreateOptionsMenu(menu);//Đều đúng
        return true;//Đều đúng
    }

    //Hàm bắt sự kiện khi click Icon Menu GioHang
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuGioHang:
                startActivity(new Intent(getApplicationContext(), com.annguyen.giaythethao.activity.GioHang.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void widget() {
        toolbarGiayNike = findViewById(R.id.toolbarGiayNike);
        listViewGiayNike = findViewById(R.id.listViewGiayNike);

        //setAdapter cho ListView
        arrayListSP = new ArrayList<>();
        giayNikeAdapter = new GiayNikeAdapter(arrayListSP, getApplicationContext());
        listViewGiayNike.setAdapter(giayNikeAdapter);

        //Phương thức để gán vào 1 layout (ProgressBar)
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        progressbarFooterView = layoutInflater.inflate(R.layout.progressbar, null);

        //Thread load data song song luồng chính (Main)
        myHandler = new MyHandler();
    }

    private void getIDLoaiSP() {
        idLoaiSP = getIntent().getIntExtra("idLoaiSP", -1);
    }

    private void setMyActionBar() {
        setSupportActionBar(toolbarGiayNike);
        getSupportActionBar().setHomeButtonEnabled(true);//Bật nút Home cho ActionBar
        toolbarGiayNike.setNavigationIcon(android.R.drawable.ic_media_previous);//set Icon cho ActionBar
        //Cài đặt sự kiện click
        toolbarGiayNike.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//Đóng Activity hiện tại
            }
        });
    }

    private void setDataSanPhamOfArrayListSP(int page) {
        String url = Server.urlSanPham + page;

        //Đọc về hết tất cả dữ liệu URL
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //response != null (nếu load data không lỗi), nó có thể = mảng "[]"(nếu không có data, nó trả về 2 dấu []);
                        if ( response != null && response.length() != 2 ) {
                            //Khi có dữ liệu thì xóa nút Loading data (progressbar)
                            listViewGiayNike.removeFooterView(progressbarFooterView);

                            int id = 0;
                            String ten = "";
                            Double gia = 0.0D;
                            int sl = 0;
                            String hinhAnh = "";
                            String moTa = "";
                            String gTinh = "";
                            String mauSac = "";
                            int size = 0;
                            int idLoaiSP = 0;

                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                                        id = jsonObject.getInt("id");
                                        ten = jsonObject.getString("tenSP");
                                        gia = jsonObject.getDouble("gia");
                                        sl = jsonObject.getInt("sl");
                                        hinhAnh = jsonObject.getString("hinhAnh");
                                        moTa = jsonObject.getString("moTa");
                                        gTinh = jsonObject.getString("gioiTinh");
                                        mauSac = jsonObject.getString("mauSac");
                                        size = jsonObject.getInt("size");
                                        idLoaiSP = jsonObject.getInt("idLoaiSP");

                                        arrayListSP.add(new SanPham(id, ten, gia, sl, hinhAnh, moTa, gTinh, mauSac, size, idLoaiSP));//Thêm dl vào mảng sp
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //Update lại Adapter sau khi thêm một Object vào arrSanPham
                            giayNikeAdapter.notifyDataSetChanged();
                        }else{
                            limitData = true;//Xác nhận hết data để load sang trang khác
                            //Khi có dữ liệu thì xóa nút Loading data (progressbar)
                            listViewGiayNike.removeFooterView(progressbarFooterView);
                            CheckConnection.showToast(getApplicationContext(),"Đã hết dữ liệu");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(getClass().getSimpleName(), "Lỗi: "+ error.toString());
                        CheckConnection.showToast(getApplicationContext(), "Lỗi khi get data table SanPham !!!");
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idLoaiSP",String.valueOf(idLoaiSP));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);//Gửi các phương thức lên Server
        requestQueue.add(stringRequest);//Thực hiện yêu cầu lấy dữ liệu từ Json
    }

    private void loadingDataIconInListView() {
        //Sự kiện khi click vào Item chuyển vào màn hình ChiTietSanPham
        listViewGiayNike.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPham.class);
                intent.putExtra("sanPham", arrayListSP.get(position));
                startActivity(intent);
            }
        });

        //Sự kiện kéo ListView thì hiển thị biểu tượng Load dữ liệu
        listViewGiayNike.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            //firstVisibleItem: vị trí Item đầu tiên, visibleItemCount: các Item có thể nhìn thấy, totalItemCount:tổng só Item trong listView
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //Vị trí đầu tiên + các Item có thể nhìn thấy == tổng số Item, và không phải lần load đầu tiên, và ko phải đang load dữ liệu
                // ( lần đầu tổng Item==0) => đang đứng ở vị trí cuối cùng và chưa có dl tiếp theo, nên sẽ cho load dữ liệu

                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading== false && limitData == false ){
                    //Biến đánh dấu vị trí cuối cùng của ListView, nếu vị trí cuối cùng đang load thì không cho kéo nữa,
                    // load xong dữ liệu mới cho kéo tiếp ( Nếu ko, dl cập nhật ko kịp cho View sẽ bị lỗi app)
                    isLoading = true;
                    MyThreadData myThreadData = new MyThreadData();
                    myThreadData.start();//bắt đầu thực thi tuyến MyThreadData để load dữ liệu

                }
            }
        });
    }

    //Cài đặt luồng load dữ liệu chạy song song với luồng (Thread) Chính(main)-đổ dữ liệu lên, để lấy sẵn dữ liệu về
    // => giúp tăng hiệu suất, ko phải đợi load dl lâu
    public class MyHandler extends Handler{

        //p. thức quản lí các Thread gửi lên
        @Override
        public void handleMessage(@NonNull Message msg) {
            //kiểm tra giá trị gửi lên trong Message là "what"
            switch (msg.what){
                case 0:
                    listViewGiayNike.addFooterView(progressbarFooterView);//thêm biểu tượng nút Load, nếu lần đầu chưa có dl
                    break;
                case 1:
                    //cập nhật và đổ dl lên listView
                    page++;
                    setDataSanPhamOfArrayListSP(page);//Nếu đã có dl và load sang trang khác
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    //Cài đặt các Luồng
    public class MyThreadData extends Thread{
        //Method thực thi các luồng
        @Override
        public void run() {
            //Gửi message cho MyHandler để hiển thị nút load
            myHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(2500);//cho ngủ 3 giây để đợi load data
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //obtainMessage: là method "tiếp tục" liên kết các Thread với Handler, lúc trước liên kết gửi 0
            Message message = myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
            super.run();
        }
    }
}
