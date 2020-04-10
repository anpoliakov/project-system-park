package models.entity;

public class Task {
    private int id;
    private Action action;
    private Participants participants;
    private Plant plant;
    private boolean status;

    public Task(int id, Action action, Participants participants, Plant plant, boolean status) {
        this.id = id;
        this.action = action;
        this.participants = participants;
        this.plant = plant;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Participants getParticipants() {
        return participants;
    }

    public void setParticipants(Participants participants) {
        this.participants = participants;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
