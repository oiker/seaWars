package dushkof.seaWars.dao.impl;

import dushkof.seaWars.dao.UserDao;
import org.assertj.core.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.crypto.spec.PSource;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private JdbcTemplate jdbcTemplate;


    @Override
    public String createUser(String name, String password) {
        try {
        Preconditions.checkArgument(!StringUtils.isEmpty(name), "Argument name cannot be empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(password), "Argument password cannot be empty");

            getJdbcTemplate().execute("INSERT INTO users(NAME , PASSWORD) VALUES('" + name +"' ,'"+ password +"');");
            LOGGER.info("User " + name + " is created");
            return "OK";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return "NOK";

        }
    }

    @Override
    public String checkUser(String name, String password) {
        try {
                getJdbcTemplate().execute("SELECT NAME FROM users WHERE NAME LIKE '" + "name" + "');");
                try{
                    getJdbcTemplate().execute("SELECT PASSWORD FROM users WHERE EXISTS (SELECT * FROM users WHERE NAME = '"+ name +"');" );
                    return "OK";
                }
                catch (Exception e){
                    return "Неправильный пароль";
                }
        }
        catch (Exception c){
            return "Данного пользователя не существует";
                    }

    }



    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}