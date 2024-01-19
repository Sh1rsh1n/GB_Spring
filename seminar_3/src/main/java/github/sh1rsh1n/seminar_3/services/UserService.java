package github.sh1rsh1n.seminar_3.services;

import java.util.List;

/**
 * интерфейс UserService
 * описание основных операций при обращении к репозиторию
 */
public interface UserService<E> {
    
    E getById(Long id);

    List<E> getAll();

    boolean save(E e);

    boolean delete(E e);
}
