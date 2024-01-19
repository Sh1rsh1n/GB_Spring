package github.sh1rsh1n.seminar_3.services.impls;


import github.sh1rsh1n.seminar_3.domain.User;
import github.sh1rsh1n.seminar_3.repositorys.Repository;
import github.sh1rsh1n.seminar_3.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс, взаимодествие и обработка пользовательских данных
 */
@Service
public class UserServiceImpl implements UserService<User> {

    private final Repository<User, Long> repository;

    public UserServiceImpl(Repository<User, Long> repository) {
        this.repository = repository;
    }

    /**
     * Удаление пользователя
     * Перед удалением, выполняется проверка существования пользователя в БД
     *
     * @param User пользователь которого удаляем
     * @reurn boolean
     */
    @Override
    public boolean delete(User user) {
        if (repository.findById(user.getId()) != null) {
            repository.delete(user);
            return true;
        }
        return false;
    }

    /**
     * Получение списка всех полльзователей
     *
     * @return List<User> список пользователей
     */
    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    /**
     * Получение пользователя по ID
     *
     * @return User пользователь
     */
    @Override
    public User getById(Long id) {
        return repository.findById(id);
    }

    /**
     * Сохранение пользователя в БД
     * предварительно выполняем проверку на null
     */
    @Override
    public boolean save(User user) {
        if (user != null) {
            repository.save(user);
            return true;
        }
        return false;
    }


}