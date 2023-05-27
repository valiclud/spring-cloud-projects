package tacos.kitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class TacoCloudJmsclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudJmsclientApplication.class, args);
	}

}
