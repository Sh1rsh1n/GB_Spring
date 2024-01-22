package github.sh1rsh1n.seminar_4.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Конфигурация запросов к БД из properties-файла
 */
@Getter
@Component
@PropertySource("classpath:query.properties")
public class SqlQueryConfig {

    @Value("${get.all}")
    private String getAll;

    @Value("${get.by.id}")
    private String getById;

    @Value("${get.by.name}")
    private String getByName;

    @Value("${user.insert}")
    private String insert;

    @Value("${user.update}")
    private String update;

    @Value("${user.delete}")
    private String delete;

}
