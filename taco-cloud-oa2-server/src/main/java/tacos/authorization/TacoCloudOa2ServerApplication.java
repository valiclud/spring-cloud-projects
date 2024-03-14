package tacos.authorization;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.authorization.users.User;
import tacos.authorization.users.UserRepository;

@SpringBootApplication
public class TacoCloudOa2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudOa2ServerApplication.class, args);
	}

	@Bean
	  public ApplicationRunner dataLoader(
	          UserRepository repo, PasswordEncoder encoder) {
	    return args -> {
	      repo.save(
	          new User("habuma", encoder.encode("password"), "ROLE_ADMIN"));
	      repo.save(
	          new User("tacochef", encoder.encode("pass"), "ROLE_ADMIN"));
	    };
	  }
	
}
