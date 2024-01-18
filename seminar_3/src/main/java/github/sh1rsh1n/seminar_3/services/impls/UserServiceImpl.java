package github.sh1rsh1n.seminar_3.services.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import github.sh1rsh1n.seminar_3.domain.User;
import github.sh1rsh1n.seminar_3.repositorys.UserRepository;
import github.sh1rsh1n.seminar_3.services.UserService;

/**
 * Класс, взаимодествие и обработка пользовательских данных
 */
@Service
public class UserServiceImpl implements UserService<User> {

    private UserRepository<User, Integer> repository;

    public UserServiceImpl(UserRepository<User, Integer> repository) {
        this.repository = repository;
    }

    /**
     * Удаление пользователя
     * Перед удалением, выполняется проверка существования пользователя в БД
     * @param User пользователь которого удаляем
     * @reurn boolean
     */
    @Override
    public boolean delete(User user) {
        if (repository.findById(user.getId()).isPresent()) {
            repository.delete(user);
            return true;
        }
        return false;
    }

    /**
     * Получение списка всех полльзователей
     * @return List<User> список пользователей
     */
    @Override
    public List<User> getAll() {
        return repository.findAll();        
    }

    /**
     * Получение пользователя по ID
     * @return User пользователь
     */
    @Override
    public User getById(Integer id) {
        return repository.findById(id).orElse(null);
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