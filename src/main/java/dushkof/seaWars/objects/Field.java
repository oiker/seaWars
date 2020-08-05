package dushkof.seaWars.objects;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long gameID;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Cell> cells;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ship> ships;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private boolean isReady;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameID() {
        return gameID;
    }

    public void setGameID(Long gameID) {
        this.gameID = gameID;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
