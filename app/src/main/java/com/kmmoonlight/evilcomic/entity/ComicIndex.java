package com.kmmoonlight.evilcomic.entity;

import java.util.List;

public class ComicIndex {

    private String comicTitle;

    private String comicImage;

    private String comicAuthor;

    private String comicArea;

    private String comicState;

    private String comicIntroduce;

    private List<Chapter> comicChapterList;

    public ComicIndex(String comicTitle, String comicImage, String comicAuthor, String comicArea, String comicState, String comicIntroduce, List<Chapter> comicChapterList) {
        this.comicTitle = comicTitle;
        this.comicImage = comicImage;
        this.comicAuthor = comicAuthor;
        this.comicArea = comicArea;
        this.comicState = comicState;
        this.comicIntroduce = comicIntroduce;
        this.comicChapterList = comicChapterList;
    }

    public String getComicTitle() {
        return comicTitle;
    }

    public void setComicTitle(String comicTitle) {
        this.comicTitle = comicTitle;
    }

    public String getComicImage() {
        return comicImage;
    }

    public void setComicImage(String comicImage) {
        this.comicImage = comicImage;
    }

    public String getComicAuthor() {
        return comicAuthor;
    }

    public void setComicAuthor(String comicAuthor) {
        this.comicAuthor = comicAuthor;
    }

    public String getComicArea() {
        return comicArea;
    }

    public void setComicArea(String comicArea) {
        this.comicArea = comicArea;
    }

    public String getComicState() {
        return comicState;
    }

    public void setComicState(String comicState) {
        this.comicState = comicState;
    }

    public String getComicIntroduce() {
        return comicIntroduce;
    }

    public void setComicIntroduce(String comicIntroduce) {
        this.comicIntroduce = comicIntroduce;
    }

    public List<Chapter> getComicChapterList() {
        return comicChapterList;
    }

    public void setComicChapterList(List<Chapter> comicChapterList) {
        this.comicChapterList = comicChapterList;
    }

    public static class Chapter {

        private String chapterTitle;

        private String chapterUrl;

        public Chapter(String chapterTitle, String chapterUrl) {
            this.chapterTitle = chapterTitle;
            this.chapterUrl = chapterUrl;
        }

        public String getChapterTitle() {
            return chapterTitle;
        }

        public void setChapterTitle(String chapterTitle) {
            this.chapterTitle = chapterTitle;
        }

        public String getChapterUrl() {
            return chapterUrl;
        }

        public void setChapterUrl(String chapterUrl) {
            this.chapterUrl = chapterUrl;
        }
    }

}
