package dushkof.seaWars.dao.impl;

import dushkof.seaWars.dao.GameDao;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class GameDaoImpl implements GameDao {
    private DriverManagerDataSource dataSource;

    @Override
    public String init() {
        try {
            // создаем таблицы и возвращаем ОК в случае успеха
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.execute("CREATE TABLE users( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME CHAR(20) NOT NULL, PASSWORD CHAR(10) NOT NULL);");
            return "OK";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Required
    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DriverManagerDataSource getDataSource() {
        return dataSource;
    }
}
