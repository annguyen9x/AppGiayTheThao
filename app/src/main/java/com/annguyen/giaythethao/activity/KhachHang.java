package com.annguyen.giaythethao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.annguyen.giaythethao.R;
import com.annguyen.giaythethao.ultil.CheckConnection;
import com.annguyen.giaythethao.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KhachHang extends AppCompatActivity {
    private EditText editTextTenKH, editTextSDT, editTextEmail, editTextDiaChi;
    private RadioButton radioButtonNam, radioButtonNu;
    private Button buttonXacNhan, buttonQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);

        widget();
    }

    private void widget() {
        editTextTenKH = findViewById(R.id.editTextHoTen);
        editTextSDT = findViewById(R.id.editTextDienThoai);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextDiaChi = findViewById(R.id.editTextDiaChi);
        radioButtonNam = findViewById(R.id.radioButtonNam);
        radioButtonNu = findViewById(R.id.radioButtonNu);
        buttonQuayLai = findViewById(R.id.buttonQuayLai);
        buttonXacNhan = findViewById(R.id.buttonXacNhanTTKH);

        //Sự kiện khi click button "Quay lại"
        buttonQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//tắt màn hình hiện tại
            }
        });

        //Sự kiện khi click vào Button "Xác nhận"
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            setDataDatHang();//cài đặt chức năng đặt hàng
        }else {
            CheckConnection.showToast(getApplicationContext(), "Vui lòng kiểm tra kết nối Internet !");
        }
    }

    private void setDataDatHang(){
        //Sự kiện khi click button "Xác nhận"
        buttonXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Double tongTien = getIntent().getDoubleExtra("TongTien", 0);
                final String tenkh = editTextTenKH.getText().toString().trim();
                final String sdt = editTextSDT.getText().toString().trim();
                final String email = editTextEmail.getText().toString().trim();
                final String diachi = editTextDiaChi.getText().toString().trim();

                String gioiTinh = "";
                if(radioButtonNam.isChecked()){
                    gioiTinh = "Nam";
                }else if(radioButtonNu.isChecked()){
                    gioiTinh = "Nữ";
                }
                final String finalGioiTinh = gioiTinh;

                if(tongTien > 0 && tenkh.length() > 0 && sdt.length() > 0 && email.length() > 0 && diachi.length() > 0 ){
                    //Đưa dữ liệu lên Server
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlGioHang,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(final String responseMaDH) {
                                    if(responseMaDH != null && Integer.parseInt(responseMaDH) > 0){
                                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.urlChiTietDonHang,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        if(response.equals("successInsertCTDH")){
                                                            MainActivity.arrayListGioHang.clear();//Xóa dữ liệu trong giỏ hàng đã đặt mua thàng công
                                                            Toast.makeText(KhachHang.this, "Đặt hàng thành công !", Toast.LENGTH_LONG).show();
                                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                            startActivity(intent);
                                                            Toast.makeText(KhachHang.this, "Mời bạn tiếp tục mua hàng", Toast.LENGTH_LONG).show();
                                                        }else {
                                                            Toast.makeText(KhachHang.this, "Lỗi khi thêm ChiTietDonHang !", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        Toast.makeText(KhachHang.this, "Lỗi server khi get data ChiTietDonHang !", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                        ){
                                            @Override
                                            protected Map<String, String> getParams() throws AuthFailureError {//Thêm chi tiết đơn hàng
                                                JSONArray jsonArray = new JSONArray();

                                                for(int i = 0; i < MainActivity.arrayListGioHang.size(); i++){
                                                    JSONObject jsonObject = new JSONObject();
                                                    try {
                                                        jsonObject.put("MaDH", responseMaDH);
                                                        jsonObject.put("MaSP", MainActivity.arrayListGioHang.get(i).getIdsp());
                                                        jsonObject.put("SoLuong", MainActivity.arrayListGioHang.get(i).getSoluong());
                                                        jsonObject.put("DonGia", MainActivity.arrayListGioHang.get(i).getGiasp());

                                                        jsonArray.put(jsonObject);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                Map<String, String> map = new HashMap<>();
                                                map.put("MyJSON", String.valueOf(jsonArray));
                                                return map;
                                            }
                                        };//end stringRequest1

                                        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                                        requestQueue1.add(stringRequest1);
                                    }// end if

                                }// end onResponse
                            },//end  new Response.Listener{}
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(KhachHang.this, "Lỗi khi get data table DonHang !!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {//Thêm đơn hàng
                            Map<String, String> param = new HashMap<>();
                            param.put("tongTien",String.valueOf(tongTien));
                            param.put("tenKH",tenkh);
                            param.put("dienThoai",sdt);
                            param.put("email",email);
                            param.put("diaChi",diachi);
                            param.put("gioiTinh", finalGioiTinh);
                            return param;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }else {
                    Toast.makeText(KhachHang.this, "Thông tin khách hàng không được rỗng, giỏ hàng phải có sản phẩm !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
