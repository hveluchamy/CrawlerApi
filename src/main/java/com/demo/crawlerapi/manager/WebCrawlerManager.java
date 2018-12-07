package com.demo.crawlerapi.manager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WebCrawlerManager {
    private static final int MAX_DEPTH = 5;
    private HashSet<String> links;

    public WebCrawlerManager() {
        links = new HashSet<>();
    }

    public void getPageLinks(String URL, int depth) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {

                System.out.println(">> Depth: " + depth + " [" + URL + "]");
                try {
                    links.add(URL);

                    Document document = Jsoup.connect(URL).get();
                    Elements linksOnPage = document.select("a[href]");

                    depth++;
                    for (Element page : linksOnPage) {
                        getPageLinks(page.attr("abs:href"), depth);

                    }
                } catch (IOException e) {
                    System.err.println("For '" + URL + "': " + e.getMessage());
                }

        }
    }


    public static void main(String[] args) {
        WebCrawlerManager webCrawlerManager = new WebCrawlerManager();
        webCrawlerManager.getPageLinks("http://www.espncricinfo.com/cricket/scores/series/18693/india-in-aus-2018-19", 0);
    }
}


