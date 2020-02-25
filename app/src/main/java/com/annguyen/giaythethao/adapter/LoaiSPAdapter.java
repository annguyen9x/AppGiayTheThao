package com.annguyen.giaythethao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.annguyen.giaythethao.R;
import com.annguyen.giaythethao.model.LoaiSP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiSPAdapter extends BaseAdapter {
    private ArrayList<LoaiSP> arrayList;
    private Context context;//Màn hình

    public LoaiSPAdapter(ArrayList<LoaiSP> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Class để lưu các View đã khởi tạo khi load lên lần đàu, để khi cuộn lại không khởi tạo lại
    class ViewHolder{
        TextView txtLoaiSP;
        ImageView imgLoaiSP;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null ){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Gán convertView = View Item ( Trong NavigationView)
            convertView = layoutInflater.inflate(R.layout.row_item_loai_sp,null);

            viewHolder.txtLoaiSP = convertView.findViewById(R.id.textViewLoaiSP);
            viewHolder.imgLoaiSP = convertView.findViewById(R.id.imageViewLoaiSP);

            convertView.setTag(viewHolder);//Gán các Tag đã lấy được vào View hiện tại]
        }else{
           viewHolder = (ViewHolder) convertView.getTag();
        }

        LoaiSP loaiSP = (LoaiSP) getItem(position);

        viewHolder.txtLoaiSP.setText(loaiSP.getTenLoai());

       //Nếu bị lỗi load ảnh thì gán ảnh lỗi theo loaisp
        int img_error = R.drawable.error_image;
        switch (loaiSP.getIDLoai()){
            case 1:
                img_error = R.drawable.logo_nike;
                break;
            case 2:
                img_error = R.drawable.logo_vans;
            case 3:
                img_error = R.drawable.logo_adidas;
        }
        //Gán ảnh
        Picasso.with(context).load(loaiSP.getHinhAnh())
                .placeholder(R.drawable.logo_vans)//ảnh chờ khi đang laod
                .error(img_error)//ảnh nếu load lỗi
                .into(viewHolder.imgLoaiSP);//Gán URL cho hình ảnh
        return convertView;
    }
}
