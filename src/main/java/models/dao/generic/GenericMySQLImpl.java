package models.dao.generic;

public abstract class GenericMySQLImpl<T> implements GenericDAO <T>{
    //в классе прописывается путь к connection pool

    @Override
    public int create(T t) {
        return 0;
    }

    @Override
    public T read(long id) {
        return null;
    }

    @Override
    public boolean update(T t) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
