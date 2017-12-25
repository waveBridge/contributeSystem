package cn.cs.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.cs.entity.User;

public class UserDaoImpl implements UserDao {
	
	private HibernateTemplate hibernateTemplate; 
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//查询用户
	@Override
	public int searchUser(String username, String password) {
		System.out.println("searchUer...dao...");
		
		try{
			@SuppressWarnings("unchecked")
			List<User> userList = (List<User>)hibernateTemplate.find("from User where username = ? and password = ?", username, password);
			if(userList.size() == 0){
				return 0;
			} else {
				return userList.get(0).getUid();
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return 0;
		}
		
	}
	
}
