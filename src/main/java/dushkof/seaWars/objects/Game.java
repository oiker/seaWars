package dushkof.seaWars.objects;



public class Game {
    private int id;
    private String userHost;
    private String secondUser;
    private int hostField;
    private int joinField;
    private boolean isStarted;
    private String creatingTime;
    private String finishGame;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserHost() {
        return userHost;
    }

    public void setUserHost(String userHost) {
        this.userHost = userHost;
    }

    public String getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(String secondUser) {
        this.secondUser = secondUser;
    }

    public int getJoinField() {
        return joinField;
    }

    public void setJoinField(int joinField) {
        this.joinField = joinField;
    }

    public int getHostField() {
        return hostField;
    }

    public void setHostField(int hostField) {
        this.hostField = hostField;
    }

    public String getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(String creatingTime) {
        this.creatingTime = creatingTime;
    }

    public String getFinishGame() {
        return finishGame;
    }

    public void setFinishGame(String finishGame) {
        this.finishGame = finishGame;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
}
