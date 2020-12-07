package ru.itis.repositories;

import ru.itis.models.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class PostsCrudRepositoryImpl implements CrudRepository<Post> {

    private DataSource dataSource;

    //language=SQL
    private final static String SQL_INSERT = "insert into posts(userId, creationDate, name, postText) " +
            "values (?, ?, ?, ?)";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "select * from posts where id = ?";

    //language=SQL
    private final static String SQL_SELECT_ALL = "select * from posts";

    private JdbcTemplate jdbcTemplate;

    public PostsCrudRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Post> postRowMapper = (row, rowNumber) ->
            Post.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .creationDate(row.getString("creationDate"))
                    .userId(row.getLong("userId"))
                    .text(row.getString("postText"))
                    .build();

    @Override
    public void save(Post entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getUserId(), entity.getCreationDate(),
                entity.getName(), entity.getText());
    }

    @Override
    public Post findById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, postRowMapper, id);
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, postRowMapper);
    }
}
