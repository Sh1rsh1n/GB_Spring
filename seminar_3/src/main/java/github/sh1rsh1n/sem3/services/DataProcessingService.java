package github.sh1rsh1n.sem3.services;


import github.sh1rsh1n.sem3.domain.User;
import github.sh1rsh1n.sem3.repositorys.Repository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * класс, обработка данных полученных из БД
 */
@Service
public class DataProcessingService {

    private final Repository<User, Long> repository;

    public DataProcessingService(Repository<User, Long> repository) {
        this.repository = repository;
    }

    /**
     * метод, сортировки списка пользователей по возрасту полученного из БД
     * @return List<User> список пользователей 
     */
    public List<User> sortUsersByAge() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    /**
     * метод, фильтрация списка пользователей полученного из БД
     * больше указанного значения возраста
     * @return List<User> список пользователей 
     */
    public List<User> filterUsersByAge(int age) {
        return repository.findAll().stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    /**
     * метод, вычисление среднего возраста всех пользователей из БД
     * @return double
     */
    public double calculateAverageAge() {
        return repository.findAll().stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }
}
