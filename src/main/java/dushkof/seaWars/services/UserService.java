package dushkof.seaWars.services;

public interface UserService {
    public String sayHi();

    public String createUser(String name, String password);

    public String checkUserPassword(String name, String password);

}
