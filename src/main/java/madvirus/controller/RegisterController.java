package madvirus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import madvirus.exception.DuplicateMemberException;
import madvirus.model.command.RegisterCommand;
import madvirus.model.validator.RegisterCommandValidator;
import madvirus.service.MemberRegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private MemberRegisterService memberRegisterService;

	@Autowired
	public RegisterController(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	@GetMapping("/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	@GetMapping("/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}
	
	@PostMapping("/step2")
	public String handleStep2(RegisterCommand registerCommand, boolean agree) {
		if(!agree) return "register/step1";
		return "register/step2";
	}
	
	@PostMapping("/step3")
	public String handleStep3(RegisterCommand registerCommand, Errors errors) {
		new RegisterCommandValidator().validate(registerCommand, errors);
		if(errors.hasErrors()) return "register/step2";
		
		try {
			memberRegisterService.register(registerCommand);
		} catch (DuplicateMemberException e) {
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
		return "register/step3";
	}
	
	
}
