package com.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.model.UserModel;


@Component
public class UserDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Transactional
	public void insertUser(UserModel u) {
		this.hibernateTemplate.saveOrUpdate(u);
	}
	@Transactional
	public void delete(UserModel u) {
		System.out.println("delete called");
		this.hibernateTemplate.delete(u);
	}
	
	public UserModel getDataById(int id) {
		return this.hibernateTemplate.get(UserModel.class, id);
	}
	
	public List<UserModel> getAllUser() {
		return this.hibernateTemplate.loadAll(UserModel.class);
	}
}