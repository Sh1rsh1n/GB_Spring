package github.sh1rsh1n.seminar_3.services;

import org.springframework.stereotype.Service;

import github.sh1rsh1n.seminar_3.repositorys.UserRepository;

/**
 * Класс, взаимодествие с пользовательскими данными
 */
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


}