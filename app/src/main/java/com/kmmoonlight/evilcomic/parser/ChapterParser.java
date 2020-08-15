package com.kmmoonlight.evilcomic.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ChapterParser {

    public static List<String> parseChapter(String content) {

        Document doc = Jsoup.parse(content);

        Element imgWrapper = doc.getElementById("cp_img");

        Elements imgList = imgWrapper.getElementsByTag("img");

        List<String> resultList = new ArrayList<>();

        for (Element e : imgList) {
            String imageUrl = e.attr("data-original");
            resultList.add(imageUrl);
        }

        return resultList;
    }






}
