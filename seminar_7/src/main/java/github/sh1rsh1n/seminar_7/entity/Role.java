package github.sh1rsh1n.seminar_7.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Роли пользователя, реализуют интерфейс GrantedAuthority
 */
public enum Role implements GrantedAuthority {

    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
