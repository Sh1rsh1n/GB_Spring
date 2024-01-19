package github.sh1rsh1n.sem3.services;


import github.sh1rsh1n.sem3.domain.User;

/**
 * Класс, уведомления о каких либо действиях с данными 
 */
public class NotificationService {
    
    /**
     * оповещение о создании пользователя
     * @param user пользователь
     */
    public static void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }

    /**
     * все остальные оповещения
     * @param s сообщение
     */
    public static void sendNotification(String s) {
        System.out.println(s);
    }
}
