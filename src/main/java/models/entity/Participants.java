package models.entity;

public class Participants {
    private long id;
    private long fromParticipantId;
    private long forParticipantId;

    public Participants(long id, long fromParticipant, long forParticipant) {
        this.id = id;
        this.fromParticipantId = fromParticipant;
        this.forParticipantId = forParticipant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromParticipantId() {
        return fromParticipantId;
    }

    public void setFromParticipantId(long fromParticipantId) {
        this.fromParticipantId = fromParticipantId;
    }

    public long getForParticipantId() {
        return forParticipantId;
    }

    public void setForParticipantId(long forParticipantId) {
        this.forParticipantId = forParticipantId;
    }
}
