package repositories;
import models.User;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {


    private DataSource dataSource;

    //language=SQL
    private final static String SQL_INSERT = "insert into semestr_user(first_name, last_name, email, hash_password) " +
            "values (?, ?, ?, ?)";
    //language=SQL
    private final static String SQL_FIND_BY_EMAIL = "select * from semestr_user where email = ?";

    //language=SQL
    private final static String SQL_SELECT_ALL = "select * from semestr_user";

    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> usersRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .hashPassword(row.getString("hash_password"))
            .build();

    @Override
    public List<User> findAllByAge(Integer age) {
        return null;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, usersRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    /*@Override
    public void save(User entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getHashPassword());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Problem with insert user");
            }

            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("Problem with retieve id");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            closeAllConnection(generatedKeys, statement, connection);
        }
    }*/

    public void save(User entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getHashPassword());
    }

    private void closeAllConnection(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
            }
        }
    }



}

