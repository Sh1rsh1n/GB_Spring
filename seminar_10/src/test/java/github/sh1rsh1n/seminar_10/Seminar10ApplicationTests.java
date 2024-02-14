package github.sh1rsh1n.seminar_10;

import github.sh1rsh1n.seminar_10.controller.TaskController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Seminar10ApplicationTests {

	@Autowired
	private TaskController taskController;

	@Test
	void contextLoads() {
		assertThat(taskController).isNotNull();
	}


}
