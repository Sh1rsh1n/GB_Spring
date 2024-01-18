package github.sh1rsh1n.seminar_3.services;

import github.sh1rsh1n.seminar_3.domain.User;

public class NotificationService {
    
    public static void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }

    public static void sendNotification(String s) {
        System.out.println(s);
    }
}
