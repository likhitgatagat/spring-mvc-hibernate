package com.cims.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cims.spring.entity.Login;
import com.cims.spring.service.UserService;
@SessionAttributes("login")
@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("login")
	public Login populateLogin() {
		return new Login();
	}
	 
	@RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
	public String init(@ModelAttribute("login") Login login) {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String submit(@ModelAttribute("login") Login login, Model model) {
		if(login != null && login.getUserName() != null && login.getPassword() != null) {
			System.out.println("Credentials Filled");
			if(userService.authenticateUser(login.getUserName(), login.getPassword())) {
				System.out.println("Vaslid");
				return "redirect:/user";
			}
			else {
				model.addAttribute("error", "Invalid Credentials");
				System.out.println("Invalid");
				return "login";
			}
		}
		else {
			model.addAttribute("error", "Please enter credentials");
			return "login";
		}
	}
	
}
