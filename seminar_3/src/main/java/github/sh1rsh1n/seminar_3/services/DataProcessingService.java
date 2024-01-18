package github.sh1rsh1n.seminar_3.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import github.sh1rsh1n.seminar_3.domain.User;
import github.sh1rsh1n.seminar_3.repositorys.Repository;

@Service
public class DataProcessingService {

    private final Repository<User, Integer> repository;

    public DataProcessingService(Repository<User, Integer> repository) {
        this.repository = repository;
    }

    public List<User> sortUsersByAge() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(int age) {
        return repository.findAll().stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge() {
        return repository.findAll().stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }
}
