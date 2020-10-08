package repository;

public interface CrudRepository<T> {
    void save(T entity);
}
