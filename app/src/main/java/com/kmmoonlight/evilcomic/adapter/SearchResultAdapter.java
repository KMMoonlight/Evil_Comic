package com.kmmoonlight.evilcomic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kmmoonlight.evilcomic.R;
import com.kmmoonlight.evilcomic.entity.SearchResult;
import com.kmmoonlight.evilcomic.page.IndexActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> implements View.OnClickListener {

    public List<SearchResult> searchResultList;

    public Context context;

    public LayoutInflater layoutInflater;

    public SearchResultAdapter(List<SearchResult> searchResultList, Context context) {
        this.searchResultList = searchResultList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = layoutInflater.inflate(R.layout.search_item_layout, parent, false);
        return new SearchResultViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        Glide.with(context)
                .load(searchResultList.get(position).getImageUrl())
                .fitCenter()
                .into(holder.iv_comic_image);

        holder.tv_comic_title.setText(searchResultList.get(position).getSearchTitle());
        holder.tv_comic_desc.setText(searchResultList.get(position).getSearchDesc());
        holder.tv_comic_author.setText(searchResultList.get(position).getSearchAuthor());
        holder.tv_comic_state.setText(searchResultList.get(position).getSearchState());

        holder.card_comic.setOnClickListener(this);
        holder.card_comic.setTag(searchResultList.get(position));
    }

    @Override
    public int getItemCount() {
        return searchResultList.size();
    }

    @Override
    public void onClick(View view) {
        String comicUrl = ((SearchResult)view.getTag()).getSearchResultUrl();
        Intent indexIntent = new Intent(context, IndexActivity.class);
        indexIntent.putExtra("comicUrl", comicUrl);
        context.startActivity(indexIntent);
    }

    public static class SearchResultViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_comic_image;

        TextView tv_comic_title;

        TextView tv_comic_desc;

        TextView tv_comic_author;

        TextView tv_comic_state;

        CardView card_comic;

        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_comic_image = itemView.findViewById(R.id.iv_comic_image);
            tv_comic_title = itemView.findViewById(R.id.tv_comic_title);
            tv_comic_desc = itemView.findViewById(R.id.tv_comic_desc);
            tv_comic_author = itemView.findViewById(R.id.tv_comic_author);
            tv_comic_state = itemView.findViewById(R.id.tv_comic_state);
            card_comic = itemView.findViewById(R.id.card_comic);
        }
    }


}
