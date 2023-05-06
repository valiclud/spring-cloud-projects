package tacos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.data.TacoService;
import tacos.dto.TacoDto;
import tacos.web.DesignProperties;

@Controller
@RequestMapping("/allDesignsForm")
@SessionAttributes("tacoDesigns")
public class AllDesignController {

  private final TacoService tacoService;
  private final DesignProperties designProperties;

  @Autowired
  public AllDesignController(TacoService tacoService, DesignProperties designProperties) {
    this.tacoService = tacoService;
    this.designProperties = designProperties;
  }
  
  @ModelAttribute(name="allDesigns")
  public List<TacoDto> order() {
    return new ArrayList<>();
  }

  @GetMapping
  public String allOrdersForm(Model model, @RequestParam("page") Optional<Integer> page, 
      @RequestParam("size") Optional<Integer> size) {
    List<TacoDto> tacoOrders = tacoService.findAll();
    this.addPaginationAttributes(model, page, size, tacoOrders);
    
    return "allDesignsForm";
  }
  
  private void addPaginationAttributes(Model model, Optional<Integer> page,
      Optional<Integer> size, List<TacoDto> tacosList) {
    int currentPage = page.orElse(1);
        int pageSize = size.orElse(this.designProperties.getPageSize());
    Page<TacoDto> tacoPage = this.findOrderPage(PageRequest.of(currentPage - 1, pageSize), tacosList);
    
    model.addAttribute("designPage", tacoPage);

        int totalPages = tacoPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
  }
  
  private Page<TacoDto> findOrderPage(Pageable pageable, List<TacoDto> orders) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TacoDto> list;

        if (orders.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, orders.size());
            list = orders.subList(startItem, toIndex);
        }

        Page<TacoDto> orderPage
          = new PageImpl<TacoDto>(list, PageRequest.of(currentPage, pageSize), orders.size());

        return orderPage;
    }
}
