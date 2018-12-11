package Mapper;

import com.demo.crawlerapi.Entity.Fixture;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FixtureMapper implements RowMapper<Fixture> {
    @Override
    public Fixture mapRow(ResultSet resultSet, int i) throws SQLException {
        Fixture fixture = new Fixture();
        fixture.setFixtureJson(resultSet.getString("fixture_json"));
        return fixture;
    }
}
