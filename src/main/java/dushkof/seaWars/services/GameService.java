package dushkof.seaWars.services;

public interface GameService {
    // метод выполняет инит базы данных, для первого запуска
    public String init();

    public void startGame();
}
