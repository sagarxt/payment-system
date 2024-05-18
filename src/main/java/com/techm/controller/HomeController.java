package com.techm.controller;

import com.techm.entity.Biller;
import com.techm.entity.Customer;
import com.techm.service.BillerService;
import com.techm.service.CustomerService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BillerService billerService;

    @GetMapping
    public ModelAndView home(ModelAndView model) {
        model.setViewName("home");
        return model;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
    	modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, ModelAndView modelAndView) {
        Customer customer = customerService.loginCustomer(email, password);
        Biller biller = billerService.loginBiller(email, password);
        if (customer != null) {
        	session.setAttribute("customer", customer);
        	modelAndView.setViewName("redirect:/customer/dashboard");
        } else if (biller != null) {
        	session.setAttribute("biller", biller);
        	modelAndView.setViewName("redirect:/biller/dashboard");
        } else {
        	modelAndView.addObject("message", "Invalid email or password");
        	modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {
    	modelAndView.clear();
    	session.invalidate();
    	modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }
}
