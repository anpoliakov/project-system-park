package models.dao.generic;

public interface GenericDAO <T> {

    int create(T t);
    T read(long id);
    boolean update(T t);
    boolean delete(long id);

}
