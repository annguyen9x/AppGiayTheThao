package com.annguyen.giaythethao.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.annguyen.giaythethao.R;
import com.annguyen.giaythethao.activity.ChiTietSanPham;
import com.annguyen.giaythethao.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> {
    Context context;
    ArrayList<SanPham> arrayListSP;

    public SanPhamAdapter(Context context, ArrayList<SanPham> arrayListSP) {
        this.context = context;
        this.arrayListSP = arrayListSP;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewAnhSP;
        private TextView textViewTenSPandGT, textViewGiaSP;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAnhSP = itemView.findViewById(R.id.imageViewAnhSP);
            textViewTenSPandGT = itemView.findViewById(R.id.textViewTenSPandGT);
            textViewGiaSP = itemView.findViewById(R.id.textViewGiaSP);

            //Bắt sự kiện click vào các Item để chuyển sang màn hình trang chi tiết sản phẩm
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSanPham.class);
                    intent.putExtra("sanPham", arrayListSP.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_group_sp, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    //Set các giá trị cho view
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        SanPham sp = arrayListSP.get(position);
        //Gán ảnh sp
        Picasso.with(context).load(sp.getHinhAnh()).placeholder(R.drawable.vans)
                .error(R.drawable.error_image).resize(100,100).into(holder.imageViewAnhSP);

        //Gán các Text cho sản phẩm
        holder.textViewTenSPandGT.setText(sp.getTenSP()+ " - " + sp.getGioiTinh());

        NumberFormat numberFormat = new DecimalFormat("##,###,##0.###");
        holder.textViewGiaSP.setText(numberFormat.format(sp.getGia()) + " đ");
    }

    @Override
    public int getItemCount() {
        return arrayListSP.size();//Trả về tất cả sp trong mảng
    }

}
