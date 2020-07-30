package dushkof.seaWars.objects;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@ToString(of = {"id", "numberOfCells"})
@EqualsAndHashCode(of = {"id"})
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer numberOfCells;
    @ManyToOne
    private Field field;
    @OneToMany
    private List<Cell> allCells;
    @OneToMany
    private List<Cell> woundedCells;
    private boolean isAlive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfCells() {
        return numberOfCells;
    }

    public void setNumberOfCells(Integer numberOfCells) {
        this.numberOfCells = numberOfCells;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public List<Cell> getAllCells() {
        return allCells;
    }

    public void setAllCells(List<Cell> allCells) {
        this.allCells = allCells;
    }

    public List<Cell> getWoundedCells() {
        return woundedCells;
    }

    public void setWoundedCells(List<Cell> woundedCells) {
        this.woundedCells = woundedCells;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
