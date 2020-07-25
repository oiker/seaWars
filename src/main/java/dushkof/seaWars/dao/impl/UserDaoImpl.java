package dushkof.seaWars.dao.impl;

import dushkof.seaWars.Objects.User;
import dushkof.seaWars.dao.UserDao;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Empty;
import org.assertj.core.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.util.StringUtils;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public String getUserPassword(String name) {
        try {
            String user = getJdbcTemplate().queryForObject("SELECT PASSWORD FROM users WHERE NAME LIKE '" + name + "';", String.class);
            LOGGER.info(user);
            return user;
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
            return e.getMessage();
        }
    }



    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}