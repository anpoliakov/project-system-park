package models.entity;

public class Participants {
    private int id;
    private int fromParticipantId;
    private int forParticipantId;

    public Participants(int id, int fromParticipant, int forParticipant) {
        this.id = id;
        this.fromParticipantId = fromParticipant;
        this.forParticipantId = forParticipant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromParticipantId() {
        return fromParticipantId;
    }

    public void setFromParticipantId(int fromParticipantId) {
        this.fromParticipantId = fromParticipantId;
    }

    public int getForParticipantId() {
        return forParticipantId;
    }

    public void setForParticipantId(int forParticipantId) {
        this.forParticipantId = forParticipantId;
    }
}
