package madvirus.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import madvirus.exception.MemberNotFoundException;
import madvirus.exception.WrongIdPasswordException;
import madvirus.model.auth.AuthInfo;
import madvirus.model.auth.AuthService;
import madvirus.model.command.LoginCommand;

@Controller
public class AuthController {
	
	private AuthService authService;

	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping("/login")
	public String form(LoginCommand loginCommand, @CookieValue(value = "remember", required = false) Cookie rCookie) {
		if(rCookie != null) {
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response) {
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			session.setAttribute("authInfo", authInfo);
			
			Cookie rememberCookie = new Cookie("remember", loginCommand.getEmail());
			rememberCookie.setPath("/");
			
			if(loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60 * 60);
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			
		} catch (MemberNotFoundException e) {
			errors.reject("memberNotFound");
			return "login/loginForm";
		} catch (WrongIdPasswordException e) {
			errors.reject("passwordNotMatching");
			return "login/loginForm";
		}
		return "login/loginSuccess";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
