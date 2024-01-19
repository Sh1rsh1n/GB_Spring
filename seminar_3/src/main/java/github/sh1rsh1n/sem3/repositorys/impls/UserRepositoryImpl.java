package github.sh1rsh1n.sem3.repositorys.impls;


import github.sh1rsh1n.sem3.domain.User;
import github.sh1rsh1n.sem3.repositorys.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализация обращения к БД
 */
@Component
public class UserRepositoryImpl implements Repository<User, Long> {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Получаем пользователя по ID
     * возвращаем Optional<User>, так как пользователя с указанным ID может не быть в БД
     *
     * @param Integer - id пользователя
     * @return Optional<User>
     */
    @Override
    public User findById(Long id) {
        String query = "select u.name, u.age, u.email from users as u where id=?";
        if (id == 0) {
            return null;
        }
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }


    /**
     * получаем список всех пользователей
     *
     * @return Lisr<User>
     */
    @Override
    public List<User> findAll() {
        String query = "select * from users";
        return jdbcTemplate.query(query, mapper);
    }

    /**
     * Сохранение пользователя в БД
     * Выполняется проверка, есть ли пользователь в БД и по результату проверки,
     * либо создаем пользователя, либо обновляем его данные
     */
    @Override
    public void save(User user) {
        String createQuery = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        String updateQuery = "UPDATE users SET name=?, age=?, email=? WHERE id=?";
        if (findById(user.getId()) != null) {
            jdbcTemplate.update(
                    updateQuery,
                    user.getName(),
                    user.getAge(),
                    user.getEmail(),
                    user.getId());
        } else {
            jdbcTemplate.update(
                    createQuery,
                    user.getName(),
                    user.getAge(),
                    user.getEmail());
        }
    }

    /**
     * Удаление пользователя из БД
     */
    @Override
    public void delete(User user) {
        String deleteQuery = "DELETE FROM users WHERE id=?";
        jdbcTemplate.update(deleteQuery);
    }

    /**
     * преобразование данных о пользователе из БД в сущность
     */
    private final RowMapper<User> mapper = (r, i) -> {
        User user = new User();
        user.setId(r.getLong("id"));
        user.setName(r.getString("name"));
        user.setAge(r.getInt("age"));
        user.setEmail(r.getString("email"));
        return user;
    };
}
