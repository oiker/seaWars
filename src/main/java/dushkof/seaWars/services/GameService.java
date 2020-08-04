package dushkof.seaWars.services;

import dushkof.seaWars.objects.Field;
import dushkof.seaWars.objects.Game;

import java.util.List;

public interface GameService {

    Field createField(String name, Long gameId);

    void startGame(Long gameId);

    String createGame(String name);

    String connectSecondUser(Long id, String name);

    List<Game> foundNewGames();

    String leaveGame(String name, Long gameId);

    Game getGameById(Long gameId);
}
