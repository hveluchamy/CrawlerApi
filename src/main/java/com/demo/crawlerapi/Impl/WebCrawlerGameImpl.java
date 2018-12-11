package com.demo.crawlerapi.Impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebCrawlerGameImpl  implements WebCrawler {

    @Override
    public void processLinkData(String URL) throws IOException {
        System.out.println("processing games for url: " + URL);
        Document document = Jsoup.connect(URL).get();
        System.out.println(document.text());
    }
}
