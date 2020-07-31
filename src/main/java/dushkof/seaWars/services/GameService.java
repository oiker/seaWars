package dushkof.seaWars.services;

import dushkof.seaWars.objects.Field;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;

import java.util.List;

public interface GameService {
    // метод выполняет инит базы данных, для первого запуска
    public String init();

    public void startGame();

    public String createGame(String name);

    public String connectSecondUser(Integer id, String name);

    List<Game> foundNewGames();
}
