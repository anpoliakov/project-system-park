package model.entity;

public class Task {
    private int id;
    private int actionId;
    private int participantsId;
    private int plantId;
    private boolean status;

    public Task(int id, int action, int participants, int plant, boolean status) {
        this.id = id;
        this.actionId = action;
        this.participantsId = participants;
        this.plantId = plant;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getParticipantsId() {
        return participantsId;
    }

    public void setParticipantsId(int participantsId) {
        this.participantsId = participantsId;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
