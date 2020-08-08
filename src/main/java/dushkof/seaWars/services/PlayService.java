package dushkof.seaWars.services;

public interface PlayService {

    String shoot(Long cellId);

    String whoseTurn(String playerName, Long gameId);
}
