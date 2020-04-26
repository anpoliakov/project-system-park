package models.entity;

public class Park {
    private long  id;
    private String name;
    private long  ownerId;

    public Park(long  id, String name, long  owner) {
        this.id = id;
        this.name = name;
        this.ownerId = owner;
    }

    public long  getId() {
        return id;
    }

    public void setId(long  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long  getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long  ownerId) {
        this.ownerId = ownerId;
    }
}