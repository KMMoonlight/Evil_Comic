package com.kmmoonlight.evilcomic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kmmoonlight.evilcomic.R;
import com.kmmoonlight.evilcomic.entity.ComicIndex;
import com.kmmoonlight.evilcomic.page.ChapterActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> implements View.OnClickListener {

    public Context context;

    public LayoutInflater layoutInflater;

    public List<ComicIndex.Chapter> chapterList;

    public ChapterAdapter(Context context, List<ComicIndex.Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = layoutInflater.inflate(R.layout.chapter_item_layout, parent, false);
        return new ChapterViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        holder.btn_chapter.setText(chapterList.get(position).getChapterTitle());
        holder.btn_chapter.setTag(chapterList.get(position));
        holder.btn_chapter.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    @Override
    public void onClick(View view) {
        ComicIndex.Chapter chapter = (ComicIndex.Chapter) view.getTag();
        Intent chapterIntent = new Intent(context, ChapterActivity.class);
        chapterIntent.putExtra("ChapterUrl", chapter.getChapterUrl());
        chapterIntent.putExtra("ChapterTitle", chapter.getChapterTitle());
        context.startActivity(chapterIntent);
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder {

        Button btn_chapter;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_chapter = itemView.findViewById(R.id.btn_chapter);
        }

    }

}
