package github.sh1rsh1n.seminar_7;

import github.sh1rsh1n.seminar_7.entity.Role;
import github.sh1rsh1n.seminar_7.entity.User;
import github.sh1rsh1n.seminar_7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@RequiredArgsConstructor
public class Seminar7Application implements CommandLineRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Seminar7Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User("user", "$2a$10$jQlBlZZuO4aTHDw2YXwimupl0GrDGZRPCjsAr/eDFBT3AWRmBPaqe", Role.USER);
        userRepository.save(user);

        User admin = new User("admin", "$2a$10$SN6IVfmAGy0fiRd5FsM9tOcgQLpvZvLHtbwbAEJ5zA0Nxe9ILsvf2", Role.ADMIN);
        userRepository.save(admin);

    }
}
