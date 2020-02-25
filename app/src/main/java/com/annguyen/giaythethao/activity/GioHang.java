package com.annguyen.giaythethao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.annguyen.giaythethao.R;
import com.annguyen.giaythethao.adapter.GioHangAdapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GioHang extends AppCompatActivity {
    private Toolbar toolbarGH;
    private TextView textViewThongBaoGH;
    private  static  TextView textViewTongTien;
    private ListView listViewGH;
    private Button buttonDatHang, buttonMuaThem;

    public GioHangAdapter gioHangAdapter;
    private static Double toTalMany = 0D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        widget();
        setMyActionBar();
        checkDataGH();//Kiểm tra nếu gh rỗng thì hiển thị thông báo
        setTongTienGioHang();
        setOnClickButton();//Sự kiện khi click vào 2 button
    }

    private void widget() {
        toolbarGH = findViewById(R.id.toolbarGioHang);
        textViewThongBaoGH = findViewById(R.id.textViewThongBaoGH);
        textViewTongTien = findViewById(R.id.textViewTongTienGH);
        listViewGH = findViewById(R.id.listViewGH);
        buttonDatHang = findViewById(R.id.buttonDatHang);
        buttonMuaThem = findViewById(R.id.buttonMuaThemGH);

        gioHangAdapter = new GioHangAdapter(GioHang.this, MainActivity.arrayListGioHang);
        listViewGH.setAdapter(gioHangAdapter);
    }

    private void setMyActionBar() {
        setSupportActionBar(toolbarGH);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbarGH.setNavigationIcon(android.R.drawable.ic_media_previous);

        //Sự kiện click vào Toolbar quay về trang chủ
        toolbarGH.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void checkDataGH() {
        if(MainActivity.arrayListGioHang.size() == 0 ){
            gioHangAdapter.notifyDataSetChanged();//Cập nhật dữ liệu gh
            textViewThongBaoGH.setVisibility(View.VISIBLE);//Hiển thị TextView bị đè bởi listView trong FrameLayout
            listViewGH.setVisibility(View.INVISIBLE);//Ẩn ListView đè TextView trong FrameLayouot
        }else{
            gioHangAdapter.notifyDataSetChanged();
            textViewThongBaoGH.setVisibility(View.INVISIBLE);//Ẩn
            listViewGH.setVisibility(View.VISIBLE);//Hiện
        }
    }

    public static void setTongTienGioHang(){
        Double tongTien=0D;
        for(int i = 0; i < MainActivity.arrayListGioHang.size(); i++){
            tongTien += MainActivity.arrayListGioHang.get(i).getThanhTien();
        }
        toTalMany = tongTien;
        NumberFormat numberFormat = new DecimalFormat("###,###,##0.###");
        textViewTongTien.setText(numberFormat.format(tongTien)+ " đ");
    }

    private void setOnClickButton(){
        buttonMuaThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GioHang.this, MainActivity.class));
            }
        });

        buttonDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( MainActivity.arrayListGioHang.size() >= 1 ){
                    Intent intent = new Intent(getApplicationContext(), KhachHang.class);
                    intent.putExtra("TongTien", toTalMany);
                    startActivity(intent);
                }else{
                    Toast.makeText(GioHang.this, "Giỏ hàng của bạn chưa có sản phẩm để thanh toán !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
