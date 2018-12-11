package com.demo.crawlerapi.Impl;

import com.demo.crawlerapi.Entity.Result;
import com.demo.crawlerapi.manager.JdbcTemplateManager;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WebCrawlerResultsImpl implements WebCrawler {
    public static final String HOME_TEAM_PATH = "li.cscore_item.cscore_item--home>div>div.cscore_score";
    public static final String AWAY_TEAM_PATH = "li.cscore_item.cscore_item--away>div>div.cscore_score";
    public static final String MATCH_NAME_PATH = "div.cscore.final>a>div>div.cscore_info-overview";

    @Autowired
    private JdbcTemplateManager jdbcTemplateManager;

    @Override
    public void processLinkData(String URL) throws IOException, ParseException {
        Gson gson = new Gson();
        String json;

        Document document = Jsoup.connect(URL).get();
        Elements linksOnPage = document.select("#results > ul>li");
        for (Element page : linksOnPage) {
            String url = page.select("a[href]").attr("href");
            Result result = new Result();
            extractGameAndSeriesId(url, result);
            result.setAwayTeamScore( page.select(AWAY_TEAM_PATH).text());
            result.setHomeTeamScore(page.select(HOME_TEAM_PATH).text());
            result.setMatchName(page.select(MATCH_NAME_PATH).text());


            json = gson.toJson(result);
            jdbcTemplateManager.insertResultEntry(json);

        }

        //<div id="results" style="display:none">
    }

    private void extractGameAndSeriesId(String url, Result result) {
        String re1=".*?";	// Non-greedy match on filler
        String re2="(series)";	// Word 1
        String re3=".*?";	// Non-greedy match on filler
        String re4="(\\d+)";	// Integer Number 1
        String re5=".*?";	// Non-greedy match on filler
        String re6="(game)";	// Word 2
        String re7=".*?";	// Non-greedy match on filler
        String re8="(\\d+)";	// Integer Number 2

        Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(url);
        if (m.find())
        {
            String word1=m.group(1);
            String int1=m.group(2);
            String word2=m.group(3);
            String int2=m.group(4);
            result.setSeriesId(int1);
            result.setGameId(int2);

        }
    }
}
