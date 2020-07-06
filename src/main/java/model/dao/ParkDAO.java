package model.dao;

import model.entity.Park;

import java.util.List;

public interface ParkDAO {
    int addPark(Park park);
    Park getParkById(int id);
    List<Park> getParksByOwnerId(int ownerId);
    boolean deleteParkById(int id);
}
