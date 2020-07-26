package dushkof.seaWars.dao.impl;

import dushkof.seaWars.dao.GameDao;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.mappers.GameMapper;
import dushkof.seaWars.services.impl.GameServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class GameDaoImpl implements GameDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameDaoImpl.class);
    private static final String CREATE_USER_TABLE = "CREATE TABLE users( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME CHAR(20) NOT NULL UNIQUE , PASSWORD CHAR(10) NOT NULL);";
    private static final String CREATE_GAME_TABLE = "CREATE TABLE game( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, USERHOST CHAR(20), SECONDUSER CHAR(20), FIELDID INT, ISSTARTED BOOL);";
    private static final String CREATE_FIELD_TABLE = "CREATE TABLE field( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, GAME INT, CELLS INT );";
    private static final String CREATE_CELLS_TABLE = "CREATE TABLE cells( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, X INT , Y INT , FIELDID INT , STATUS INT, CHECKED BOOL);";
    private static final String FOUND_GAMES_REQUEST = "SELECT * FROM game WHERE SECONDUSER IS NULL;";
    private static final String HOST_PLAYER_JOIN_TABLE = "INSERT game ( USERHOST ) VALUES('%s');";
    private static final String SECOND_PLAYER_JOIN_TABLE = "UPDATE game SET SECONDUSER = '%s' WHERE ID = %s;";
    private JdbcTemplate jdbcTemplate;

    @Override
    public String init() {
        try {
            // создаем таблицы и возвращаем ОК в случае успеха
            LOGGER.info("Start DB initialization");
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
    public void hostJoin(String name) {
        getJdbcTemplate().execute(String.format(HOST_PLAYER_JOIN_TABLE,name));
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
