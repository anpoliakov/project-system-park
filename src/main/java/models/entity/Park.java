package models.entity;

public class Park {
    private int  id;
    private String name;
    private int  ownerId;

    public Park(int  id, String name, int  owner) {
        this.id = id;
        this.name = name;
        this.ownerId = owner;
    }

    public int  getId() {
        return id;
    }

    public void setId(int  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int  getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int  ownerId) {
        this.ownerId = ownerId;
    }
}