package github.sh1rsh1n.seminar_4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import github.sh1rsh1n.seminar_4.entity.User;

public interface UserReposotory extends JpaRepository<User, Integer>{

    Optional<User> findByName(String name);
}
