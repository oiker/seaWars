package dushkof.seaWars.services;

public interface GameService {
    // метод выполняет инит базы данных, для первого запуска
    public String init();

    public void startGame();

    public String createGame(String name);

    public String connectSecondUser(Integer id, String name);
}
