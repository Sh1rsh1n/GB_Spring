package github.sh1rsh1n.seminar_11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Seminar11Application {

	public static void main(String[] args) {
		SpringApplication.run(Seminar5Application.class, args);
	}

}
