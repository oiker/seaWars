package dushkof.seaWars.dao;

public interface GameDao {

    public String init();

    public void hostJoin(String name);

    public void playerJoin(int id, String name);

}
