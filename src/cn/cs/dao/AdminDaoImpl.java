package cn.cs.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.cs.entity.Admin;
import cn.cs.entity.Material;
import cn.cs.entity.User;

public class AdminDaoImpl implements AdminDao {
	private HibernateTemplate hibernateTemplate; 
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//根据账号密码查询
	@Override
	public boolean searchAdmin(String adminName, String password) {
		System.out.println("searchAdmin...dao...");
		try{
			@SuppressWarnings("unchecked")
			List<Admin> adminList = (List<Admin>) hibernateTemplate.find("from Admin where adminName = ? and password = ?", adminName,password);
			if(adminList.size() == 0){
				return false;
			} else{
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}

	}

	//获取所有的材料
	@Override
	public List<Material> getAllMaterial() {	
		System.out.println("getAllMaterial...dao...");
		
		try{
			@SuppressWarnings("unchecked")
			List<Material> materialList = (List<Material>) hibernateTemplate.find("from Material");
			return materialList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	//根据nickname获取稿件
	@Override
	public Set<Material> getMaterialByNickname(String nickname) {
		System.out.println("getMaterialByNickname...dao...");
		
		try{
			@SuppressWarnings("unchecked")
			List<User> userList = (List<User>) hibernateTemplate.find("from User where nickname = ?", nickname);
			if(userList == null || userList.size() == 0){
				return null;									//没找到或错误
			} else {
				Set<Material> materialSet = userList.get(0).getMaterialSet();
				for(int i = 1; i < userList.size();i ++){
					materialSet.addAll(userList.get(i).getMaterialSet());
				}
				return materialSet;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	//通过状态获取稿件
	@Override
	public List<Material> getMaterialByState(int state) {
		System.out.println("getMaterialByState...dao...");
		
		try{
			@SuppressWarnings("unchecked")
			List<Material> materialList = (List<Material>) hibernateTemplate.find("from Material where state = ?", state);
			return materialList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
}
