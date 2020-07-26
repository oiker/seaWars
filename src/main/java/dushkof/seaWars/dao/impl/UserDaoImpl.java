package dushkof.seaWars.dao.impl;

import dushkof.seaWars.dao.UserDao;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.objects.mappers.UserMapper;
import org.assertj.core.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.List;


public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private static final String GET_USERS_QUERY = "SELECT * FROM users WHERE NAME LIKE '%s';";
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
            String password = getJdbcTemplate().queryForObject("SELECT PASSWORD FROM users WHERE NAME LIKE '" + name + "';", String.class);
            LOGGER.info("User " + name + " has password " + password);
            return password;
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public User getUserByName(String name) {
        List<User> users = getJdbcTemplate().query(String.format(GET_USERS_QUERY, name), new UserMapper());
        return users.get(0);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}