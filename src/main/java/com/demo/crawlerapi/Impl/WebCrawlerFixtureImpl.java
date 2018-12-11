package com.demo.crawlerapi.Impl;

import com.demo.crawlerapi.Entity.Fixture;
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

@Service
public class WebCrawlerFixtureImpl   implements WebCrawler {

    public static final String HOME_TEAM_PATH = "li.cscore_item--home > div.cscore_team > span > span.cscore_name--short";
    public static final String AWAY_TEAM_PATH = "li.cscore_item--away > div.cscore_team > span > span.cscore_name--short";

    @Autowired
    private JdbcTemplateManager jdbcTemplateManager;

    @Override
    public void processLinkData(String URL) throws IOException, ParseException {
        Gson gson = new Gson();
        String json;

        Document document = Jsoup.connect(URL).get();

        Elements linksOnPage = document.select("#fixtures > ul>li");
        for (Element page : linksOnPage) {

            Fixture fixture = new Fixture();
            String matchDate = page.getElementsByClass("cscore_date-time").attr("data-date");

            fixture.setFixtureDateTime(matchDate);
            fixture.setHomeTeam( page.select(HOME_TEAM_PATH).text());
            fixture.setAwayTeam( page.select(AWAY_TEAM_PATH).text());

            json = gson.toJson(fixture);
            jdbcTemplateManager.insertFixtureEntry(json);
        }


    }
}
