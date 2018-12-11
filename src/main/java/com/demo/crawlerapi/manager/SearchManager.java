package com.demo.crawlerapi.manager;

import com.demo.crawlerapi.Entity.Fixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchManager {
    @Autowired
    private JdbcTemplateManager jdbcTemplateManager;

    public List<String> getAllFixtures(){
        return jdbcTemplateManager.getAllFixtures();
    }

    public List<String> getAllResults() {
        return jdbcTemplateManager.getAllResults();
    }

    public List<String> getResultBySeriesId(String seriesId) {
        return jdbcTemplateManager.getResultsBySeriesId(seriesId);
    }

    public List<String> getResultsByGameId(String gameId){
        return jdbcTemplateManager.getResultsByGameId(gameId);
    }
}
