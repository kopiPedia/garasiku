package com.koped.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("")
public class AuthController {
<<<<<<< HEAD
	
<<<<<<< HEAD
=======

>>>>>>> a763272e1b91ffe89082f9c1ced6650a465eb8fb
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
<<<<<<< HEAD
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
=======
>>>>>>> a763272e1b91ffe89082f9c1ced6650a465eb8fb

}
