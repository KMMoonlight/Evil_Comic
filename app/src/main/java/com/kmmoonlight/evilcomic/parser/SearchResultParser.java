package com.kmmoonlight.evilcomic.parser;

import com.kmmoonlight.evilcomic.entity.SearchResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SearchResultParser {

    public static List<SearchResult>  parserSearchResult(String content) {

        List<SearchResult> searchResultList = new ArrayList<>();

        Document doc = Jsoup.parse(content);

        Elements resultSets = doc.getElementsByClass("book-list").get(0).getElementsByTag("li");

        for (Element e: resultSets) {
            String imageUrl = e.getElementsByClass("book-list-cover")
                    .get(0)
                    .getElementsByTag("img")
                    .get(0)
                    .attr("data-original");

            Element bookInfo = e.getElementsByClass("book-list-info")
                    .get(0)
                    .getElementsByTag("a")
                    .get(0);

            String comicUrl = bookInfo.attr("href");

            String comicTitle = bookInfo.getElementsByTag("p").get(0).text();
            String comicDesc = bookInfo.getElementsByTag("p").get(1).text();

            Element comicInfo = e.getElementsByClass("book-list-info")
                    .get(0)
                    .getElementsByClass("book-list-info-bottom")
                    .get(0);

            String comicAuthor = comicInfo.getElementsByTag("span").get(0).text();
            String comicState = comicInfo.getElementsByTag("span").get(1).text();

            SearchResult searchResult = new SearchResult(imageUrl, comicTitle, comicDesc, comicAuthor, comicState, comicUrl);

            searchResultList.add(searchResult);
        }

        return searchResultList;
    }

}
