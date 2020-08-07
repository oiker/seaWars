package dushkof.seaWars.objects;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@ToString(of = {"id" })
@EqualsAndHashCode(of = {"id"})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User userHost;
    @OneToOne
    private User secondUser;
    @OneToOne
    private Field hostField;
    @OneToOne
    private Field joinField;
    private Boolean isStarted;
    private Boolean isFinished;
    private Date creatingTime;
    private Date finishGame;
    private String Winner;

    public String getWinner() {return Winner;}

    public void setWinner(String winner) {Winner = winner;}



    protected Game() {}

    @PrePersist
    protected void onCreate(){
        creatingTime = new Date();
    }

    public Game(User userHost) {
        this.userHost = userHost;
    }

    public Game(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserHost() {
        return userHost;
    }

    public void setUserHost(User userHost) {
        this.userHost = userHost;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public Field getHostField() {
        return hostField;
    }

    public void setHostField(Field hostField) {
        this.hostField = hostField;
    }

    public Field getJoinField() {
        return joinField;
    }

    public void setJoinField(Field joinField) {
        this.joinField = joinField;
    }

    public Boolean getStarted() {
        return isStarted;
    }

    public void setStarted(Boolean started) {
        isStarted = started;
    }

    public Date getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(Date creatingTime) {
        this.creatingTime = creatingTime;
    }

    public Date getFinishGame() {
        return finishGame;
    }

    public void setFinishGame(Date finishGame) {
        this.finishGame = finishGame;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }
}
