package dushkof.seaWars.services;

public interface PlayService {

    String surrenderUser(String name, Long gameId);

    String shoot(Long cellId);

    String whoseTurn(String playerName, Long gameId);
}
