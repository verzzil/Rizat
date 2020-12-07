package ru.itis.repositories;
import java.util.List;

public interface CrudRepository<T> {
    void save(T entity);
    T findById(Long id);
    List<T> findAll();

}
