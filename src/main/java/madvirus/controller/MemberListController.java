package madvirus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import madvirus.dao.MemberDao;
import madvirus.model.Member;
import madvirus.model.command.ListCommand;

@Controller
public class MemberListController {

	private MemberDao memberDao;

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@RequestMapping("/memberList")
	public String listAll(Model model, ListCommand listCommand) {
		List<Member> members = memberDao.selectAll();
		model.addAttribute("members", members);
		return "member/memberList";
	}
	
	@RequestMapping("/members")
	public String list(ListCommand listCommand, Errors errors, Model model) {
		if(errors.hasErrors()) return "member/memberList";
		if(listCommand.getFrom() != null && listCommand.getTo() != null) {
			List<Member> members = memberDao.selectByRegdate(listCommand.getFrom(), listCommand.getTo());
			model.addAttribute("members", members);
		}
		
		return "member/memberList";
	}
	
}
