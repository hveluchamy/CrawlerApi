package com.demo.crawlerapi.manager;

import com.demo.crawlerapi.Impl.WebCrawler;
import com.demo.crawlerapi.Impl.WebCrawlerFixtureImpl;
import com.demo.crawlerapi.Impl.WebCrawlerGameImpl;
import com.demo.crawlerapi.Impl.WebCrawlerResultsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

@Service
public class WebCrawlerManager {

    //private static final int MAX_DEPTH = 25;
    private HashSet<String> links;

    private HashSet<String> fixtureLinks;
    private HashSet<String> resultLinks;

    @Value("${depth.value}")
    private int MAX_DEPTH;


    @Autowired
    private WebCrawlerResultsImpl webCrawlerResults;

    @Autowired
    private WebCrawlerFixtureImpl webCrawlerFixture;



    public WebCrawlerManager() {
        links = new HashSet<>();
        fixtureLinks = new HashSet<>();
        resultLinks = new HashSet<>();
    }

    static boolean processedFixture = false;

    public void getPageLinks(String URL, int depth) throws ParseException {
        String text;
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {

         //   System.out.println(">> Depth: " + depth + " [" + URL + "]");
            try {
                links.add(URL);

                Document document = Jsoup.connect(URL).get();

                Elements linksOnPage = document.select("a[href]");
                depth++;
                for (Element page : linksOnPage) {
                    //18693/game
                    if(((page.attr("abs:href").toLowerCase().indexOf("id/18693") > 1) ||
                            (page.attr("abs:href").toLowerCase().indexOf("18693/game") > 1) ||
                            (page.attr("abs:href").toLowerCase().indexOf("series/18693") > 1)) &&
                            isNotRejectedUrls(page)) {

                        WebCrawler webCrawler;

                        if(page.attr("abs:href").toLowerCase().indexOf("series") > 1){

                           // processLinkData(document);
                          //  System.out.println("processing series here itself");


                        }

                        if(page.attr("abs:href").toLowerCase().indexOf("fixture") > 1 && !processedFixture){
                          //  webCrawler = new WebCrawlerFixtureImpl();
                            webCrawler = webCrawlerFixture;
                            String fixtureLink = page.attr("abs:href").toLowerCase();
                            if(!fixtureLinks.contains(fixtureLink)){
                                webCrawler.processLinkData(fixtureLink);
                                fixtureLinks.add(fixtureLink);
                                processedFixture = true;
                            }

                        }

                        if(page.attr("abs:href").toLowerCase().indexOf("game") > 1){
                            webCrawler = new WebCrawlerGameImpl();
                           // webCrawler.processLinkData(page.attr("abs:href").toLowerCase());
                        }

                        if(page.attr("abs:href").toLowerCase().indexOf("result") > 1){
                           // System.out.println("processing results");
                           // webCrawler = new WebCrawlerResultsImpl();
                            webCrawler = webCrawlerResults;
                            String resultLink = page.attr("abs:href").toLowerCase();
                            if(!resultLinks.contains(resultLink)){
                                webCrawler.processLinkData(resultLink);
                                resultLinks.add(resultLink);
                            }

                        }
                        getPageLinks(page.attr("abs:href"), depth);
                    }


                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }

        }
    }



    private boolean isNotRejectedUrls(Element page) {
        return !(page.attr("abs:href").toLowerCase().indexOf("facebook") > 1) &&
                !(page.attr("abs:href").toLowerCase().indexOf("coverage") > 1) &&
                !(page.attr("abs:href").toLowerCase().indexOf("report") > 1) &&
                !(page.attr("abs:href").toLowerCase().indexOf("commentary") > 1)&&
                !(page.attr("abs:href").toLowerCase().indexOf("video") > 1)&&
                !(page.attr("abs:href").toLowerCase().indexOf("preview") > 1);
    }

    public static void main(String[] args) throws ParseException {
        WebCrawlerManager webCrawlerManager = new WebCrawlerManager();
        webCrawlerManager.getPageLinks("http://www.espncricinfo.com/cricket/scores/series/18693/india-in-aus-2018-19", 0);
    }
}
