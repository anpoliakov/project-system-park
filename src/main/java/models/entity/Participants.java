package models.entity;

public class Participants {
    private int id;
    private Person fromParticipant;
    private Person forParticipant;

    public Participants(int id, Person fromParticipant, Person forParticipant) {
        this.id = id;
        this.fromParticipant = fromParticipant;
        this.forParticipant = forParticipant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getFromParticipant() {
        return fromParticipant;
    }

    public void setFromParticipant(Person fromParticipant) {
        this.fromParticipant = fromParticipant;
    }

    public Person getForParticipant() {
        return forParticipant;
    }

    public void setForParticipant(Person forParticipant) {
        this.forParticipant = forParticipant;
    }
}
