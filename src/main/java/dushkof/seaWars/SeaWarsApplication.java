package dushkof.seaWars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class SeaWarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeaWarsApplication.class, args);
	}

}
