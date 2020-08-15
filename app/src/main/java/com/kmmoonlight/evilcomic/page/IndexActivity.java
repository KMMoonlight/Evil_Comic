package com.kmmoonlight.evilcomic.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kmmoonlight.evilcomic.R;
import com.kmmoonlight.evilcomic.adapter.ChapterAdapter;
import com.kmmoonlight.evilcomic.entity.ComicIndex;
import com.kmmoonlight.evilcomic.net.Callback;
import com.kmmoonlight.evilcomic.net.NetWorkTask;
import com.kmmoonlight.evilcomic.net.QueryIdTable;
import com.kmmoonlight.evilcomic.parser.ComicIndexParser;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener, Callback {

    public String comicUrl = "/book/459";

    public ImageView iv_leading;

    public RelativeLayout rl_loading;

    public ComicIndex comicIndex;

    public ImageView iv_comic_image;

    public TextView tv_comic_title;

    public TextView tv_comic_author;

    public TextView tv_comic_area;

    public TextView tv_comic_state;

    public TextView tv_comic_introduce;

    public RecyclerView rv_chapter;

    public ChapterAdapter chapterAdapter;

    public List<ComicIndex.Chapter> chapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Intent intent = getIntent();
        comicUrl = intent.getStringExtra("comicUrl");

        initView();
        initData();
    }

    private void initData() {
        NetWorkTask.QueryComicIndex(comicUrl, this, QueryIdTable.COMIC_INDEX_DATA);
    }

    private void initView() {
        iv_leading = findViewById(R.id.iv_leading);
        iv_leading.setBackgroundResource(R.drawable.ic_back);
        iv_leading.setOnClickListener(this);

        rl_loading = findViewById(R.id.rl_loading);

        iv_comic_image = findViewById(R.id.iv_comic_image);
        tv_comic_title = findViewById(R.id.tv_comic_title);
        tv_comic_author = findViewById(R.id.tv_comic_author);
        tv_comic_area = findViewById(R.id.tv_comic_area);
        tv_comic_state = findViewById(R.id.tv_comic_state);
        tv_comic_introduce = findViewById(R.id.tv_introduce);
        rv_chapter = findViewById(R.id.rv_chapter);
        rv_chapter.setLayoutManager(new GridLayoutManager(IndexActivity.this, 3, RecyclerView.VERTICAL, false));
        chapterList = new ArrayList<>();
        chapterAdapter = new ChapterAdapter(IndexActivity.this, chapterList);
        rv_chapter.setAdapter(chapterAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_leading:
                onBackPressed();
                break;

        }
    }

    @Override
    public void onResult(String result, int requestId) {
        rl_loading.setVisibility(View.GONE);
        comicIndex = ComicIndexParser.parseComicIndex(result);

        //设置数据
        Glide.with(IndexActivity.this)
                .load(comicIndex.getComicImage())
                .centerCrop()
                .into(iv_comic_image);

        tv_comic_title.setText(comicIndex.getComicTitle());
        tv_comic_author.setText(comicIndex.getComicAuthor());
        tv_comic_area.setText(comicIndex.getComicArea());
        tv_comic_state.setText(comicIndex.getComicState());
        tv_comic_introduce.setText(comicIndex.getComicIntroduce());

        chapterList.clear();
        chapterList.addAll(comicIndex.getComicChapterList());
        chapterAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error, int requestId) {
        rl_loading.setVisibility(View.GONE);
        Toast.makeText(IndexActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}