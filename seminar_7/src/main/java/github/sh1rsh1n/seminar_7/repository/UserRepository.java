package github.sh1rsh1n.seminar_7.repository;

import github.sh1rsh1n.seminar_7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
