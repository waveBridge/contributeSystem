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
}
