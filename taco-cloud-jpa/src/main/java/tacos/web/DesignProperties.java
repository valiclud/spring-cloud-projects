package tacos.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Component
@ConfigurationProperties(prefix="taco.designs")
@Data
@Validated
public class DesignProperties {
  
  @Min(value=2, message="must be between 2 and 25")
  @Max(value=25, message="must be between 2 and 25")
  private int pageSize = 2;
 
}