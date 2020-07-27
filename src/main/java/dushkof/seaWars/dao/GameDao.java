package dushkof.seaWars.dao;

import dushkof.seaWars.objects.Game;

import java.util.List;

public interface GameDao {

    String init();

    String hostJoin(String name);

    boolean checkIfGameIsNotFinished(String name);

    void playerJoin(Integer id, String name);

    List<Game> foundFreeGames();

}
