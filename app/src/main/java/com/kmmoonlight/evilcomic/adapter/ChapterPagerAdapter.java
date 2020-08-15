package com.kmmoonlight.evilcomic.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.kmmoonlight.evilcomic.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

public class ChapterPagerAdapter extends PagerAdapter {

    public List<String> dataList;
    public Context context;
    public LayoutInflater layoutInflater;

    public ChapterPagerAdapter(List<String> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View pageView = layoutInflater.inflate(R.layout.chapter_view_layout, null);

        ImageView imageContent = pageView.findViewById(R.id.iv_comic_image);

        Glide.with(context)
                .load(dataList.get(position))
                .override(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageContent);

        container.addView(pageView);
        return pageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
