package ducami.org.ducademi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DucademiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DucademiApplication.class, args);
	}

}
