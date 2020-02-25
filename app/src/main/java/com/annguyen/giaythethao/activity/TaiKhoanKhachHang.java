package com.annguyen.giaythethao.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.annguyen.giaythethao.R;
import com.annguyen.giaythethao.model.KhachHangModel;
import com.annguyen.giaythethao.ultil.CheckConnection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TaiKhoanKhachHang extends AppCompatActivity {
    private Toolbar toolbarTaiKhoan;
    private TextView textViewTen, textViewEmail, textViewDienThoai, textViewGioiTinh, textViewDiaChi;
    private Button buttonCapNhat, buttonDangXuat, buttonTrangChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_khach_hang);

        widget();
        setMyActionBar();
        setDataOfActivity();
    }

    private void widget() {
        toolbarTaiKhoan = findViewById(R.id.toolbarTaiKhoan);
        textViewTen = findViewById(R.id.textViewTenTK);
        textViewEmail = findViewById(R.id.textViewEmailTK);
        textViewDienThoai = findViewById(R.id.textViewDienThoaiTK);
        textViewGioiTinh = findViewById(R.id.textViewGioTinhTK);
        textViewDiaChi = findViewById(R.id.textViewDiaChiTK);
        buttonCapNhat = findViewById(R.id.buttonCapNhatTK);
        buttonDangXuat = findViewById(R.id.buttonDangXuat);
        buttonTrangChu = findViewById(R.id.buttonTrangChuTK);

        buttonTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        buttonCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CapNhatTaiKhoan.class);
                startActivity(intent);
            }
        });

        buttonDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mapKhachHang.clear();
                Toast.makeText(TaiKhoanKhachHang.this, "Đã đăng xuất tài khoản !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setMyActionBar() {
        setSupportActionBar(toolbarTaiKhoan);
        getSupportActionBar().setHomeButtonEnabled(true);//Bật nút Home cho ActionBar
        toolbarTaiKhoan.setNavigationIcon(android.R.drawable.ic_media_previous);//set Icon cho ActionBar
        //Cài đặt sự kiện click
        toolbarTaiKhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//Đóng Activity hiện tại
            }
        });
    }

    private void setDataOfActivity() {
        KhachHangModel khachHangModel = MainActivity.mapKhachHang.get("TaiKhoan");
        textViewTen.setText(textViewTen.getText() + khachHangModel.getTenKH());
        textViewEmail.setText(textViewEmail.getText() + khachHangModel.getEmail());
        textViewDienThoai.setText(textViewDienThoai.getText() + khachHangModel.getDienThoai());
        textViewGioiTinh.setText(textViewGioiTinh.getText() + khachHangModel.getGioTinh());
        textViewDiaChi.setText(textViewDiaChi.getText() + khachHangModel.getDiaChi());
    }
}
