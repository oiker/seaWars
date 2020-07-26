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
        game.setUserHost(resultSet.getString("userhost"));
        game.setSecondUser(resultSet.getString("seconduser"));
        game.setFieldId(resultSet.getInt("fieldid"));
        //тут надо разобраться как вытягивать булианы из бд
//        game.setStarted(resultSet.getBoolean("started"));
        return game;
    }
}
