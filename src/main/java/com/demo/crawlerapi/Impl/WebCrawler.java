package com.demo.crawlerapi.Impl;

import java.io.IOException;
import java.text.ParseException;

public interface WebCrawler {
     //void getPageLinks(String URL, int depth);
    //void processLinkData()
    void processLinkData(String URL) throws IOException, ParseException;
}
