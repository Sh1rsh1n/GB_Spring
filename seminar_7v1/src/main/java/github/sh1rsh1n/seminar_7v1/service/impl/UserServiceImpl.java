package github.sh1rsh1n.seminar_7v1.service.impl;

import github.sh1rsh1n.seminar_7v1.entity.User;
import github.sh1rsh1n.seminar_7v1.repository.UserRepository;
import github.sh1rsh1n.seminar_7v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseGet(null);
    }
}
