package model.entity;

public class Participants {
    private int id;
    private int ownerId;
    private int foresterId;

    public Participants(int id, int fromOwner, int forForester) {
        this.id = id;
        this.ownerId = fromOwner;
        this.foresterId = forForester;
    }

    public Participants(int fromOwner, int forForester) {
        this.ownerId = fromOwner;
        this.foresterId = forForester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getForesterId() {
        return foresterId;
    }

    public void setForesterId(int foresterId) {
        this.foresterId = foresterId;
    }
}
