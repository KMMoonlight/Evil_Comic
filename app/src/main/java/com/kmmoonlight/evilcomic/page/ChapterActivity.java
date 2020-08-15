package com.kmmoonlight.evilcomic.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kmmoonlight.evilcomic.R;
import com.kmmoonlight.evilcomic.adapter.ChapterPagerAdapter;
import com.kmmoonlight.evilcomic.net.Callback;
import com.kmmoonlight.evilcomic.net.NetWorkTask;
import com.kmmoonlight.evilcomic.net.QueryIdTable;
import com.kmmoonlight.evilcomic.parser.ChapterParser;

import java.util.ArrayList;
import java.util.List;

public class ChapterActivity extends AppCompatActivity implements Callback {


    public String chapterUrl = "";

    public String chapterTitle = "";

    public ImageView iv_leading;

    public TextView tv_title;

    public ViewPager vp_comic;

    public TextView tv_page_number;

    public List<String> chapterList;

    public ChapterPagerAdapter chapterPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        Intent intent = getIntent();
        chapterUrl = intent.getStringExtra("ChapterUrl");
        chapterTitle = intent.getStringExtra("ChapterTitle");

        initView();

        initData();
    }

    private void initData() {
        NetWorkTask.QueryComicChapter(chapterUrl, this, QueryIdTable.COMIC_CHAPTER_DATA);
    }

    private void initView() {
        iv_leading = findViewById(R.id.iv_leading);
        iv_leading.setBackgroundResource(R.drawable.ic_back);
        iv_leading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(chapterTitle);

        vp_comic = findViewById(R.id.vp_comic);
        tv_page_number = findViewById(R.id.tv_comic_page_number);

        chapterList = new ArrayList<>();

        chapterPagerAdapter = new ChapterPagerAdapter(chapterList, this);

        vp_comic.setAdapter(chapterPagerAdapter);

        vp_comic.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_page_number.setText((position + 1) + "/" + chapterList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onResult(String result, int requestId) {
        chapterList.clear();
        chapterList.addAll(ChapterParser.parseChapter(result));
        chapterPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error, int requestId) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }



}