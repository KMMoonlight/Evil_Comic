package com.kmmoonlight.evilcomic.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kmmoonlight.evilcomic.R;
import com.kmmoonlight.evilcomic.adapter.SearchResultAdapter;
import com.kmmoonlight.evilcomic.entity.SearchResult;
import com.kmmoonlight.evilcomic.net.Callback;
import com.kmmoonlight.evilcomic.net.NetWorkTask;
import com.kmmoonlight.evilcomic.net.QueryIdTable;
import com.kmmoonlight.evilcomic.parser.SearchResultParser;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener , Callback {

    public ImageView iv_back;

    public EditText et_keywords;

    public TextView tv_search;

    public RelativeLayout rl_loading;

    public RecyclerView rv_search;

    public List<SearchResult> searchResultList;

    public SearchResultAdapter searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        iv_back = findViewById(R.id.iv_back);
        et_keywords = findViewById(R.id.et_keyword);
        tv_search = findViewById(R.id.tv_search);
        rl_loading = findViewById(R.id.rl_loading);
        rv_search = findViewById(R.id.rv_search);

        iv_back.setOnClickListener(this);
        tv_search.setOnClickListener(this);


        searchResultList = new ArrayList<>();

        searchResultAdapter = new SearchResultAdapter(searchResultList, this);

        rv_search.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv_search.setAdapter(searchResultAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

            case R.id.tv_search:
                String keywords = et_keywords.getText().toString();

                if (TextUtils.isEmpty(keywords)) {
                    Toast.makeText(SearchActivity.this, "请输入搜索关键字", Toast.LENGTH_SHORT).show();
                }else {
                    rl_loading.setVisibility(View.VISIBLE);
                    NetWorkTask.SearchComic(keywords, this, QueryIdTable.COMIC_SEARCH_DATA);
                }

                //关闭输入法
                ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                break;
        }
    }

    @Override
    public void onResult(String result, int requestId) {
        rl_loading.setVisibility(View.GONE);
        searchResultList.clear();
        searchResultList.addAll(SearchResultParser.parserSearchResult(result));
        searchResultAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error, int requestId) {
        rl_loading.setVisibility(View.GONE);
        Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}