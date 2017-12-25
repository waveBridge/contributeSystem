package cn.cs.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;

public class UserDaoImpl implements UserDao {
	
	private HibernateTemplate hibernateTemplate; 
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//查询用户
	@Override
	public int searchUser(String username, String password) {
		System.out.println("searchUer...dao...");
		
		
		// TODO 自动生成的方法存根
		return 0;
	}
	
}
