package dushkof.seaWars.dao;

public interface UserDao {

    /**
     * Save new user in DB.
     *
     * @param name User name
     *
     * @param password Password.
     */
    public void addUser(String name, String password);
}
