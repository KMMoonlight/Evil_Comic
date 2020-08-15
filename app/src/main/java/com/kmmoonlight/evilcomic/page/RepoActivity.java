package com.kmmoonlight.evilcomic.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kmmoonlight.evilcomic.R;
import com.kmmoonlight.evilcomic.adapter.MainContentAdapter;
import com.kmmoonlight.evilcomic.entity.MainPageComic;
import com.kmmoonlight.evilcomic.net.Callback;
import com.kmmoonlight.evilcomic.net.NetWorkTask;
import com.kmmoonlight.evilcomic.net.QueryIdTable;
import com.kmmoonlight.evilcomic.parser.MainPageParser;

import java.util.ArrayList;
import java.util.List;

public class RepoActivity extends AppCompatActivity implements Callback {

    public TextView tv_title;

    public ImageView iv_back;

    public RecyclerView rv_comic;

    public TextView tv_pre_page;

    public TextView tv_next_page;

    public RelativeLayout rl_loading;

    public int nowPage = 1;

    public MainContentAdapter mainContentAdapter;

    public List<MainPageComic> mainPageComicList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        initView();

        //请求数据
        rl_loading.setVisibility(View.VISIBLE);
        NetWorkTask.GetComicList(nowPage, this, QueryIdTable.COMIC_LIST_DATA);
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("全部的资源了...ORZ");

        iv_back = findViewById(R.id.iv_leading);
        iv_back.setBackgroundResource(R.drawable.ic_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rv_comic = findViewById(R.id.rv_comic);
        tv_pre_page = findViewById(R.id.tv_pre_page);
        tv_next_page = findViewById(R.id.tv_next_page);
        rl_loading = findViewById(R.id.rl_loading);


        tv_pre_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowPage == 1) {
                    Toast.makeText(RepoActivity.this, "已经是第一页了!", Toast.LENGTH_SHORT).show();
                }else {
                    rl_loading.setVisibility(View.VISIBLE);
                    nowPage -= 1;
                    NetWorkTask.GetComicList(nowPage, RepoActivity.this, QueryIdTable.COMIC_LIST_DATA);
                }
            }
        });

        tv_next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_loading.setVisibility(View.VISIBLE);
                nowPage += 1;
                NetWorkTask.GetComicList(nowPage, RepoActivity.this, QueryIdTable.COMIC_LIST_DATA);
            }
        });

        mainPageComicList = new ArrayList<>();
        mainContentAdapter = new MainContentAdapter(this, mainPageComicList);

        rv_comic.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
        rv_comic.setAdapter(mainContentAdapter);
    }

    @Override
    public void onResult(String result, int requestId) {
        rl_loading.setVisibility(View.GONE);
        //解析
        mainPageComicList.clear();
        mainPageComicList.addAll(MainPageParser.parseRepoComicList(result));
        mainContentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error, int requestId) {
        rl_loading.setVisibility(View.GONE);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}