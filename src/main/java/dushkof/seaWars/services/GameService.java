package dushkof.seaWars.services;

import dushkof.seaWars.objects.Field;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;

import java.util.List;

public interface GameService {
    // метод выполняет инит базы данных, для первого запуска
    String init();

    void startGame();

    String createGame(String name);

    String connectSecondUser(Long id, String name);

    List<Game> foundNewGames();
}
