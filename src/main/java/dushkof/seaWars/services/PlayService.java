package dushkof.seaWars.services;

import dushkof.seaWars.objects.Game;

public interface PlayService {

    String checkCellStatus(Long cellId);

    Game surrenderUser(String name, Long gameId);
}
