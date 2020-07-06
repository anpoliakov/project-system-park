package model.dao.mysql;

import model.dao.PlantDAO;
import model.dao.generic.GenericMySQLImpl;
import model.entity.Plant;
import model.util.Constants;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantMySQLImpl extends GenericMySQLImpl implements PlantDAO {
    final static Logger logger = Logger.getLogger(ActionMySQLImpl.class);

    @Override
    public int addPlant(Plant plant) {
        String requestSQL = manager.getSQLRequest("ADD_PLANT");
        int reusltOperation = Constants.DEFAULT_OPERATION;

        if(getPlantByName(plant.getName()) == null){
            Connection connection = pool.getConnection();

            try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
                statement.setString(1, preparedLowerString(plant.getName()));
                statement.setString(2, plant.getDescription());
                statement.setTimestamp(3, plant.getPlanting());
                statement.setTimestamp(4, plant.getSurvey());
                statement.setInt(5, plant.getParkId());

                if(statement.executeUpdate() > 0){
                    reusltOperation = Constants.OK_OPERATION;
                }
            } catch (SQLException e) {
                logger.error("Error in PreparedStatement, look at addPlant method",e);
            }finally {
                pool.returnConnection(connection);
            }
        }else{
            reusltOperation = Constants.ERROR_OPERATION;
        }
        return reusltOperation;
    }

    @Override
    public Plant getPlantByName(String name) {
        String requestSQL = manager.getSQLRequest("GET_PLANT_BY_NAME");
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        Plant plant = null;

        try (PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setString(1, preparedLowerString(name));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                Timestamp datePlanting = resultSet.getTimestamp("planting");
                Timestamp dateSurvey = resultSet.getTimestamp("survey");
                int parkId = resultSet.getInt("park");
                plant = new Plant(id, name, description, datePlanting, dateSurvey, parkId);
            }
        }catch (SQLException e){
            logger.error("Error in PreparedStatement, look that class",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return plant;
    }

    @Override
    public Plant updateDatePlant(Plant plant) {
        String requestSQL = manager.getSQLRequest("UPDATE_PLANT");
        Connection connection = pool.getConnection();
        Plant currentPlant = null;

        try (PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setTimestamp(1, plant.getSurvey());
            statement.setInt(2, plant.getId());
            if(statement.executeUpdate() > 0){
                currentPlant = getPlantByName(plant.getName());
            }
        }catch (SQLException e){
            logger.error("Error in PreparedStatement, look that class",e);
        }finally {
            pool.returnConnection(connection);
        }
        return currentPlant;
    }

    @Override
    public List<Plant> getListPlantsByParkId(int parkId) {
        String requestSQL = manager.getSQLRequest("GET_ALL_PLANTS_BY_PARKID");
        Connection connection = pool.getConnection();
        List <Plant> listPlants = null;
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setInt(1, parkId);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                listPlants = new ArrayList<>();

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Timestamp datePlanting = resultSet.getTimestamp("planting");
                Timestamp dateSurvey = resultSet.getTimestamp("survey");
                listPlants.add( new Plant(id, name, description, datePlanting, dateSurvey, parkId));

                while (resultSet.next()){
                    id = resultSet.getInt("id");
                    name = resultSet.getString("name");
                    description = resultSet.getString("description");
                    datePlanting = resultSet.getTimestamp("planting");
                    dateSurvey = resultSet.getTimestamp("survey");
                    listPlants.add( new Plant(id, name, description, datePlanting, dateSurvey, parkId));
                }
            }
        }catch (SQLException e){
            logger.error("Error in getListPlantsByParkId method",e);
        }finally {
            pool.returnConnection(connection);
        }
        return listPlants;
    }

    @Override
    public boolean deletePlantById(int id) {
        String requestSQL = manager.getSQLRequest("DELETE_PLANT");
        boolean operationSuccess = false;
        Connection connection = pool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)){
            statement.setInt(1, id);
            if(statement.executeUpdate() > 0) {
                operationSuccess = true;
            }
        }catch (SQLException e){
            logger.error("Error in deletePlantById method",e);
        }finally {
            pool.returnConnection(connection);
        }
        return operationSuccess;
    }

    private String preparedLowerString(String str){
        return str.toLowerCase().trim();
    }
}
