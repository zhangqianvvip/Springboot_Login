package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.UserList;
import com.example.demo.entity.User;

@Controller
public class SignUpController {
	
	@GetMapping("/signUp")
	public String signUp(@ModelAttribute("user")User user) {

		return "signup";
	}
	
	
	@PostMapping("/submitSignUp")
	public String submitSignUp(@Validated User user,BindingResult result,Model model,HttpServletRequest request) {
  
		if(!(user.getEmail().contains(user.getUsername()))) {
			FieldError error=new FieldError("user", "email", "Email必须包含用户名");
			result.addError(error);
			return "signup";
		}
		if(result.hasErrors()) {
			return "signup";
		}
		
		UserList.users.add(user);
		
		HttpSession session=request.getSession();
		session.setAttribute("uList", UserList.users);
		session.setAttribute("upUser", user);
		return "upSuccess";
	}
	
	@RequestMapping("/signupInteceptor")
	public String registered() {
		
		return "inteceptor";
	}

}
