package madvirus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("")
	public String home() {
		return "home";
	}
	
	@GetMapping("/member_temp/edit")
	public String memberPage() {
		return "member/edit";
	}
	
}
