package github.sh1rsh1n.seminar_3.repositorys.impls;

import java.util.List;
import java.util.Optional;

import github.sh1rsh1n.seminar_3.repositorys.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import github.sh1rsh1n.seminar_3.domain.User;
import org.springframework.stereotype.Component;

/**
 * Класс реализация обращения к БД
 */
@Component
public class UserRepositoryImpl implements Repository<User, Integer> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Получаем пользователя по ID
     * возвращаем Optional<User>, так как пользователя с указанным ID может не быть в БД
     * @param Integer - id пользователя
     * @return Optional<User>
     */
    @Override
    public Optional<User> findById(Integer id) {
        String query = "select * from users WHERE id=?";
        return Optional.of(jdbcTemplate.queryForObject(query, mapper, id));
    }

    /**
     * получаем список всех пользователей
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
        if (findById(user.getId()).isPresent()) {
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
        user.setId(r.getInt("id"));
        user.setName(r.getString("name"));
        user.setAge(r.getInt("age"));
        user.setEmail(r.getString("email"));
        return user;
    };
}
