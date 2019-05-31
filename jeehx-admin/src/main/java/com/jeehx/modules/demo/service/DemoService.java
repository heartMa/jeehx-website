package com.jeehx.modules.demo.service;

import org.springframework.stereotype.Service;

import com.jeehx.common.service.CrudService;
import com.jeehx.modules.demo.dao.DemoDao;
import com.jeehx.modules.demo.entity.Demo;
@Service
public class DemoService  extends CrudService<DemoDao, Demo> {
	
}
