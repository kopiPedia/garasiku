package com.koped.controller.auth;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koped.model.User;
import com.koped.repository.UserRepository;
import com.koped.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

	private final UserRepository userRepo;
	private final UserServiceImpl userService;

	@GetMapping("/")
	public String redirectToHomePage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userRepo.findByUsername(auth.getName());

			if (user.getRole().equals("Admin")) {
				return "main-admin";
			}
			if (user.getRole().equals("User")) {
				return "main-product";
			}
		}
		return "redirect:/home";
	}

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
		return "redirect:/product/products";
	}

	@GetMapping("/logout")
	public String customLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, RedirectAttributes ra) {
		try {
            userService.createUser(username, password, email);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            ra.addAttribute("error", e.getMessage());
            return "redirect:/register";
        }
	}

}
