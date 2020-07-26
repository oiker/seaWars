package dushkof.seaWars.dao;

import dushkof.seaWars.objects.Game;

import java.util.List;

public interface GameDao {

    public String init();

    public void hostJoin(String name);

    public void playerJoin(Integer id, String name);

    public List<Game> foundFreeGames();

}
