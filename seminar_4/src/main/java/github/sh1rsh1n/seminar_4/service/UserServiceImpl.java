package github.sh1rsh1n.seminar_4.service;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.sh1rsh1n.seminar_4.entity.User;
import github.sh1rsh1n.seminar_4.repository.UserRepository;

/**
 * Реализация методов обращения к репозиторию
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Удаление пользователя по ID
     * @param id - идентификатор пользователя
     * @return boolean
     */
    @Override
    public boolean deleteUser(Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Список всех пользователей
     * Если в БД нет информации о пользователяхю возвращает пустой список
     * @return List<User>
     */
    @Override
    public List<User> getAllUsers() {
        if (repository.findAll().isEmpty()){
            return Collections.emptyList();
        }
        return repository.findAll();
    }

    /**
     * Поиск пользователя по ID в БД
     * @param id - идентификатор пользователя
     * @return User
     */
    @Override
    public User getUserById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Поиск пользователя в БД по имени
     * @param name - имя пользователя
     * @return User
     */
    @Override
    public User getUserByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    /**
     * Сохранение изменений или создание нового пользователя в БД
     * @param user - пользователь
     * @return boolean
     */
    @Override
    public boolean saveUser(User user) {
        if (user != null) {
            repository.save(user);
            return true;
        }
        return false;
    }
}