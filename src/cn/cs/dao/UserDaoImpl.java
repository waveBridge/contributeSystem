package cn.cs.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.cs.entity.Material;
import cn.cs.entity.User;

public class UserDaoImpl implements UserDao {
	
	private HibernateTemplate hibernateTemplate; 
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//根据用户名密码查询用户
	@Override
	public int searchUser(String username, String password) {
		System.out.println("searchUser...dao...");
		
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

	//根据用户名查询用户
	@Override
	public int searchUser(String username) {
		System.out.println("searchUser...dao...");
		try{
			@SuppressWarnings("unchecked")
			List<User> userList = (List<User>)hibernateTemplate.find("from User where username = ?", username);
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

	//添加用户
	@Override
	public int addUser(User user) {
		System.out.println("addUser...dao...");
		try{
			int flag = (int)hibernateTemplate.save(user);
			return flag;
		}catch (Exception e) {
			System.out.println(e.toString());
			return 0;
		}
		
	}

	//根据用户id获取材料
	@Override
	public Set<Material> getMaterials(int uid) {
		System.out.println("getMaterials...dao...");
		
		try{
			User user = hibernateTemplate.get(User.class, uid);
			if(user == null) {
				return null;
			} else {
				Set<Material> materialSet = user.getMaterialSet();
				return materialSet;
			}
	
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	//上传文件
	@Override
	public boolean upFile(int uid, Material material) {
		System.out.println("upFile...dao...");
		
		try{
			User user = hibernateTemplate.get(User.class, uid);
			user.getMaterialSet().add(material);
			material.setUser(user);
			hibernateTemplate.save(user);
			hibernateTemplate.save(material);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}

	}
	
}
