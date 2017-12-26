package cn.cs.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.cs.entity.Material;
import cn.cs.entity.User;

public class MaterialDaoImpl implements MaterialDao {
	private HibernateTemplate hibernateTemplate; 
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//获取文件的url
	@Override
	public String getMUrl(int mid) {
		System.out.println("getMUrl...dao...");
		
		try{
			Material material = hibernateTemplate.get(Material.class, mid);
			return material.getUrl();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

	}

	//获取投稿的名字
	@Override
	public String getMName(int mid) {
		System.out.println("getMName...dao...");
		
		try{
			Material material = hibernateTemplate.get(Material.class, mid);
			return material.getMaterialName();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	
}
