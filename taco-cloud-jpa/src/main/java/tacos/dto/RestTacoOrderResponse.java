package tacos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter // All lombok annotations
@Getter
@ToString
public class RestTacoOrderResponse<T> {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;

    private T data;
}
