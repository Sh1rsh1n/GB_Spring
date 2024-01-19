package github.sh1rsh1n.seminar_3.services;

import github.sh1rsh1n.seminar_3.domain.User;
import org.springframework.stereotype.Service;

/**
 * Класс, регистрация пользователя
 */
@Service
public class RegistrationService {
    
    private UserService<User> userService;
    
    public RegistrationService(UserService<User> userService) {
        this.userService = userService;
    }

    /**
     * метод, процесс регистрации пользователя
     * @param name имя
     * @param age возраст
     * @param email электронная почта
     */
    public void processRegistration(String name, int age, String email) {
        User user = new User();
        user.setId(0);
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        // если пользователь успешно создан, выводим оповещение в консоль
        if (userService.save(user)) {
            NotificationService.notifyUser(user);
            return;
        }
        NotificationService.sendNotification("Incorrect value. Try again late.");
    }
}
