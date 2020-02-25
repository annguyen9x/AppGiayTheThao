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

public class GiayNikeAdapter extends BaseAdapter {
    ArrayList<SanPham> arrayListSanPham;
    Context context;

    public GiayNikeAdapter(ArrayList<SanPham> arrayListSanPham, Context context) {
        this.arrayListSanPham = arrayListSanPham;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListSanPham.size();//Trả về tất cả mảng
    }

    @Override
    public Object getItem(int position) {
        return arrayListSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //tạo class để lưu các View đã load để khỏi load lại khi kéo màn hình lên xuống
    public class ViewHolder{
        private ImageView imageViewAnhSP;
        private TextView textViewTenSP, textViewGiaSP, textViewTinhTrang, textViewTTMoTa;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if( convertView == null ){
            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_item_sp_theo_loai, null);

            viewHolder.imageViewAnhSP = convertView.findViewById(R.id.imageViewAnhSP);
            viewHolder.textViewTenSP = convertView.findViewById(R.id.textViewTenSP);
            viewHolder.textViewGiaSP = convertView.findViewById(R.id.textViewGiaSP);
            viewHolder.textViewTinhTrang = convertView.findViewById(R.id.textViewTinhTrang);
            viewHolder.textViewTTMoTa = convertView.findViewById(R.id.textViewTTMoTa);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SanPham sp = (SanPham) getItem(position);

        //Gán ảnh cho sản phẩm
        Picasso.with(context).load(sp.getHinhAnh())
                .placeholder(R.drawable.vans)
                .error(R.drawable.error_image)
                .into(viewHolder.imageViewAnhSP);

        //Gán textView TênSP 1 dòng, có ... nếu độ dài vượt quá
        viewHolder.textViewTenSP.setMaxLines(1);
        viewHolder.textViewTenSP.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.textViewTenSP.setText(sp.getTenSP());

        NumberFormat numberFormat = new DecimalFormat("###,###,##0.###");
        viewHolder.textViewGiaSP.setText(numberFormat.format(sp.getGia()) + " đ");

        //Kiểm tra nếu sl > 0 thì tình trạng = "còn hàng"
        if(sp.getSoLuong() > 0 ){
            viewHolder.textViewTinhTrang.setText("Tình trạng: còn hàng");
        }else {
            viewHolder.textViewTinhTrang.setText("Tình trạng: hết hàng");
        }

        //textView Tóm tắt mô tả sản phẩm
        viewHolder.textViewTTMoTa.setMaxLines(2);//Dài tối đa 2 dòng
        viewHolder.textViewTTMoTa.setEllipsize(TextUtils.TruncateAt.END);//Thêm dấu 3 chấm (...) ở cuối nếu dài hơn 3 dòng
        viewHolder.textViewTTMoTa.setText(sp.getMoTa());
        return convertView;
    }
}
