package com.koped.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("")
public class AuthController {
	
<<<<<<< HEAD
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
=======
//	@GetMapping("/login")
//	public String loginPage() {
//		return "Login";
//	}
//	
//@GetMapping("/home")
//	public String homePage() {
//		return "Home";
//	}
>>>>>>> 71f23512ba055c9870e669fc73720aa74c40c1b8

}
