package github.sh1rsh1n.seminar_3.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;


/**
 * Интерфейс Repository
 * описание 
 */
@Repository
public interface UserRepository<E, Id> {

    Optional<E> findById(Id id);

    List<E> findAll();

    void save(E e);

    void delete(E e);
}
