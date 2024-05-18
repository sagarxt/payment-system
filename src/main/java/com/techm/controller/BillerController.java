package com.techm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.techm.entity.Biller;
import com.techm.entity.BillerDashDTO;
import com.techm.entity.Customer;
import com.techm.entity.Orders;
import com.techm.entity.Product;
import com.techm.enums.BillerCategory;
import com.techm.service.BillerService;
import com.techm.service.OrderService;
import com.techm.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/biller")
public class BillerController {

    @Autowired
    private BillerService billerService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
    	modelAndView.addObject("biller", new Biller());
        modelAndView.setViewName("biller/register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("biller") Biller biller, ModelAndView modelAndView) {
        Biller registeredBiller = billerService.registerBiller(biller);
        if (registeredBiller == null) {
            modelAndView.addObject("message", "Email already exists");
            modelAndView.setViewName("biller/register");
        } else {
            modelAndView.addObject("message", "Biller registered successfully");
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
    
    @GetMapping("/dashboard")
    public ModelAndView dashboard(HttpSession session, ModelAndView modelAndView) {
    	Biller biller = (Biller) session.getAttribute("biller");
    	if(biller != null) {
    	BillerDashDTO bdd = billerService.billerMoreInfo(biller.getBillerId());
    	modelAndView.addObject("bdd", bdd);
    	modelAndView.setViewName("biller/dashboard");
    	}else {
    		modelAndView.setViewName("redirect:/home");
    	}
    	return modelAndView;
    }
    
    @GetMapping("/profile")
    public ModelAndView profile(ModelAndView modelAndView) {
    	modelAndView.setViewName("biller/profile");
    	return modelAndView;
    }
    
    @GetMapping("update-profile")
    public ModelAndView updateProfile(ModelAndView modelAndView) {
    	modelAndView.setViewName("biller/update-profile");
    	return modelAndView;
    }
    
    @PostMapping("update-profile")
    public ModelAndView updateProfile(HttpSession session,@RequestParam("billerName") String billerName, 
    		@RequestParam("email") String email, @RequestParam("about") String about, 
    		@RequestParam("category") String category, ModelAndView modelAndView) { 
    	
    	Biller biller = (Biller) session.getAttribute("biller");
    	biller.setBillerName(billerName);
    	biller.setEmail(email);
    	biller.setAbout(about);
    	biller.setCategory(BillerCategory.valueOf(category));
    	
        Biller updatedBiller = billerService.updateBiller(biller);
        modelAndView.addObject("message", "Profile updated successfully");
        session.setAttribute("biller", updatedBiller);
        modelAndView.setViewName("biller/profile");
        return modelAndView;
    }
    
    @GetMapping("/change-password")
    public ModelAndView changePassword(ModelAndView modelAndView) {
    	modelAndView.setViewName("biller/change-password");
    	return modelAndView;
    }
    
    @PostMapping("/change-password")
    public ModelAndView changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpSession session ,ModelAndView modelAndView) {
    	
    	Biller biller = (Biller) session.getAttribute("biller");

        if(biller.getPassword().equals(oldPassword)) {
        	biller.setPassword(newPassword);
        	biller = billerService.updateBiller(biller);
            modelAndView.addObject("message", "Password changed successfully.");
        } else {
            modelAndView.addObject("message", "Entered password is incorrect.");
        }

        modelAndView.setViewName("biller/profile");
        return modelAndView;
    }
    
    @GetMapping("/add-product")
    public ModelAndView addProduct(ModelAndView modelAndView) {
    	modelAndView.addObject("product", new Product());
    	modelAndView.setViewName("biller/add-product");
    	return modelAndView;
    }
    
    @PostMapping("/add-product")
    public ModelAndView addProduct(@ModelAttribute("product") Product product, HttpSession session, ModelAndView modelAndView) {
    	Biller biller = (Biller) session.getAttribute("biller");
    	product.setBiller(biller);
    	product.setActive(true);
    	productService.addProducct(product);
    	modelAndView.setViewName("redirect:/biller/dashboard");
    	return modelAndView;
    }
    
    @GetMapping("/view-products")
    public ModelAndView viewProducts(HttpSession session, ModelAndView modelAndView) {
    	Biller biller = (Biller) session.getAttribute("biller");
    	List<Product> products= productService.getAllProductsByBiller(biller);
    	modelAndView.addObject("products", products);
    	modelAndView.setViewName("biller/view-products");
    	return modelAndView;
    }

    @GetMapping("/edit-product")
    public ModelAndView editProduct(@RequestParam("productId") Long productId, ModelAndView  modelAndView) {
    	Product product = productService.getProductById(productId);
    	modelAndView.addObject("product", product);
    	modelAndView.setViewName("biller/edit-product");
    	return modelAndView;
    }
    
    @PostMapping("/edit-product")
    public ModelAndView editProduct(@ModelAttribute("product") Product product, ModelAndView modelAndView) {
    	Product updateProduct = productService.getProductById(product.getProductId());
    	updateProduct.setName(product.getName());
    	updateProduct.setPrice(product.getPrice());
    	updateProduct.setDescription(product.getDescription());
    	updateProduct.setCategory(product.getCategory());
    	productService.updateProduct(updateProduct);
    	
    	modelAndView.setViewName("redirect:/biller/view-products");
    	return modelAndView;
    }
    
    @GetMapping("/delete-product")
    public ModelAndView deleteProduct(@RequestParam("productId") Long productId, ModelAndView modelAndView) {
    	productService.deleteProduct(productId);
    	modelAndView.setViewName("redirect:/biller/view-products");
    	return modelAndView;
    }
    
    @GetMapping("/active-product")
    public ModelAndView activeProduct(@RequestParam("productId") Long productId, ModelAndView modelAndView) {
    	productService.activeProduct(productId);
    	modelAndView.setViewName("redirect:/biller/view-products");
    	return modelAndView;
    }
    
    @GetMapping("/pending-orders")
    public ModelAndView pendingOrders(HttpSession session, ModelAndView modelAndView) {
    	Biller biller = (Biller) session.getAttribute("biller");
    	List<Orders> orders = orderService.pendingOrdersByBiller(biller.getBillerId());
    	modelAndView.addObject("orders", orders);
    	modelAndView.setViewName("biller/pending-orders");
    	return modelAndView;
    }
    
    @GetMapping("/accept-order")
    public ModelAndView acceptOrder(@RequestParam("orderId") Long orderId, ModelAndView modelAndView) {
    	orderService.acceptOrder(orderId);
    	modelAndView.setViewName("redirect:/biller/pending-orders");
    	return modelAndView;
    }
    
    @GetMapping("/reject-order")
    public ModelAndView rejectOrder(@RequestParam("orderId") Long orderId, ModelAndView modelAndView) {
    	orderService.rejectOrder(orderId);
    	modelAndView.setViewName("redirect:/biller/pending-orders");
    	return modelAndView;
    }
    
    @GetMapping("/all-orders")
    public ModelAndView allOrders(HttpSession session, ModelAndView modelAndView) {
    	Biller biller = (Biller) session.getAttribute("biller");
    	List<Orders> orders = orderService.allOrdersByBiller(biller.getBillerId());
    	modelAndView.addObject("orders", orders);
    	modelAndView.setViewName("biller/all-orders");
    	return modelAndView;
    }
    
    @GetMapping("/view-order")
    public ModelAndView viewOrders(@RequestParam("orderId") Long orderId, ModelAndView modelAndView) {
    	Orders orders = orderService.getOrderById(orderId);
    	modelAndView.addObject("orders", orders);
    	modelAndView.setViewName("biller/view-order");
    	return modelAndView;
    }
    
    @GetMapping("/registered-customers")
	public ModelAndView registeredCustomers(HttpSession session, ModelAndView modelAndView) {
    	Biller biller = (Biller) session.getAttribute("biller");
		List<Customer> customers = billerService.registeredCustomers(biller.getBillerId());
		modelAndView.addObject("customers", customers);
		modelAndView.setViewName("biller/registered-customers");
		return modelAndView;
	}
    
    @GetMapping("/customer-orders")
    public ModelAndView customerOrders(HttpSession session, @RequestParam("customerId") Long customerId, ModelAndView modelAndView) {
    	Biller biller = (Biller) session.getAttribute("biller");
    	List<Orders> customerOrders = orderService.getOrderByCustomer(customerId);
    	List<Orders> orders = new ArrayList<>();
    	for(Orders order : customerOrders) {
    		if(order.getProduct().getBiller().getBillerId() == biller.getBillerId()) {
    			orders.add(order);
    		}
    	}
    	modelAndView.addObject("orders", orders);
    	modelAndView.setViewName("biller/customer-orders");
    	return modelAndView;
    }
}

