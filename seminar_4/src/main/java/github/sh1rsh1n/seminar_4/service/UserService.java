package github.sh1rsh1n.seminar_4.service;

import java.util.List;

import github.sh1rsh1n.seminar_4.entity.User;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Integer id);

    User getUserByName(String name);

    boolean saveUser(User user);

    boolean deleteUser(Integer id);
}
