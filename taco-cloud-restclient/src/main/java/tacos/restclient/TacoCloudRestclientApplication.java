package tacos.restclient;

import java.net.URI;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.IngredientDto;
import tacos.dto.IngredientDto.Type;
import tacos.dto.TacoDto;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan
@Slf4j
public class TacoCloudRestclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudRestclientApplication.class, args);
	}

	@Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
	
	 @Bean
	  public CommandLineRunner fetchIngredients(TacoCloudClient tacoCloudClient) {
	    return args -> {
	      log.info("----------------------- GET -------------------------");
	      log.info("GETTING ALL INGREDIENTS");
	      List<IngredientDto> ingredients = tacoCloudClient.getAllIngredients();
	      log.info("All ingredients:");
	      for (IngredientDto ingredient : ingredients) {
	        log.info("   - " + ingredient);
	      }
	    };
	  }
	 
   @Bean
   public CommandLineRunner createIngredient(TacoCloudClient tacoCloudClient) {
     return args -> {
       log.info("----------------------- POST -------------------------");
       log.info("CREATE NEW INGREDIENT");
       IngredientDto dto = new IngredientDto(11L,"VALI", "Valicek", Type.SAUCE);
       IngredientDto ingredient = tacoCloudClient.createIngredient(dto);
       log.info("Ingredient created: " + ingredient);
     };
   }

   @Bean
   public CommandLineRunner getIngredientById(TacoCloudClient tacoCloudClient) {
     return args -> {
       log.info("----------------------- GET BY ID -------------------------");
       log.info("GET INGREDIENT BY ID = 1");
       IngredientDto ingredient = tacoCloudClient.getIngredientById("1");
       log.info("Ingredient id = 1 : " + ingredient);
     };
   }

	
/*
 * Commented as Traverson requires HATEOAS rel attribute in json
 * 
 * 
	 @Bean
	  public Traverson traverson() {
	    Traverson traverson = new Traverson(
	        URI.create("http://localhost:8080/api/ingredients"), MediaTypes.HAL_JSON);
	    return traverson;
	  }

	  @Bean
	  public CommandLineRunner traversonGetIngredients(TacoCloudClient tacoCloudClient) {
	    return args -> {
	      Iterable<IngredientDto> ingredients = tacoCloudClient.getAllIngredientsWithTraverson();
	      log.info("----------------------- GET INGREDIENTS WITH TRAVERSON -------------------------");
	      for (IngredientDto ingredient : ingredients) {
	        log.info("   -  " + ingredient);
	      }
	    };
	  }
	  
	  @Bean
	  public CommandLineRunner traversonRecentTacos(TacoCloudClient tacoCloudClient) {
	    return args -> {
	      Iterable<TacoDto> recentTacos = tacoCloudClient.getRecentTacosWithTraverson();
	      log.info("----------------------- GET RECENT TACOS WITH TRAVERSON -------------------------");
	      for (TacoDto taco : recentTacos) {
	        log.info("   -  " + taco);
	      }
	    };
	  }
*/
	 
}
