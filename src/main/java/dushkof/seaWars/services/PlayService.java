package dushkof.seaWars.services;

public interface PlayService {

    String checkCellStatus(Long cellId);

    String surrenderUser(String name, Long gameId);
}
