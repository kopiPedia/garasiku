package com.koped.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("")
public class AuthController {
	
	@GetMapping("/login")
	public String loginPage() {
		return "sign-in-basic";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "sign-up-basic";
	}
	
	@GetMapping("/home")
	public String homePage() {
		return "sign-up-basic";
	}

}
