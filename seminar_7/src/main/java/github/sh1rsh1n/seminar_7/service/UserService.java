package github.sh1rsh1n.seminar_7.service;

import github.sh1rsh1n.seminar_7.entity.User;
import github.sh1rsh1n.seminar_7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

/**
 * UserService реализация интерфейса UserDetailsService
 * Так же можно добавить методы для работы с сущностью User в БД
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        UserBuilder builder;

        if (user.isPresent()) {
            User currentUser = user.get();
            builder = withUsername(username);
            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole().getAuthority());
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return builder.build();
    }
}
