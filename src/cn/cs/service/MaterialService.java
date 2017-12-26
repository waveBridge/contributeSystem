package cn.cs.service;

import java.io.File;
import java.util.Calendar;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;   //注意事务的配置引入的包一定不要错

import cn.cs.dao.MaterialDao;
import cn.cs.entity.Material;
import cn.cs.entity.User;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@Transactional
public class MaterialService {
	private MaterialDao materialDao;
	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	//根据id获取文件的url
	public String getMUrl(String mmid) {
		System.out.println("getMUrl...service...");
		
		try{
			int mid = Integer.parseInt(mmid);
			String mUrl = materialDao.getMUrl(mid);
			return mUrl;
					
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	//获取文件名
	public String getMName(String mmid) {
		System.out.println("getMName...service...");
		
		try{
			int mid = Integer.parseInt(mmid);
			String mName = materialDao.getMName(mid);
			return mName;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	
	
}
