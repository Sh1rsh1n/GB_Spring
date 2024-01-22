package github.sh1rsh1n.seminar_4.repository;

import github.sh1rsh1n.seminar_4.config.SqlQueryConfig;
import github.sh1rsh1n.seminar_4.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Реализация обращений к БД
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SqlQueryConfig queryConfig;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, SqlQueryConfig queryConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryConfig = queryConfig;
    }

    /**
     * Получение пользователя по ID
     * @param id - идентификатор пользователя
     * @return Optional<User>
     */
    @Override
    public Optional<User> findById(Integer id) {
        if (id <= 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(jdbcTemplate.queryForObject(queryConfig.getGetById(), mapper, id));
    }

    /**
     * Получение списка пользователей из БД
     * @return List<User>
     */
    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(queryConfig.getGetAll(), mapper);
    }

    /**
     * Сохранение нового и изменение существующего пользователя в БД
     * @param user - пользователь
     */
    @Override
    public void save(User user) {
        if (findById(user.getId()).isPresent()) {
            jdbcTemplate.update(
                    queryConfig.getUpdate(),
                    user.getName(),
                    user.getAge(),
                    user.getEmail(),
                    user.getId());
        } else {
            jdbcTemplate.update(
                    queryConfig.getInsert(),
                    user.getName(),
                    user.getAge(),
                    user.getEmail());
        }
    }

    /**
     * Удаление пользователя по ID
     * @param id - идентификатор пользователя
     */
    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update(queryConfig.getDelete(), id);
    }

    /**
     * Получение пользователя по имени
     * @param name - имя пользователя
     * @return Optional<User>
     */
    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(queryConfig.getGetById(), mapper, name));
    }

    /**
     * преобразование данных о пользователе из БД в сущность
     */
    private final RowMapper<User> mapper = (r, i) -> {
        User user = new User();
        user.setId(r.getInt("id"));
        user.setName(r.getString("name"));
        user.setAge(r.getInt("age"));
        user.setEmail(r.getString("email"));
        return user;
    };
}
