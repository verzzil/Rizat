package com.company;
import java.util.List;
import java.util.Optional;


public interface CrudRepository<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    List<T> findAllByAge(Integer age);
}
