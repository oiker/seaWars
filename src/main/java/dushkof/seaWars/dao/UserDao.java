package dushkof.seaWars.dao;

import dushkof.seaWars.objects.User;

public interface UserDao {

    /**
     * Save new user in DB.
     *  @param name User name
     *
     * @param password Password.
     * @return
     */

    public String createUser(String name, String password);

    public String getUserPassword(String name);

    User getUserByName(String name);
}
