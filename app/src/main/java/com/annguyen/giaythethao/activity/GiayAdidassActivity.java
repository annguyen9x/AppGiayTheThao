package com.annguyen.giaythethao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.annguyen.giaythethao.adapter.GiayAdidasAdapter;
import com.annguyen.giaythethao.model.SanPham;
import com.annguyen.giaythethao.ultil.CheckConnection;
import com.annguyen.giaythethao.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GiayAdidassActivity extends AppCompatActivity {
    private Toolbar toolbarGiayAdidas;
    private ListView listViewGiayAdidas;
    private ArrayList<SanPham> arrayListSP;
    private GiayAdidasAdapter giayAdidasAdapter;
    private int idLoaiSP;
    private int page = 1;

    private View progressbarFooterView;//View biểu tượng Loading data
    private boolean isLoading = false;//Đánh dấu đang load data, ko load tiếp nữa
    private boolean limitData = false;//Đánh dấu đang còn dữ liệu trong bảng
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giay_adidass);

        widget();

        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            getIDLoaiSP();
            setMyActionBar();
            setDataSanPhamOfArrayListSP(page);

            //Hiển thị nút Loading data khi đang tải dữ liệu, và cài đặt sự kiện chuyển mà hình sang view chitietSP
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
        toolbarGiayAdidas = findViewById(R.id.toolbarGiayAdidas);
        listViewGiayAdidas= findViewById(R.id.listViewGiayAdidas);

        //setAdapter cho ListView
        arrayListSP = new ArrayList<>();
        giayAdidasAdapter = new GiayAdidasAdapter(getApplicationContext(), arrayListSP);
        listViewGiayAdidas.setAdapter(giayAdidasAdapter);

        //Phương thức để gán vào 1 layout (ProgressBar)
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        progressbarFooterView = layoutInflater.inflate(R.layout.progressbar,null);

        //Thread load data song song luồng chính (Main)
        myHandler = new MyHandler();
    }

    private void getIDLoaiSP() {
        Intent intent = getIntent();
        idLoaiSP = intent.getIntExtra("idLoaiSP", -1);
        intent.putExtra("idLoaiSP",idLoaiSP);
    }

    private void setMyActionBar() {
        setSupportActionBar(toolbarGiayAdidas);
        getSupportActionBar().setHomeButtonEnabled(true);//Bật nút Home cho ActionBar
        toolbarGiayAdidas.setNavigationIcon(android.R.drawable.ic_media_previous);//Set Icon cho ActionBar

        //Sự kiện click vào ActionBar thì về trang trước, tắt trang hiện tại
        toolbarGiayAdidas.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setDataSanPhamOfArrayListSP(final int page) {
        String url = Server.urlSanPham + page;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //response != null (nếu load data không lỗi), nó có thể = mảng "[]"(nếu không có data, nó trả về 2 dấu []);
                        if(response != null && response.length() != 2 ){
                            //Khi có dữ liệu thì xóa nút Loading data (progressbar)
                            listViewGiayAdidas.removeFooterView(progressbarFooterView);

                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                    for(int i = 0; i < jsonArray.length(); i++){
                                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                                        arrayListSP.add(new SanPham(
                                                jsonObject.getInt("id"),
                                                jsonObject.getString("tenSP"),
                                                jsonObject.getDouble("gia"),
                                                jsonObject.getInt("sl"),
                                                jsonObject.getString("hinhAnh"),
                                                jsonObject.getString("moTa"),
                                                jsonObject.getString("gioiTinh"),
                                                jsonObject.getString("mauSac"),
                                                jsonObject.getInt("size"),
                                                jsonObject.getInt("idLoaiSP")
                                        )) ;
                                    }
                                } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            giayAdidasAdapter.notifyDataSetChanged();//Update lại Adapter sau khi thêm một Object vào arrSanPham
                        }
                        else{
                            limitData = true;//Xác nhận hết data để load sang trang khác
                            //Khi có dữ liệu thì xóa nút Loading data (progressbar)
                            listViewGiayAdidas.removeFooterView(progressbarFooterView);
                            CheckConnection.showToast(getApplicationContext(),"Đã hết dữ liệu");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CheckConnection.showToast(getApplicationContext(), "Lỗi khi get data table SanPham !!!");
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("idLoaiSP", String.valueOf(idLoaiSP));
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void loadingDataIconInListView() {
        //Sự kiện chuyển sang View ChiTietSP khi click vào Item trong ListView
        listViewGiayAdidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GiayAdidassActivity.this, ChiTietSanPham.class);
                intent.putExtra("sanPham", arrayListSP.get(position));
                startActivity(intent);
            }
        });

        //Sự kiện khi kéo listView thì hiển thị view Loading data
        listViewGiayAdidas.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //Nếu vị trí đầu tiên + các Item có thể nhìn thấy == tổng số Item, lần đầu tổng Item == 0,..
                if( firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading == false && limitData == false){
                    isLoading = true;
                    MyThreadData myThreadData = new MyThreadData();
                    myThreadData.start();//Bắt đầu thực thi tuyến MyThreadData để load dữ liệu
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
            switch (msg.what){
                case 0:
                    listViewGiayAdidas.addFooterView(progressbarFooterView);//Thêm View Loading data khi đang load
                    break;
                case 1:
                    page++;
                    setDataSanPhamOfArrayListSP(page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    //Cài đặt các Luồng
    public class MyThreadData extends Thread{
        @Override
        public void run() {
            myHandler.sendEmptyMessage(0);//Gửi message cho MyHandler để hiển thị nút load
            try {
                Thread.sleep(2500);//Nghỉ 2,5 giây để load
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
