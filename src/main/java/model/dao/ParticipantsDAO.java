package model.dao;

import model.entity.Participants;
import model.entity.Person;

import java.util.List;

public interface ParticipantsDAO {
    int addParticipants(Person owner, Person forester);
    Participants getParticipantsByOwnerIDForesterID(int ownerId, int foresterId);
    Participants getParticipantsById(int id);
    List <Participants> getListParticipantsByOwnerId(int ownerId);
    List <Participants> getListParticipantsByForesterId(int foresterId);
}
