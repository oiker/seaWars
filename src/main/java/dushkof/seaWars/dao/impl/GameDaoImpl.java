package dushkof.seaWars.dao.impl;

import dushkof.seaWars.dao.GameDao;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.mappers.GameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GameDaoImpl implements GameDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameDaoImpl.class);
    private static final String DROP_ALL_TABLES = "DROP TABLE IF EXISTS users, cells, game, field, ship;";
    private static final String CREATE_SHIP_TABLE = "CREATE TABLE ship( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NUMBEROFCELLS INT, ALLCELLS INT, WOUNDEDCELLS INT, ISALIVE BOOL);";
    private static final String CREATE_USER_TABLE = "CREATE TABLE users( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME CHAR(20) NOT NULL UNIQUE , PASSWORD CHAR(10) NOT NULL, REGISTRATIONTIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
    private static final String CREATE_GAME_TABLE = "CREATE TABLE game( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, USERHOST CHAR(20), SECONDUSER CHAR(20), HOSTFIELD INT, JOINFIELD INT , ISSTARTED BOOL, CREATINGTIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP, FINISHGAME TIMESTAMP );";
    private static final String CREATE_FIELD_TABLE = "CREATE TABLE field( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, GAME INT);";
    private static final String CREATE_CELLS_TABLE = "CREATE TABLE cells( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, X INT , Y INT , FIELDID INT , STATUS INT, CHECKED BOOL);";
    private static final String FOUND_GAMES_REQUEST = "SELECT * FROM game WHERE SECONDUSER IS NULL;";
    private static final String HOST_PLAYER_JOIN_TABLE = "INSERT game ( USERHOST, ISSTARTED ) VALUES('%s', false);";
    private static final String SECOND_PLAYER_JOIN_TABLE = "UPDATE game SET SECONDUSER = '%s' WHERE ID = %s;";
    private static final String DELETE_NOT_VALID_GAMES = "DELETE FROM game WHERE USERHOST = '%s' AND SECONDUSER IS NULL;";
    private JdbcTemplate jdbcTemplate;

    @Override
    public String init() {
        try {
            // создаем таблицы и возвращаем ОК в случае успеха
            LOGGER.info("Start DB initialization");
            getJdbcTemplate().execute(DROP_ALL_TABLES);
            getJdbcTemplate().execute(CREATE_SHIP_TABLE);
            getJdbcTemplate().execute(CREATE_USER_TABLE);
            getJdbcTemplate().execute(CREATE_GAME_TABLE);
            getJdbcTemplate().execute(CREATE_FIELD_TABLE);
            getJdbcTemplate().execute(CREATE_CELLS_TABLE);
            LOGGER.info("DB initialization is success");
            return "OK";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return "NOK";
        }
    }

    @Override
    public String hostJoin(String name) {
        try {
            LOGGER.info("Creating game for " + name);
            getJdbcTemplate().execute(String.format(DELETE_NOT_VALID_GAMES, name));
            getJdbcTemplate().execute(String.format(HOST_PLAYER_JOIN_TABLE, name));
            LOGGER.info("Creating game is success for " + name);
            return "OK";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return "NOK";
        }
    }

    public boolean checkIfGameIsNotFinished(String name){
        try{
            LOGGER.info("Start checking if game is not finished for " + name);
            Boolean checkIfGameIsNotFinished = getJdbcTemplate().queryForObject("SELECT ISSTARTED FROM game WHERE USERHOST LIKE '" + name + "';",Boolean.class);
            LOGGER.info("Finish checking if game is not finished for " + name);
            return checkIfGameIsNotFinished;
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void playerJoin(Integer id, String name) {
        getJdbcTemplate().execute(String.format(SECOND_PLAYER_JOIN_TABLE, name, id));
    }

    @Override
    public List<Game> foundFreeGames() {
        List<Game> games = getJdbcTemplate().query(String.format(FOUND_GAMES_REQUEST), new GameMapper());
        return games;
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



}
