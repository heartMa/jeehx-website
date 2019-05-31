package com.jeehx.modules.demo.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeehx.common.web.BaseController;
import com.jeehx.modules.demo.entity.Demo;
import com.jeehx.modules.demo.service.DemoService;
@Controller
@RequestMapping(value="demo")
public class DemoController extends BaseController{
	@Autowired
	private DemoService demoService;
	@RequiresPermissions("sys:user:list")
	@RequestMapping(value="")
	public String index() {
		System.out.println("####################");
		Demo demo = new Demo();
		demo.setName("test");
		demo.setRemarks("remark");
		demo.setDelFlag("1");
		demo.setIsNewRecord(true);
		demoService.save(demo);
		return this.render("demo");
	}
	@RequestMapping(value="index")
	public String index1(ModelMap map) {
		System.out.println("####################");
		map.put("navTitle","控制台");
		//int m = 1/0;
		return this.render("index");
	}
	
	@RequestMapping(value="home")
	public String main(ModelMap map) {
		System.out.println("####################");
		//int m = 1/0;
		return this.render("main");
	}
}
