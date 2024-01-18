package github.sh1rsh1n.seminar_3.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.sh1rsh1n.seminar_3.domain.User;
import github.sh1rsh1n.seminar_3.repositorys.UserRepository;

@Service
public class DataProcessingService {
    
    
    private final UserRepository<User> repository;

    public DataProcessingService(UserRepository<User> repository) {
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
