package github.sh1rsh1n.seminar_7.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
