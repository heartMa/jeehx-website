/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeehx.com/">Jeehx</a> All rights reserved.
 */
package com.jeehx.modules.sys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeehx.common.web.BaseController;

/**
 * 登录Controller
 * @author jeehx
 * @version 2013-5-31
 */
@Controller
public class LoginController  extends BaseController{
	@RequestMapping(value = "login")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		return this.render("login");
	}
}
