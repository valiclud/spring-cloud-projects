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
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/allOrdersForm")
@SessionAttributes("tacoOrders")
public class AllOrdersController {

	
	private OrderRepository orderRepo;
	 
	@Autowired
	public AllOrdersController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@ModelAttribute(name="allOrders")
	public List<TacoOrder> order() {
		return new ArrayList<>();
	}

	//@ModelAttribute
	@GetMapping
	public String allOrdersForm(Model model, @RequestParam("page") Optional<Integer> page, 
      @RequestParam("size") Optional<Integer> size) {
		Iterable <TacoOrder> tacoOrders = orderRepo.findAll();
		List<TacoOrder> ordersList = Streamable.of(tacoOrders).toList();
		this.addPaginationAttributes(model, page, size, ordersList);
		
		return "allOrdersForm";
	}
	
	private void addPaginationAttributes(Model model, Optional<Integer> page,
			Optional<Integer> size, List<TacoOrder> ordersList) {
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(2);
		Page<TacoOrder> orderPage = this.findOrderPage(PageRequest.of(currentPage - 1, pageSize), ordersList);
		
		model.addAttribute("orderPage", orderPage);

        int totalPages = orderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
	}
	
	private Page<TacoOrder> findOrderPage(Pageable pageable, List<TacoOrder> orders) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TacoOrder> list;

        if (orders.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, orders.size());
            list = orders.subList(startItem, toIndex);
        }

        Page<TacoOrder> orderPage
          = new PageImpl<TacoOrder>(list, PageRequest.of(currentPage, pageSize), orders.size());

        return orderPage;
    }
}
