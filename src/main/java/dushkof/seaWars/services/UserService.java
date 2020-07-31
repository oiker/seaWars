package dushkof.seaWars.services;

import dushkof.seaWars.objects.User;

public interface UserService {
    public String sayHi();

    public String createUser(String name, String password);

    public String checkUserPassword(String name, String password);

}
