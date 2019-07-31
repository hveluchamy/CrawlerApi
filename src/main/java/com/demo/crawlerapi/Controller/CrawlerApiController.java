package com.demo.crawlerapi.Controller;

import com.demo.crawlerapi.manager.SearchManager;
import com.demo.crawlerapi.manager.WebCrawlerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@RequestMapping("/CrawlerApi")
@RestController
public class CrawlerApiController {
    @Autowired
    private WebCrawlerManager webCrawler;

    @Autowired
    private SearchManager searchManager;

    @RequestMapping(value = "/sampleApi", method = RequestMethod.GET)
    public String getSampleApi(HttpServletResponse response) {
        searchManager.getUserByEmailId("blah");
        return "test test";
    }

    @RequestMapping(value = "/setupdata", method = RequestMethod.GET)
    public String setupdata(HttpServletResponse response) throws ParseException {
        webCrawler.getPageLinks("http://www.espncricinfo.com/cricket/scores/series/18693/india-in-aus-2018-19", 0);
        return "setup complete";
    }

    @RequestMapping(value = "/fixtures", method = RequestMethod.GET, produces = "application/json")
    public List<String> getAllFixtures(HttpServletResponse response) {
        return searchManager.getAllFixtures();
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET, produces = "application/json")
    public List<String> getAllResults(HttpServletResponse response) {
        return searchManager.getAllResults();
    }

    @RequestMapping(value = "/resultsBySeries", method = RequestMethod.GET, produces = "application/json")
    public List<String> getResultsBySeries(@RequestParam("seriesId") String seriesId) {
        return searchManager.getResultBySeriesId(seriesId);
    }

    @RequestMapping(value = "/resultsByGame/{gameId}", method = RequestMethod.GET, produces = "application/json")
    public List<String> getResultsByGames(@PathVariable("gameId") String gameId) {
        return searchManager.getResultsByGameId(gameId);
    }


}
