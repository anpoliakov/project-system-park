package models.entity;

import java.util.Date;

public class Plant {
    private int id;
    private String name;
    private String description;
    private Date planting;
    private Date survey;
    private Park park;

    public Plant(int id, String name, String description, Date planting, Date survey, Park park) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.planting = planting;
        this.survey = survey;
        this.park = park;
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

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }
}
