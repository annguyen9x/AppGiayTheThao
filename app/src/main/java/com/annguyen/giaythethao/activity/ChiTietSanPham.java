package com.annguyen.giaythethao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.annguyen.giaythethao.R;
import com.annguyen.giaythethao.model.GioHang;
import com.annguyen.giaythethao.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ChiTietSanPham extends AppCompatActivity {
    private Toolbar toolbarCTSP;
    private ImageView imageViewAnhCTSP;
    private TextView textViewTenSPCTSP, textViewGiaCTSP, textViewTinhTrangCTSP, textViewGioiTinhCTSP, textViewMauCTSP, textViewMotaCTSP;
    private Spinner spinnerSL;
    private Button btnThemVaoGH;

    private SanPham sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        widget();
        setMyActionBar();//Thanh ActionBar
        getDataInIntent();
    }

    //Hàm gắn Menu icon Giỏ hàng
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giohang, menu);
        // return super.onCreateOptionsMenu(menu);//Đều đúng
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
        toolbarCTSP = findViewById(R.id.toolbarChiTietSP);
        imageViewAnhCTSP = findViewById(R.id.imageViewAnhCTSP);
        textViewTenSPCTSP = findViewById(R.id.textViewTenSpCTSP);
        textViewGiaCTSP = findViewById(R.id.textViewGiaSpCTSP);
        textViewTinhTrangCTSP = findViewById(R.id.textViewTinhTrangCTSP);
        textViewGioiTinhCTSP = findViewById(R.id.textViewGioiTinhCTSP);
        textViewMauCTSP = findViewById(R.id.textViewMauSacCTSP);
        textViewMotaCTSP = findViewById(R.id.textViewMoTaCTSP);
        spinnerSL = findViewById(R.id.spinnerSlCTSP);
        btnThemVaoGH = findViewById(R.id.buttonThemGH);

        //Sự kiện click button "ThemVaoGH"
        setClickButtonGioHang();
    }

    private void setMyActionBar() {
        setSupportActionBar(toolbarCTSP);
        getSupportActionBar().setHomeButtonEnabled(true);//Bật nút home
        toolbarCTSP.setNavigationIcon(android.R.drawable.ic_media_previous);//Set Icon quay về
        //Sự kiện Click nút quay về
        toolbarCTSP.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//Tắt view hiện tại
            }
        });
    }

    private void getDataInIntent() {
        sp = (SanPham) getIntent().getSerializableExtra("sanPham");
        //Gán hình ảnh cho SP
        Picasso.with(getApplicationContext()).load(sp.getHinhAnh()).placeholder(R.drawable.vans).error(R.drawable.error_image).into(imageViewAnhCTSP);

        //Gán giá trị cho các TextView
        textViewTenSPCTSP.setText(sp.getTenSP());

        NumberFormat numberFormat = new DecimalFormat("###,###,##0.###");
        textViewGiaCTSP.setText(numberFormat.format(sp.getGia())+ " đ");

        //nếu sl > 0 => còn hàng
        if(sp.getSoLuong() > 0 ){
            textViewTinhTrangCTSP.setText("Tình trạng: còn hàng");
        }else {
            textViewTinhTrangCTSP.setText("Tình trạng: hết hàng");
        }

        textViewGioiTinhCTSP.setText("Size " + sp.getGioiTinh() +": "+ sp.getSize());
        textViewMauCTSP.setText("Màu: " + sp.getMauSac());

        //Set Data cho spinner Số lượng
        setDataMySpinner(sp.getSoLuong());

        textViewMotaCTSP.setText(sp.getMoTa());
    }

    private void setDataMySpinner(int soLuong) {
      //set Data cho Spinner Số lượng
        Integer[]  arrSL = new Integer[soLuong];
        for(int i = 0; i < soLuong; i++){
            arrSL[i] = i+1;
        }
        ArrayAdapter<Integer> arrayAdapterSL = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_item,arrSL);
        spinnerSL.setAdapter(arrayAdapterSL);
    }

    private void setClickButtonGioHang() {
        btnThemVaoGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuong = Integer.parseInt(spinnerSL.getSelectedItem().toString());

                //Trường hợp giỏ hàng đã có sản phẩm, chỉ cập nhật số lượng, hoặc thêm mới một sp
                if(MainActivity.arrayListGioHang.size() > 0){
                    boolean isCheckThem = false;

                    for (int i = 0; i < MainActivity.arrayListGioHang.size(); i++){
                        //Trường hợp giỏ hàng đã có sp, và cập nhật số lượng
                        if( MainActivity.arrayListGioHang.get(i).getIdsp() == sp.getId() ){
                            if( sp.getSoLuong() >= (soLuong + MainActivity.arrayListGioHang.get(i).getSoluong()) ) {
                                int slMoi = soLuong + MainActivity.arrayListGioHang.get(i).getSoluong();
                                MainActivity.arrayListGioHang.get(i).setSoluong(slMoi);
                                MainActivity.arrayListGioHang.get(i).setThanhTien(slMoi* MainActivity.arrayListGioHang.get(i).getGiasp());
                            }else{
                                Toast.makeText(ChiTietSanPham.this, "Số lượng không đủ, chỉ có thể mua thêm: " + (sp.getSoLuong() - MainActivity.arrayListGioHang.get(i).getSoluong())+ " sản phẩm !", Toast.LENGTH_LONG).show();
                            }
                            isCheckThem = true;
                        }
                    }
                    //Trường hợp giỏ hàng đã có sp, và thêm mới 1 sp
                    if( isCheckThem == false ){
                        MainActivity.arrayListGioHang.add(new GioHang(sp.getId(), sp.getHinhAnh(),
                                sp.getTenSP(), sp.getGioiTinh(), sp.getSize(), sp.getMauSac(), sp.getGia(), soLuong, sp.getGia()*soLuong));
                    }
                }else{//Trường hợp chưa có sản phẩm phải thêm mới sp
                    MainActivity.arrayListGioHang.add(new GioHang(sp.getId(), sp.getHinhAnh(),
                            sp.getTenSP(), sp.getGioiTinh(), sp.getSize(), sp.getMauSac(), sp.getGia(), soLuong, sp.getGia()*soLuong));
                }

                Intent intent = new Intent(getApplicationContext(), com.annguyen.giaythethao.activity.GioHang.class);
                startActivity(intent);
            }
        });
    }

}
