package tacos.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ClientDto implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Long Id;
  
  private Date createdAt = new Date();

  private String deliveryName;
  private String deliveryStreet;
  private String deliveryCity;
  private String deliveryState;
  private String deliveryZip;

}
