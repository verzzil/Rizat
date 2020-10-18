package repository;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public void save(User entity) {
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO Users (first_name, last_name, password, email, uuid) VALUES (?, ?, ?, ?, ?);");
            st.setString(1, entity.getFirstName());
            st.setString(2, entity.getLastName());
            st.setObject(3, entity.getPassword());
            st.setObject(4, entity.getEmail());
            st.setObject(5, UUID.randomUUID().toString());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"));
                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from users where first_name ='" + login + "'");
            if(!result.next())
                return Optional.empty();
            User newUser = new User(
                    result.getString("first_name"),
                    result.getString("password"),
                    result.getString("uuid")
            );
            return Optional.of(newUser);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
