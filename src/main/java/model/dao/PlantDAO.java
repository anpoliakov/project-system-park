package model.dao;

import model.entity.Plant;

import java.util.List;

public interface PlantDAO {
    int addPlant(Plant plant);
    Plant getPlantByName(String name);
    Plant updateDatePlant (Plant plant);
    List <Plant> getListPlantsByParkId(int id);
    boolean deletePlantById(int id);

}
