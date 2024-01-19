package github.sh1rsh1n.sem3.repositorys;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс Repository
 * описание основных операций при обращении к БД
 */
public interface Repository<E, Id> {

    E findById(Id id);

    List<E> findAll();

    void save(E e);

    void delete(E e);
}
