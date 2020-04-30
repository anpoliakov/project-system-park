package models.entity;

import java.util.Date;
import java.util.Objects;

public class Plant {
    private int id;
    private String name;
    private String description;
    private Date planting;
    private Date survey;
    private int parkId;

    public Plant(int id, String name, String description, Date planting, Date survey, int parkId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.planting = planting;
        this.survey = survey;
        this.parkId = parkId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPlanting() {
        return planting;
    }

    public void setPlanting(Date planting) {
        this.planting = planting;
    }

    public Date getSurvey() {
        return survey;
    }

    public void setSurvey(Date survey) {
        this.survey = survey;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return id == plant.id &&
                parkId == plant.parkId &&
                Objects.equals(planting, plant.planting) &&
                Objects.equals(survey, plant.survey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planting, survey, parkId);
    }
}
