package com.demo.crawlerapi.manager;


import Mapper.FixtureMapper;
import com.demo.crawlerapi.Entity.Fixture;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTemplateManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertResultEntry(String json){
        JSONObject jsonObject = new JSONObject(json);
        jdbcTemplate.update("INSERT INTO results (result_json) values (?::JSON) ", json);
    }

    public void insertFixtureEntry(String json){
        JSONObject jsonObject = new JSONObject(json);
        jdbcTemplate.update("INSERT INTO fixtures (fixture_json) values (?::JSON) ", json);
    }

    public List<String> getAllFixtures(){
        //jdbcTemplate.
        List<String> fixtureList = jdbcTemplate.queryForList("Select fixture_json from fixtures", String.class);
        return fixtureList;
    }


    public List<String> getAllResults() {
        List<String> resultList = jdbcTemplate.queryForList("Select result_json from results", String.class);
        return resultList;
    }

    public List<String> getResultsBySeriesId(String seriesId) {
        List<String> resultList = jdbcTemplate.queryForList("select result_json from results where result_json ->> 'seriesId' = ?",  String.class, seriesId);
        return resultList;
    }

    public List<String> getResultsByGameId(String gameId) {
        List<String> resultList = jdbcTemplate.queryForList("select result_json from results where result_json ->> 'gameId' = ?",  String.class, gameId);
        return resultList;
    }
}
