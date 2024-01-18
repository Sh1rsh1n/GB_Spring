package github.sh1rsh1n.seminar_3.services;

import java.util.List;

public interface UserService<E> {
    
    E getById(Integer id);

    List<E> getAll();

    boolean save(E e);

    boolean delete(E e);
}
