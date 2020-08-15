package com.kmmoonlight.evilcomic.parser;


import com.kmmoonlight.evilcomic.entity.MainPageComic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MainPageParser {

    public static List<List<MainPageComic>> parserMainPage(String content) {

        List<MainPageComic> nearlyUpdateList = new ArrayList<>();
        List<MainPageComic> hotList = new ArrayList<>();
        List<MainPageComic> beautifulList = new ArrayList<>();
        List<MainPageComic> loveList = new ArrayList<>();
        List<MainPageComic> finishList = new ArrayList<>();

        List<List<MainPageComic>> comicCollection = new ArrayList<>();
        comicCollection.add(nearlyUpdateList);
        comicCollection.add(hotList);
        comicCollection.add(beautifulList);
        comicCollection.add(loveList);
        comicCollection.add(finishList);


        //Start Parser
        Document doc = Jsoup.parse(content);

        //分类数据
        Elements elementsList = doc.getElementsByClass("manga-list-2");
        for(int i = 0; i < elementsList.size(); i++) {
            Element element = elementsList.get(i);
            Elements comicLiList = element.getElementsByTag("li");
            for (Element e: comicLiList) {
                Element comic = e.getElementsByTag("div").get(0).getElementsByTag("a").get(0);
                String comicUrl =  comic.attr("href");
                String comicName = comic.attr("title");
                String comicImage = comic.getElementsByTag("img").get(0).attr("data-original");
                String comicInfo = e.getElementsByTag("p").get(1).getElementsByTag("a").get(0).text();
                MainPageComic mainPageComic = new MainPageComic(comicName, comicInfo, comicUrl, comicImage);
                comicCollection.get(i).add(mainPageComic);
            }

        }

        return comicCollection;
    }


    public static List<MainPageComic> parseRepoComicList(String content) {
        List<MainPageComic> comicList = new ArrayList<>();

        Document doc = Jsoup.parse(content);

        Element elementsList = doc.getElementsByClass("manga-list-2").get(0);

        Elements liList = elementsList.getElementsByTag("li");

        for (Element e: liList) {
            Element comic = e.getElementsByTag("div").get(0).getElementsByTag("a").get(0);
            String comicUrl =  comic.attr("href");
            String comicImage = comic.getElementsByTag("img").get(0).attr("data-original");
            String comicName = e.getElementsByTag("p").get(0).getElementsByTag("a").get(0).text();
            String comicInfo = e.getElementsByTag("p").get(1).getElementsByTag("a").get(0).text();
            MainPageComic mainPageComic = new MainPageComic(comicName, comicInfo, comicUrl, comicImage);
            comicList.add(mainPageComic);
        }

        return comicList;
    }


}
