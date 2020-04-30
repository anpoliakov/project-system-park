package models.dao;

import models.entity.Park;

public interface ParkDAO {
    int addPark(Park park);
    Park getParkById(int id);
    Park getParkByOwnerId(int ownerId);
    boolean deleteParkById(int id);
}
