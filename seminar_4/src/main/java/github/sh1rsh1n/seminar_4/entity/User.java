package github.sh1rsh1n.seminar_4.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;


/**
 * Описание сущности "пользователь"
 * и его связывание с таблицей в БД
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "email", length = 255)
    private String email;
}
