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
        game.setHostField(resultSet.getInt("hostfield"));
        game.setJoinField(resultSet.getInt("joinfield"));
        game.setCreatingTime(resultSet.getString("creatingtime"));
        game.setFinishGame(resultSet.getString("finishgame"));
        game.setStarted(resultSet.getBoolean("isstarted"));
        return game;
    }
}
