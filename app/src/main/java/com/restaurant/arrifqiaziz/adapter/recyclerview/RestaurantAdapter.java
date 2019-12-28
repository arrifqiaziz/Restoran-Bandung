package com.restaurant.arrifqiaziz.adapter.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.restaurant.arrifqiaziz.R;
import com.restaurant.arrifqiaziz.model.restaurant.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    List<Restaurant> list;
    Context context;

    OnItemClickListener mOnItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_info)
        ImageView imageInfo;
        @BindView(R.id.txt_judul)
        TextView txtJudul;
        @BindView(R.id.txt_alamat)
        TextView txtAlamat;
        @BindView(R.id.txt_kategori)
        TextView txtKategori;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public RestaurantAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Restaurant item = list.get(position);
        Picasso.get().load(item.getFoto()).into(holder.imageInfo);
        holder.txtJudul.setText(item.getNamarm());
        holder.txtAlamat.setText(item.getAlamat());
        holder.txtKategori.setText(item.getKategori());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(Restaurant item) {
        list.add(item);
        notifyItemInserted(list.size() + 1);
    }

    public void addAll(List<Restaurant> listItem) {
        for (Restaurant item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        list.clear();
        notifyDataSetChanged();
    }

    public void swap(List<Restaurant> datas) {
        if (datas == null || datas.size() == 0)
            return;
        if (list != null && list.size() > 0)
            list.clear();
        list.addAll(datas);
        notifyDataSetChanged();

    }

    public Restaurant getItem(int pos) {
        return list.get(pos);
    }

    public String showHourMinute(String hourMinute) {
        String time = "";
        time = hourMinute.substring(0, 5);
        return time;
    }

    public void setFilter(List<Restaurant> list) {
        list = new ArrayList<>();
        list.addAll(list);
        notifyDataSetChanged();
    }

    public List<Restaurant> getListItem() {
        return list;
    }

}
