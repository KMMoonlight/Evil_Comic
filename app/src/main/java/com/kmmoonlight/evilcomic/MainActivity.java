package com.kmmoonlight.evilcomic;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kmmoonlight.evilcomic.adapter.MainContentAdapter;
import com.kmmoonlight.evilcomic.entity.MainPageComic;
import com.kmmoonlight.evilcomic.net.Callback;
import com.kmmoonlight.evilcomic.net.NetWorkTask;
import com.kmmoonlight.evilcomic.net.QueryIdTable;
import com.kmmoonlight.evilcomic.page.RepoActivity;
import com.kmmoonlight.evilcomic.page.SearchActivity;
import com.kmmoonlight.evilcomic.parser.MainPageParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Callback {

    public List<List<MainPageComic>> comicCollectionList;

    public View view_nearly;
    public View view_hot;
    public View view_beautiful;
    public View view_love;
    public View view_finish;


    public TextView tv_title;

    public MainContentAdapter nearlyAdapter;
    public MainContentAdapter hotAdapter;
    public MainContentAdapter beautifulAdapter;
    public MainContentAdapter loveAdapter;
    public MainContentAdapter finishAdapter;

    RecyclerView rv_nearly;
    RecyclerView rv_hot;
    RecyclerView rv_beautiful;
    RecyclerView rv_love;
    RecyclerView rv_finish;

    RelativeLayout rl_loading;
    public ImageView iv_search;

    public TextView tv_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        QueryData();
    }

    private void initView() {

        tv_title = findViewById(R.id.tv_title);
        view_nearly = findViewById(R.id.layout_nearly);
        view_hot = findViewById(R.id.layout_hot);
        view_beautiful = findViewById(R.id.layout_beautiful);
        view_love = findViewById(R.id.layout_love);
        view_finish = findViewById(R.id.layout_finish);
        rl_loading = findViewById(R.id.rl_loading);
        iv_search = findViewById(R.id.iv_tailing);
        iv_search.setBackgroundResource(R.drawable.ic_search);

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        tv_more = findViewById(R.id.tv_more);

        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent more_intent = new Intent(MainActivity.this, RepoActivity.class);
                startActivity(more_intent);
            }
        });


        tv_title.setText("EvilComic");
        ((ImageView)findViewById(R.id.iv_leading)).setVisibility(View.GONE);

        ((TextView)view_nearly.findViewById(R.id.tv_category_name)).setText("最近更新");
        ((TextView)view_hot.findViewById(R.id.tv_category_name)).setText("最热漫画");
        ((TextView)view_beautiful.findViewById(R.id.tv_category_name)).setText("耽美");
        ((TextView)view_love.findViewById(R.id.tv_category_name)).setText("恋爱");
        ((TextView)view_finish.findViewById(R.id.tv_category_name)).setText("完结优选");

        rv_nearly = view_nearly.findViewById(R.id.rv_comic);
        rv_hot = view_hot.findViewById(R.id.rv_comic);
        rv_beautiful = view_beautiful.findViewById(R.id.rv_comic);
        rv_love = view_love.findViewById(R.id.rv_comic);
        rv_finish = view_finish.findViewById(R.id.rv_comic);

        rv_nearly.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
        rv_hot.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
        rv_beautiful.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
        rv_love.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
        rv_finish.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
    }

    private void initData() {
        comicCollectionList = new ArrayList<>();
    }


    private void QueryData() {
        NetWorkTask.QueryMainPageData(this, QueryIdTable.MAIN_PAGE_DATA);
    }



    @Override
    public void onResult(String result, int requestId){
        if (requestId == QueryIdTable.MAIN_PAGE_DATA) {
            rl_loading.setVisibility(View.GONE);

            comicCollectionList.clear();
            comicCollectionList.addAll(MainPageParser.parserMainPage(result));

            nearlyAdapter = new MainContentAdapter(MainActivity.this, comicCollectionList.get(0));
            hotAdapter = new MainContentAdapter(MainActivity.this, comicCollectionList.get(1));
            beautifulAdapter = new MainContentAdapter(MainActivity.this, comicCollectionList.get(2));
            loveAdapter = new MainContentAdapter(MainActivity.this, comicCollectionList.get(3));
            finishAdapter = new MainContentAdapter(MainActivity.this, comicCollectionList.get(4));

            rv_nearly.setAdapter(nearlyAdapter);
            rv_hot.setAdapter(hotAdapter);
            rv_beautiful.setAdapter(beautifulAdapter);
            rv_love.setAdapter(loveAdapter);
            rv_finish.setAdapter(finishAdapter);
        }
    }

    @Override
    public void onError(String error, int requestId) {
        if (requestId == QueryIdTable.MAIN_PAGE_DATA) {
            rl_loading.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "MainPage Query Error!", Toast.LENGTH_SHORT).show();
        }
    }
}