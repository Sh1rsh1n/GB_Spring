package github.sh1rsh1n.seminar_7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация безопасности
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Способ кодировки паролей
     * @return
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Цепочка фильтров:
     * - отключены cors, csrf-фильтры
     * - авторизация http-запросов, определяем какие из endpoint будут требовать от пользователя авторизации
     * - форма логина, определяем свою кастомную форму и на каком endpoint-e она находится
     * - переопределяем функцию logout, при успешном выходе пользователь будет переадресован на /login
     * - обработка исключений, при любом исключении будет статус ответа UNAUTHORIZED
     *      при попытке получить доступ к закрытому ресурсу, будет ответ FORBIDDEN
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/").permitAll()
                        .requestMatchers("/private", "/h2-console/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(out -> out
                        .logoutSuccessUrl("/login")
                        .permitAll())
                .exceptionHandling(configurer -> configurer.authenticationEntryPoint((request, response, exception) -> {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.getWriter().write("UNAUTHORIZED.");
                            response.sendRedirect("/login");
                        })
                        .accessDeniedHandler((request, response, excepton) -> {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.getWriter().write("FORBIDDEN");
                            response.sendRedirect("/login");
                        }));
        return http.build();
    }
}
