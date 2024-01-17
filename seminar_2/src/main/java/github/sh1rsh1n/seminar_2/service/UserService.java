package github.sh1rsh1n.seminar_2.service;

import github.sh1rsh1n.seminar_2.model.User;
import github.sh1rsh1n.seminar_2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}