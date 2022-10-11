package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.dao.UserDao;
import com.model.UserModel;


@Controller
public class MyController {
	@Autowired
	private UserDao dao;
	
	@RequestMapping("/")
	public String index(Model m) {
		List<UserModel> list = dao.getAllUser();
		m.addAttribute("list", list);
		return "index";
	}
	@RequestMapping("register")
	public String registerUser() {
		return "register";
	}
	@RequestMapping(value="submituser",method = RequestMethod.POST)
	public RedirectView register(@ModelAttribute UserModel u,HttpServletRequest request) {
		this.dao.insertUser(u);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	@RequestMapping(value="update/{id}")
	public String update(@PathVariable int id,Model m) {
		UserModel u = this.dao.getDataById(id);
		m.addAttribute("u", u);
		return "update";
	}
	@RequestMapping(value="delete/{id}")
	public RedirectView delete(@PathVariable int id,HttpServletRequest request) {
		UserModel u = this.dao.getDataById(id);
		this.dao.delete(u);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
}
