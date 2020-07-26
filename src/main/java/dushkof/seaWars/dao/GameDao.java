package dushkof.seaWars.dao;

public interface GameDao {

    public String init();

    public void hostJoin(String name);

    public void playerJoin(Integer id, String name);

}
