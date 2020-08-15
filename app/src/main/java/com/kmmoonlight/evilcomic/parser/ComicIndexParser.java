package com.kmmoonlight.evilcomic.parser;

import android.util.Log;

import com.kmmoonlight.evilcomic.entity.ComicIndex;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class ComicIndexParser {


    public static ComicIndex parseComicIndex(String content) {

        Document doc = Jsoup.parse(content);

        Element element_main = doc.getElementsByClass("detail-main").get(0);

        //图片
        String image = element_main.getElementsByClass("detail-main-cover")
                .get(0)
                .getElementsByTag("img")
                .get(0)
                .attr("data-original");

        //信息
        Element info = element_main.getElementsByClass("detail-main-info")
                .get(0);

        String comicTitle = info.getElementsByTag("p").get(0).text();
        String comicAuthor = info.getElementsByTag("p").get(2).text();
        String comicArea = info.getElementsByTag("p").get(3).text();
        String comicState = info.getElementsByTag("p").get(4).text();

        //简介
        String comicIntroduce = doc.getElementsByClass("detail-desc").get(0).text();


        //章节列表
        Elements chapterElement = doc.getElementById("detail-list-select").getElementsByTag("li");

        List<ComicIndex.Chapter> chapterList = new ArrayList<>();

        for (Element e : chapterElement) {
            Element chapterLink = e.getElementsByTag("a").get(0);
            ComicIndex.Chapter chapter = new ComicIndex.Chapter(chapterLink.text(), chapterLink.attr("href"));
            chapterList.add(chapter);
        }


        return new ComicIndex(comicTitle, image, comicAuthor, comicArea, comicState, comicIntroduce, chapterList);
    }


}
