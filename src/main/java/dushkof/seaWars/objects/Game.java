package dushkof.seaWars.objects;


import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    private Date creatingTime;
    private Date finishGame;

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
}
