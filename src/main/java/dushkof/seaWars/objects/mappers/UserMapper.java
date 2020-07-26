package dushkof.seaWars.Objects.mappers;

import dushkof.seaWars.Objects.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
