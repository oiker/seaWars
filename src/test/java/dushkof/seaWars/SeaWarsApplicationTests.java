package dushkof.seaWars;

import dushkof.seaWars.objects.User;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeaWarsApplicationTests {

	@Test
	void contextLoads() {
	}

	private User user;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void createNewUserTest() throws Exception{
	}



}
