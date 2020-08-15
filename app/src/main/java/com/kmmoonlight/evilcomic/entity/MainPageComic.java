package com.kmmoonlight.evilcomic.entity;

public class MainPageComic {

    private String ComicName;

    private String ComicInfo;

    private String ComicUrl;

    private String ComicImage;

    public MainPageComic() {
    }

    public MainPageComic(String comicName, String comicInfo, String comicUrl, String comicImage) {
        ComicName = comicName;
        ComicInfo = comicInfo;
        ComicUrl = comicUrl;
        ComicImage = comicImage;
    }

    public String getComicName() {
        return ComicName;
    }

    public void setComicName(String comicName) {
        ComicName = comicName;
    }

    public String getComicInfo() {
        return ComicInfo;
    }

    public void setComicInfo(String comicInfo) {
        ComicInfo = comicInfo;
    }

    public String getComicUrl() {
        return ComicUrl;
    }

    public void setComicUrl(String comicUrl) {
        ComicUrl = comicUrl;
    }

    public String getComicImage() {
        return ComicImage;
    }

    public void setComicImage(String comicImage) {
        ComicImage = comicImage;
    }
}
