package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "login";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/submitLogin")
	public String loginSuccess(@Validated User user, BindingResult brequest, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		List<User> users = (List<User>) session.getAttribute("uList");
		model.addAttribute("userList", users);
		return "loginSuccess";
	}
}
