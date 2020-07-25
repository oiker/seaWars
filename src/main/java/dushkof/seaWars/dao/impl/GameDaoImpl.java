package dushkof.seaWars.dao.impl;

import dushkof.seaWars.dao.GameDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class GameDaoImpl implements GameDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameDaoImpl.class);
    private static final String CREATE_USER_TABLE = "CREATE TABLE users( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME CHAR(20) NOT NULL UNIQUE , PASSWORD CHAR(10) NOT NULL);";
    private JdbcTemplate jdbcTemplate;

    @Override
    public String init() {
        try {
            // создаем таблицы и возвращаем ОК в случае успеха
            LOGGER.info("Start DB initialization");
            getJdbcTemplate().execute(CREATE_USER_TABLE);
            LOGGER.info("DB initialization is success");
            return "OK";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return "NOK";
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
