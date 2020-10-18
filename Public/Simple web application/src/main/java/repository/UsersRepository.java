package repository;

import models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {

    Optional<User> findUserByLogin(String login);

}
