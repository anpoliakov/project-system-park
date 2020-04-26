package models.entity;

public class Task {
    private long id;
    private long actionId;
    private long participantsId;
    private long plantId;
    private boolean status;

    public Task(long id, long action, long participants, long plant, boolean status) {
        this.id = id;
        this.actionId = action;
        this.participantsId = participants;
        this.plantId = plant;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getActionId() {
        return actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }

    public long getParticipantsId() {
        return participantsId;
    }

    public void setParticipantsId(long participantsId) {
        this.participantsId = participantsId;
    }

    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
