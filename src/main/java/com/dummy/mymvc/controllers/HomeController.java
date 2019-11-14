package com.dummy.mymvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dummy.mymvc.dao.MyTableDao;
import com.dummy.mymvc.entities.MyTable;

@Controller
public class HomeController {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private MyTableDao dao;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("msg", "msg from controller");
		model.addAttribute("appname", applicationContext.getDisplayName());
		return "home";
	}

	@GetMapping("/new")
	public String newPage(Model model) {
		model.addAttribute("msg", "this is a new page creatd");
		model.addAttribute("appname", applicationContext.getDisplayName());
		return "new";
	}
	
	@GetMapping("/add")
	public String addEntry(Model model) {
		MyTable myTableData = new MyTable(1, "my name");
		this.dao.insertRecord(myTableData);
		model.addAttribute("msg","data has been inserted, check db");
		return "add";
	}

}
