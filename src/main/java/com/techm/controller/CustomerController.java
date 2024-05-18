package com.techm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.techm.entity.Biller;
import com.techm.entity.Customer;
import com.techm.entity.Orders;
import com.techm.entity.Product;
import com.techm.service.BillerService;
import com.techm.service.CustomerService;
import com.techm.service.OrderService;
import com.techm.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BillerService billerService;

	@Autowired
	ObjectFactory<HttpSession> httpSessionFactory;

	@GetMapping("/register")
	public ModelAndView register(ModelAndView modelAndView) {
		modelAndView.addObject("customer", new Customer());
		modelAndView.setViewName("customer/register");
		return modelAndView;
	}

	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute("customer") Customer customer, ModelAndView modelAndView) {
		Customer registeredCustomer = customerService.registerCustomer(customer);
		if (registeredCustomer == null) {
			modelAndView.addObject("message", "Email already exists!");
			modelAndView.setViewName("customer-register");
		} else {
			modelAndView.addObject("message", "Customer registered successfully");
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@GetMapping("/dashboard")
	public ModelAndView dashboard(ModelAndView modelAndView) {
		List<Product> products = productService.getAllProducts();
		Collections.reverse(products);
		modelAndView.addObject("products", products);
		modelAndView.setViewName("customer/dashboard");
		return modelAndView;
	}

	@GetMapping("/profile")
	public ModelAndView profile(ModelAndView modelAndView) {
		modelAndView.setViewName("customer/profile");
		return modelAndView;
	}

	@GetMapping("update-profile")
	public ModelAndView updateProfile(ModelAndView modelAndView) {
		modelAndView.setViewName("customer/update-profile");
		return modelAndView;
	}

	@PostMapping("/update-profile")
	public ModelAndView updateProfile(@RequestParam("name") String name, @RequestParam("email") String email,
			ModelAndView modelAndView, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		customer.setName(name);
		customer.setEmail(email);
		Customer updatedCustomer = customerService.updateCustomer(customer);
		modelAndView.addObject("message", "Profile updated successfully");
		session.setAttribute("customer", updatedCustomer);
		modelAndView.setViewName("customer/profile");
		return modelAndView;
	}

	@GetMapping("/change-password")
	public ModelAndView changePassword(ModelAndView modelAndView) {
		modelAndView.setViewName("customer/change-password");
		return modelAndView;
	}

	@PostMapping("/change-password")
	public ModelAndView changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, HttpSession session, ModelAndView modelAndView) {

		Customer customer = (Customer) session.getAttribute("customer");

		if (customer.getPassword().equals(oldPassword)) {
			customer.setPassword(newPassword);
			customerService.updateCustomer(customer);
			modelAndView.addObject("message", "Password changed successfully.");
		} else {
			modelAndView.addObject("message", "Entered password is incorrect.");
		}
		modelAndView.setViewName("customer/profile");
		return modelAndView;
	}

	@GetMapping("all-billers")
	public ModelAndView allBillers(HttpSession session, ModelAndView modelAndView) {
		List<Biller> billers = billerService.getAllBillers();
		modelAndView.addObject("billers", billers);
		modelAndView.setViewName("customer/all-billers");
		return modelAndView;
	}

	@GetMapping("/view-biller-products")
	public ModelAndView viewBillerProducts(@RequestParam("billerId") Long billerId, ModelAndView modelAndView) {
		List<Product> products = productService.getAllActiveProductsByBiller(billerService.getBillerById(billerId));
		modelAndView.addObject("products", products);
		modelAndView.setViewName("customer/view-biller-products");
		return modelAndView;
	}

	@GetMapping("/buy")
	public ModelAndView buyProduct(@RequestParam("productId") Long productId, HttpSession session,
			ModelAndView modelAndView) {
		Customer customer = (Customer) session.getAttribute("customer");
		orderService.buyProduct(customer.getCustomerId(), productId);
		modelAndView.setViewName("redirect:/customer/my-orders");
		return modelAndView;
	}

	@GetMapping("/my-orders")
	public ModelAndView myOrders(HttpSession session, ModelAndView modelAndView) {
		Customer customer = (Customer) session.getAttribute("customer");
		List<Orders> orders = orderService.getOrderByCustomer(customer.getCustomerId());
		modelAndView.addObject("orders", orders);
		modelAndView.setViewName("customer/my-orders");
		return modelAndView;
	}

	@GetMapping("/view-order")
	public ModelAndView viewOrders(@RequestParam("orderId") Long orderId, ModelAndView modelAndView) {
		Orders orders = orderService.getOrderById(orderId);
		modelAndView.addObject("orders", orders);
		modelAndView.setViewName("customer/view-order");
		return modelAndView;
	}

	@GetMapping("/all-products")
	public ModelAndView allProducts(ModelAndView modelAndView) {
		List<Product> products = productService.getAllProducts();
		modelAndView.addObject("products", products);
		modelAndView.setViewName("customer/all-products");
		return modelAndView;
	}

	@GetMapping("/registered-billers")
	public ModelAndView registeredBillers(HttpSession session, ModelAndView modelAndView) {
		Customer customer = (Customer) session.getAttribute("customer");
		List<Biller> billers = customerService.registeredBillers(customer.getCustomerId());
		modelAndView.addObject("billers", billers);
		modelAndView.setViewName("customer/registered-billers");
		return modelAndView;
	}

	@GetMapping("/approved-orders")
	public ModelAndView approvedOrders(HttpSession session, ModelAndView modelAndView) {
		Customer customer = (Customer) session.getAttribute("customer");
		List<Orders> orders = orderService.getOrdersByCustomerAndStatus(customer.getCustomerId(), "Approved");
		modelAndView.addObject("orders", orders);
		modelAndView.setViewName("customer/my-orders");
		return modelAndView;
	}
	
	@GetMapping("/pending-orders")
	public ModelAndView pendingOrders(HttpSession session, ModelAndView modelAndView) {
		Customer customer = (Customer) session.getAttribute("customer");
		List<Orders> orders = orderService.getOrdersByCustomerAndStatus(customer.getCustomerId(), "Pending");
		modelAndView.addObject("orders", orders);
		modelAndView.setViewName("customer/my-orders");
		return modelAndView;
	}
	@GetMapping("/rejected-orders")
	public ModelAndView rejectedOrders(HttpSession session, ModelAndView modelAndView) {
		Customer customer = (Customer) session.getAttribute("customer");
		List<Orders> orders = orderService.getOrdersByCustomerAndStatus(customer.getCustomerId(), "Rejected");
		modelAndView.addObject("orders", orders);
		modelAndView.setViewName("customer/my-orders");
		return modelAndView;
	}

}
