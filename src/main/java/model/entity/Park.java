package model.entity;

public class Park {
    private int  id;
    private String name;
    private int ownerId;

    public Park(int  id, String name, int  owner) {
        this.id = id;
        this.name = name;
        this.ownerId = owner;
    }

    public Park(String name, int ownerId) {
        this.name = name;
        this.ownerId = ownerId;
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

    @Override
    public String toString() {
        return "Park{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}