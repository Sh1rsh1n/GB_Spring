package github.sh1rsh1n.seminar_3.services;

import org.springframework.stereotype.Service;

import github.sh1rsh1n.seminar_3.domain.User;

@Service
public class RegistrationService {
    
    private UserService<User> userService;
    
    public RegistrationService(UserService<User> userService) {
        this.userService = userService;
    }

    public void processRegistration(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        if (userService.save(user)) {
            NotificationService.notifyUser(user);
            return;
        }
        NotificationService.sendNotification("Incorrect value. Try again late.");
    }
}
