package MainService.MainService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainServiceApplication.class, args);
	}

}
