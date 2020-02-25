package com.annguyen.giaythethao.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.annguyen.giaythethao.R;
import com.annguyen.giaythethao.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GiayAdidasAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> arrayListSP;

    public GiayAdidasAdapter(Context context, ArrayList<SanPham> arrayListSP) {
        this.context = context;
        this.arrayListSP = arrayListSP;
    }

    @Override
    public int getCount() {
        return arrayListSP.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListSP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Class luu các View khi keo man hinh ko khoi tao lai
    public class ViewHolder{
        ImageView imageViewAnhSP;
        TextView  textViewTenSP, textViewGia, textViewTinhTrang, textViewTTMoTa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null ){
            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_item_sp_theo_loai, null);

            viewHolder.imageViewAnhSP = convertView.findViewById(R.id.imageViewAnhSP);
            viewHolder.textViewTenSP = convertView.findViewById(R.id.textViewTenSP);
            viewHolder.textViewGia = convertView.findViewById(R.id.textViewGiaSP);
            viewHolder.textViewTinhTrang = convertView.findViewById(R.id.textViewTinhTrang);
            viewHolder.textViewTTMoTa = convertView.findViewById(R.id.textViewTTMoTa);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        SanPham sp = arrayListSP.get(position);

        //Gán ảnh sp
        Picasso.with(context).load(sp.getHinhAnh())
                .placeholder(R.drawable.vans)
                .error(R.drawable.error_image)
                .into(viewHolder.imageViewAnhSP);

        //Gán textView TênSP 1 dòng, có ... nếu độ dài vượt quá
        viewHolder.textViewTenSP.setMaxLines(1);
        viewHolder.textViewTenSP.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.textViewTenSP.setText(sp.getTenSP());

        NumberFormat numberFormat = new DecimalFormat("###,###,##0.###");
        viewHolder.textViewGia.setText(numberFormat.format(sp.getGia()) + " đ");
//        nếu sl > 0 => còn hàng
        if(sp.getSoLuong() > 0 ){
            viewHolder.textViewTinhTrang.setText("Tình trạng: còn hàng");
        }else {
            viewHolder.textViewTinhTrang.setText("Tình trạng: hết hàng");
        }

        //Gán textView Tóm Tắt 2 dòng, có ... nếu độ dài vượt quá
        viewHolder.textViewTTMoTa.setMaxLines(2);
        viewHolder.textViewTTMoTa.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.textViewTTMoTa.setText(sp.getMoTa());
        return convertView;
    }

}
