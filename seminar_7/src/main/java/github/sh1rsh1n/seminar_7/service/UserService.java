package github.sh1rsh1n.seminar_7.service;

import github.sh1rsh1n.seminar_7.entity.User;

public interface UserService {

    void saveUser(User user);

    User findUserByUsername(String username);
}
