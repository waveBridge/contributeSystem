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

	//改变稿件
	@Override
	public boolean changeFile(int mid, String materialName, String rePath, String date) {
		System.out.println("changeFile...dao...");
		
		try{
			Material material = hibernateTemplate.get(Material.class, mid);
			material.setDate(date);
			material.setMaterialName(materialName);
			material.setState(0);
			material.setUrl(rePath);
			hibernateTemplate.update(material);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	//修改密码
	@Override
	public boolean changePass(int uid, String oldPass, String newPass) {
		System.out.println("changePass...dao...");
		
		try{
			User user = hibernateTemplate.get(User.class, uid);
			if(user.getPassword().equals(oldPass)){
				user.setPassword(newPass);
				hibernateTemplate.update(user);
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}		
	}

	//修改个人信息
	@Override
	public boolean changeInfo(String nickname, String address, String resume, int uid) {
		System.out.println("changeInfo...dao...");
		
		try{
			User user = hibernateTemplate.get(User.class, uid);
			user.setNickname(nickname);
			user.setAddress(address);
			user.setResume(resume);
			hibernateTemplate.update(user);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
}
