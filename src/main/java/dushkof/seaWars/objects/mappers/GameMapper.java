package dushkof.seaWars.objects.mappers;

import dushkof.seaWars.objects.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet resultSet, int i) throws SQLException {
        Game game = new Game();
        game.setId(resultSet.getInt("id"));
        game.setUserHost(resultSet.getString("userHost"));
        game.setSecondUser(resultSet.getString("secondUser"));
        game.setFieldId(resultSet.getInt("fieldId"));
//        game.setStarted();
        return null;
    }
}
