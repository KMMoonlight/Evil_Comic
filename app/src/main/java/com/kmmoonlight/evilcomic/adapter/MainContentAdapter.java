package com.kmmoonlight.evilcomic.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kmmoonlight.evilcomic.R;
import com.kmmoonlight.evilcomic.entity.MainPageComic;
import com.kmmoonlight.evilcomic.page.IndexActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainContentAdapter extends RecyclerView.Adapter<MainContentAdapter.MainContentViewHolder> implements View.OnClickListener {

    public Context context;
    public LayoutInflater layoutInflater;
    public List<MainPageComic> dataList;

    public MainContentAdapter(Context context, List<MainPageComic> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MainContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = layoutInflater.inflate(R.layout.comic_item_layout, parent, false);
        return new MainContentViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainContentViewHolder holder, int position) {

        holder.tv_comic_title.setText(dataList.get(position).getComicName());
        holder.tv_comic_info.setText(dataList.get(position).getComicInfo());

        Glide.with(context)
                .load(dataList.get(position).getComicImage())
                .centerCrop()
                .into(holder.iv_comic_image);

        holder.ll_comic.setTag(dataList.get(position));

        holder.ll_comic.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    @Override
    public void onClick(View view) {
        MainPageComic mainPageComic = (MainPageComic) view.getTag();
        Intent indexIntent = new Intent(context, IndexActivity.class);
        indexIntent.putExtra("comicUrl", mainPageComic.getComicUrl());
        context.startActivity(indexIntent);
    }

    public static class MainContentViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_comic_image;

        public TextView tv_comic_title;

        public TextView tv_comic_info;

        public LinearLayout ll_comic;

        public MainContentViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_comic_image = itemView.findViewById(R.id.iv_comic_image);
            tv_comic_title = itemView.findViewById(R.id.tv_comic_title);
            tv_comic_info = itemView.findViewById(R.id.tv_comic_info);
            ll_comic = itemView.findViewById(R.id.ll_comic);
        }
    }



}
