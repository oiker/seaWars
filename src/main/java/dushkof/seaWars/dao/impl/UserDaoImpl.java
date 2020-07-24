package dushkof.seaWars.dao.impl;

import dushkof.seaWars.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class UserDaoImpl implements UserDao {
    private DriverManagerDataSource dataSource;


    @Override
    public String createUser(String name, String password) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.execute("INSERT INTO users(NAME , PASSWORD) VALUES('" + name +"' ,'"+ password +"');");
            return "OK";
        } catch (Exception e) {
            return e.getMessage();

        }
    }

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }
}