package tacos.restclient;

import java.util.Collection;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tacos.dto.IngredientDto;
import tacos.dto.TacoDto;

@Service
@Slf4j
public class TacoCloudClient {

  private RestTemplate rest;
  
  public TacoCloudClient(RestTemplate rest) {
    this.rest = rest;
  }
  
/*  private Traverson traverson;

  public TacoCloudClient(RestTemplate rest, Traverson traverson) {
    this.rest = rest;
    this.traverson = traverson;
  }
*/
  //
  // GET examples
  //

  /*
   * Specify parameter as varargs argument
   */
  public IngredientDto getIngredientById(String ingredientId) {
    return rest.getForObject("http://localhost:8080/api/ingredients/{id}",
                             IngredientDto.class, ingredientId);
  }

  /*
   * Alternate implementations...
   * The next three methods are alternative implementations of
   * getIngredientById() as shown in chapter 6. If you'd like to try
   * any of them out, comment out the previous method and uncomment
   * the variant you want to use.
   */

  /*
   * Specify parameters with a map
   */
  /*
  public Ingredient getIngredientById(String ingredientId) {
    Map<String, String> urlVariables = new HashMap<>();
    urlVariables.put("id", ingredientId);
    return rest.getForObject("http://localhost:8080/ingredients/{id}",
        Ingredient.class, urlVariables);
  }
  */

  /*
   * Request with URI instead of String
   */
  /*
  public Ingredient getIngredientById(String ingredientId) {
    Map<String, String> urlVariables = new HashMap<>();
    urlVariables.put("id", ingredientId);
    URI url = UriComponentsBuilder
              .fromHttpUrl("http://localhost:8080/ingredients/{id}")
              .build(urlVariables);
    return rest.getForObject(url, Ingredient.class);
  }
  */

  /*
   * Use getForEntity() instead of getForObject()
   */
  /*
  public Ingredient getIngredientById(String ingredientId) {
    ResponseEntity<Ingredient> responseEntity =
        rest.getForEntity("http://localhost:8080/ingredients/{id}",
            Ingredient.class, ingredientId);
    log.info("Fetched time: {}",
            responseEntity.getHeaders().getDate());
    return responseEntity.getBody();
  }
  */

  public List<IngredientDto> getAllIngredients() {
    return rest.exchange("http://localhost:8080/api/ingredients",
            HttpMethod.GET, null, new ParameterizedTypeReference<List<IngredientDto>>() {})
        .getBody();
  }

  //
  // PUT examples
  //

  public void updateIngredient(IngredientDto ingredient) {
    rest.put("http://localhost:8080/ingredients/{id}",
          ingredient, ingredient.getId());
  }

  //
  // POST examples
  //
  public IngredientDto createIngredient(IngredientDto ingredient) {
    return rest.postForObject("http://localhost:8080/api/ingredients",
        ingredient, IngredientDto.class);
  }

  /*
   * Alternate implementations...
   * The next two methods are alternative implementations of
   * createIngredient() as shown in chapter 6. If you'd like to try
   * any of them out, comment out the previous method and uncomment
   * the variant you want to use.
   */
  /*
  public java.net.URI createIngredient(Ingredient ingredient) {
    return rest.postForLocation("http://localhost:8080/ingredients",
        ingredient);
  }
  */

  /*
  public Ingredient createIngredient(Ingredient ingredient) {
    ResponseEntity<Ingredient> responseEntity =
           rest.postForEntity("http://localhost:8080/ingredients",
                              ingredient,
                              Ingredient.class);
    log.info("New resource created at {}",
             responseEntity.getHeaders().getLocation());
    return responseEntity.getBody();
  }
  */

  //
  // DELETE examples
  //

  public void deleteIngredient(IngredientDto ingredient) {
    rest.delete("http://localhost:8080/ingredients/{id}",
        ingredient.getId());
  }

  //
  // Traverson with RestTemplate examples  
  // Commented as Traverson requires HATEOAS rel attribute in json
  //
/*
  public Iterable<IngredientDto> getAllIngredientsWithTraverson() {
    ParameterizedTypeReference<CollectionModel<IngredientDto>> ingredientType =
        new ParameterizedTypeReference<CollectionModel<IngredientDto>>() {};

    CollectionModel<IngredientDto> ingredientRes =
        traverson
          .follow("ingredients")
          .toObject(ingredientType);

    Collection<IngredientDto> ingredients = ingredientRes.getContent();
    return ingredients;
  }

  public IngredientDto addIngredient(IngredientDto ingredient) {
    String ingredientsUrl = traverson
        .follow("")
        .asLink()
        .getHref();

    return rest.postForObject(ingredientsUrl,
                              ingredient,
                              IngredientDto.class);
  }

  public Iterable<TacoDto> getRecentTacosWithTraverson() {
    ParameterizedTypeReference<CollectionModel<TacoDto>> tacoType =
        new ParameterizedTypeReference<CollectionModel<TacoDto>>() {};

    CollectionModel<TacoDto> tacoRes =
        traverson
          .follow("tacos")
          .follow("recents")
          .toObject(tacoType);

    Collection<TacoDto> tacos = tacoRes.getContent();
    // Alternatively, list the two paths in the same call to follow()
    
    CollectionModel<Taco> tacoRes =
       traverson
         .follow("tacos", "recents")
         .toObject(tacoType);
    
    return tacos;
  }
*/
}