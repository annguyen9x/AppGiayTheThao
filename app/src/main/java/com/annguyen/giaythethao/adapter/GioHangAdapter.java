package com.annguyen.giaythethao.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.annguyen.giaythethao.R;
import com.annguyen.giaythethao.activity.MainActivity;
import com.annguyen.giaythethao.model.GioHang;
import com.annguyen.giaythethao.model.SanPham;
import com.annguyen.giaythethao.ultil.CheckConnection;
import com.annguyen.giaythethao.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.annguyen.giaythethao.activity.GioHang.setTongTienGioHang;

public class GioHangAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GioHang> arrayListGH;
    private SanPham sanPhamTrongKho;//Để kt soluong mua < sl
    private GioHangAdapter gioHangAdapter;//dung reload page

    public GioHangAdapter(Context context, ArrayList<GioHang> arrayListGH) {
        this.context = context;
        this.arrayListGH = arrayListGH;
        this.gioHangAdapter = this;
    }

    @Override
    public int getCount() {
        return arrayListGH.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListGH.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        ImageView imageViewAnhSP;
        TextView textViewTenSP, textViewGioiTinhSize, textViewMauSac, textViewGia;
        Button btnCongSL, btnTruSL, btnSL;
        ImageButton imageButtonXoaSP;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_item_gio_hang, null);

            viewHolder.imageViewAnhSP = convertView.findViewById(R.id.imageViewAnhGH);
            viewHolder.textViewTenSP = convertView.findViewById(R.id.textViewTenSpGH);
            viewHolder.textViewGioiTinhSize = convertView.findViewById(R.id.textViewGioiTinhSizeGH);
            viewHolder.textViewMauSac = convertView.findViewById(R.id.textViewMauSacGH);
            viewHolder.textViewGia = convertView.findViewById(R.id.textViewGiaSpGH);
            viewHolder.btnTruSL = convertView.findViewById(R.id.buttonTruSlGH);
            viewHolder.btnSL = convertView.findViewById(R.id.buttonSlGH);
            viewHolder.btnCongSL = convertView.findViewById(R.id.buttonCongSlGH);
            viewHolder.imageButtonXoaSP = convertView.findViewById(R.id.imageViewXoaSPGH);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GioHang gh = (GioHang) getItem(position);

        Picasso.with(context).load(gh.getHinhAnh())
                .placeholder(R.drawable.vans)
                .error(R.drawable.error_image)
                .into(viewHolder.imageViewAnhSP);

        viewHolder.textViewTenSP.setText(gh.getTensp());
        viewHolder.textViewGioiTinhSize.setText("Size "+gh.getGioitinh()+": " +gh.getSize());
        viewHolder.textViewMauSac.setText("- "+gh.getMausac());

        NumberFormat numberFormat = new DecimalFormat("###,###,##0.###");
        viewHolder.textViewGia.setText(numberFormat.format(gh.getGiasp()) + " đ");

        //Set Số lượng
        int soluong = gh.getSoluong();
        viewHolder.btnSL.setText(String.valueOf(soluong));

        //Set sự kiện khi click vào button "-"
        viewHolder.btnTruSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( Integer.parseInt(viewHolder.btnSL.getText().toString()) > 1 ){
                    int slMoi = Integer.parseInt(viewHolder.btnSL.getText().toString()) - 1;
                    viewHolder.btnSL.setText(String.valueOf(slMoi));

                    //update lại số lượng, thành tiền cho giỏ hàng, và tổng tiền
                    MainActivity.arrayListGioHang.get(position).setSoluong(slMoi);
                    MainActivity.arrayListGioHang.get(position).setThanhTien(slMoi* MainActivity.arrayListGioHang.get(position).getGiasp());
                    setTongTienGioHang();
                }else {
                    Toast.makeText(context, "Số lượng mua phải lớn hơn 0 !!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Kiểm tra sl còn bao nhiêu trong kho trước khi set cho sự kiện tăng sp lên (nhấn "+")
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlChiTietSanPhamTheoIDSP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if ( response != null && response.length() != 2 ){
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                    sanPhamTrongKho = new SanPham(
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
                                    );
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CheckConnection.showToast(context, "Lỗi khi get data table SanPham !!!");
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("IDSP", String.valueOf(MainActivity.arrayListGioHang.get(position).getIdsp()));
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        //Set sự kiện khi click vào button "+"
        viewHolder.btnCongSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sanPhamTrongKho.getSoLuong() > Integer.parseInt(viewHolder.btnSL.getText().toString())) {
                    int slMoi = Integer.parseInt(viewHolder.btnSL.getText().toString()) + 1;
                    viewHolder.btnSL.setText(String.valueOf(slMoi));

                    //update lại số lượng, thành tiền cho giỏ hàng, và tổng tiền
                    MainActivity.arrayListGioHang.get(position).setSoluong(slMoi);
                    MainActivity.arrayListGioHang.get(position).setThanhTien(slMoi * MainActivity.arrayListGioHang.get(position).getGiasp());
                    setTongTienGioHang();
                }else {
                    Toast.makeText(context, "Chỉ còn: " + sanPhamTrongKho.getSoLuong() + " sản phẩm !!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Sự kiện click vào ImageViewXoaSP thì xóa SP
        viewHolder.imageButtonXoaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiển thị thông báo có xóa sp không
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Nếu giỏ hàng rỗng thì hiển thị thông báo
                        if(MainActivity.arrayListGioHang.size() > 0 ){
                            MainActivity.arrayListGioHang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();//Update Adapter GH
                            com.annguyen.giaythethao.activity.GioHang.setTongTienGioHang();//update tổng tiền của giỏ hàng
                        }
                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Bạn đã không xóa sản phẩm này !", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });
        return convertView;
    }
}
