package com.jeehx.modules.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jeehx.common.persistence.CrudDao;
import com.jeehx.modules.demo.entity.Demo;

@Mapper
public interface DemoDao extends CrudDao<Demo> {

}
