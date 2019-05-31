package com.jeehx.modules.demo.entity;

import com.jeehx.common.persistence.DataEntity;

import lombok.Getter;
import lombok.Setter;


public class Demo extends DataEntity<Demo> {
	private static final long serialVersionUID = 1L;
	@Getter @Setter private String name;
	
}
