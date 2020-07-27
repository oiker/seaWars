package dushkof.seaWars.objects;

import java.util.List;

public class Ship {
    private int id;
    private int numberOfCells;
    private List<Cell> allCells;
    private List<Cell> woundedCells;
    private boolean isAlive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public void setNumberOfCells(int numberOfCells) {
        this.numberOfCells = numberOfCells;
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
