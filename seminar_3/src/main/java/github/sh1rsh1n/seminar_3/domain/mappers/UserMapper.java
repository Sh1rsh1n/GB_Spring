package github.sh1rsh1n.seminar_3.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import github.sh1rsh1n.seminar_3.domain.User;
import github.sh1rsh1n.seminar_3.domain.dto.UserDto;

public class UserMapper {
    
    public User dtoToEntity(UserDto dto) {
        return new User(
            dto.getName(),
            dto.getAge(),
            dto.getEmail()
        );
    }

    public UserDto entityToDto(User user) {
        return new UserDto(
            user.getId(),
            user.getName(), 
            user.getAge(), 
            user.getEmail()
        );
    }
}
