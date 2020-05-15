package models.entity;

import java.util.Objects;

public class Action {
    private int  id;
    private String name;

    public Action(int  id, String name) {
        this.id = id;
        this.name = name;
    }

    public Action(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return Objects.equals(id, action.id) &&
                Objects.equals(name, action.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
