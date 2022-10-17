package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Pwd;
import com.example.demo.service.CheckService;

@Controller
public class IndexController {
	@Autowired
	CheckService testSrevice;

	@GetMapping("/form")
	public String form(Model model) {
		Pwd pwd = new Pwd();
		model.addAttribute("pwd", pwd);
		return "form";
	}

	@PostMapping("/check")
	public String add(@ModelAttribute Pwd pwd, Model model) {
		model.addAttribute("res", testSrevice.checkPass(pwd.getPassword()));
		model.addAttribute("pwd", pwd);
		return "checkResult";
	}
}
