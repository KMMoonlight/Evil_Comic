package com.kmmoonlight.evilcomic.entity;

public class SearchResult {

    private String imageUrl;

    private String searchTitle;

    private String searchDesc;

    private String searchAuthor;

    private String searchState;

    private String searchResultUrl;

    public SearchResult(String imageUrl, String searchTitle, String searchDesc, String searchAuthor, String searchState, String searchResultUrl) {
        this.imageUrl = imageUrl;
        this.searchTitle = searchTitle;
        this.searchDesc = searchDesc;
        this.searchAuthor = searchAuthor;
        this.searchState = searchState;
        this.searchResultUrl = searchResultUrl;
    }

    public String getSearchResultUrl() {
        return searchResultUrl;
    }

    public void setSearchResultUrl(String searchResultUrl) {
        this.searchResultUrl = searchResultUrl;
    }

    public SearchResult() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getSearchDesc() {
        return searchDesc;
    }

    public void setSearchDesc(String searchDesc) {
        this.searchDesc = searchDesc;
    }

    public String getSearchAuthor() {
        return searchAuthor;
    }

    public void setSearchAuthor(String searchAuthor) {
        this.searchAuthor = searchAuthor;
    }

    public String getSearchState() {
        return searchState;
    }

    public void setSearchState(String searchState) {
        this.searchState = searchState;
    }
}
