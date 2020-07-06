package model.dao.mysql;

import model.dao.ParticipantsDAO;
import model.dao.generic.GenericMySQLImpl;
import model.entity.Participants;
import model.entity.Person;
import model.util.Constants;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantsMySQLImpl extends GenericMySQLImpl implements ParticipantsDAO {
    final static Logger logger = Logger.getLogger(ActionMySQLImpl.class);

    @Override
    public int addParticipants(Person owner, Person forester) {
        String requestSQL = manager.getSQLRequest("ADD_PARTICIPANTS");
        int reusltOperation = Constants.DEFAULT_OPERATION;
        int ownerId = owner.getId();
        int foresterId = forester.getId();

        if(getParticipantsByOwnerIDForesterID(ownerId, foresterId) == null){
            Connection connection = pool.getConnection();
            try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
                statement.setInt(1, ownerId);
                statement.setInt(2, foresterId);
                if(statement.executeUpdate() > 0){
                    reusltOperation = Constants.OK_OPERATION;
                }
            } catch (SQLException e) {
                logger.error("Error in PreparedStatement, look ParticipantsMySQLImpl class",e);
            }finally {
                pool.returnConnection(connection);
            }
        }else{
            reusltOperation = Constants.ERROR_OPERATION;
        }
        return reusltOperation;
    }

    @Override
    public Participants getParticipantsByOwnerIDForesterID(int ownerId, int foresterId) {
        String requestSQL = manager.getSQLRequest("GET_PARTICIPANTS_BY_OWNER_FORESTER");
        Participants participants = null;
        ResultSet resultSet = null;
        Connection connection = pool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setInt(1, ownerId);
            statement.setInt(2, foresterId);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                participants = new Participants(id,ownerId,foresterId);
            }
        } catch (SQLException e) {
            logger.error("Error in PreparedStatement, check SQL request",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return participants;
    }

    @Override
    public Participants getParticipantsById(int id) {
        String requestSQL = manager.getSQLRequest("GET_PARTICIPANTS_BY_ID");
        Participants participants = null;
        ResultSet resultSet = null;
        Connection connection = pool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int participantsId = resultSet.getInt("id");
                int ownerId = resultSet.getInt("ownerId");
                int foresterId = resultSet.getInt("foresterId");
                participants = new Participants(participantsId,ownerId,foresterId);
            }
        } catch (SQLException e) {
            logger.error("Error in PreparedStatement, check SQL request",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return  participants;
    }

    @Override
    public List<Participants> getListParticipantsByOwnerId(int ownerId) {
        String requestSQL = manager.getSQLRequest("GET_ALL_PARTICIPANTS_WHERE_OWNER_ID");
        Connection connection = pool.getConnection();
        List <Participants> listParticipants = null;
        ResultSet resultSet = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)){
            statement.setInt(1, ownerId);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                listParticipants = new ArrayList<>();
                int id = resultSet.getInt("id");
                int idOwner = resultSet.getInt("ownerId");
                int idForester = resultSet.getInt("foresterId");
                listParticipants.add(new Participants(id, idOwner, idForester));

                while(resultSet.next()) {
                    id = resultSet.getInt("id");
                    idOwner = resultSet.getInt("ownerId");
                    idForester = resultSet.getInt("foresterId");
                    listParticipants.add(new Participants(id, idOwner, idForester));
                }
            }


        }catch (SQLException e){
            logger.error("Error in statement, look that class",e);
        }finally {
            pool.closeResultSet(resultSet); // ВЕРНО ЛИ ? что тут закрыл
            pool.returnConnection(connection);
        }
        return listParticipants;
    }

    @Override
    public List<Participants> getListParticipantsByForesterId(int foresterId) {
        String requestSQL = manager.getSQLRequest("GET_ALL_PARTICIPANTS_WHERE_FORESTER_ID");
        Connection connection = pool.getConnection();
        List <Participants> listParticipants = null;
        ResultSet resultSet = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)){
            statement.setInt(1, foresterId);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                listParticipants = new ArrayList<>();
                int id = resultSet.getInt("id");
                int idOwner = resultSet.getInt("ownerId");
                int idForester = resultSet.getInt("foresterId");
                listParticipants.add(new Participants(id, idOwner, idForester));

                while(resultSet.next()) {
                    id = resultSet.getInt("id");
                    idOwner = resultSet.getInt("ownerId");
                    idForester = resultSet.getInt("foresterId");
                    listParticipants.add(new Participants(id, idOwner, idForester));
                }
            }
        }catch (SQLException e){
            logger.error("Error in statement, look that class",e);
        }finally {
            pool.closeResultSet(resultSet); // ВЕРНО ЛИ ? что тут закрыл
            pool.returnConnection(connection);
        }
        return listParticipants;
    }
}
