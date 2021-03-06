package cn.cs.service;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;   //注意事务的配置引入的包一定不要错

import cn.cs.dao.AdminDao;
import cn.cs.dao.MaterialDao;
import cn.cs.entity.Material;
import cn.cs.entity.User;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@Transactional
public class AdminService {
	private AdminDao adminDao;
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	//管理员登录
	public boolean login(String adminName, String password) {
		System.out.println("adminLogin...service...");
		
		try{
			int aid = adminDao.searchAdmin(adminName, password);
			System.out.println("aid == " + aid);
			if(aid == 0){
				return false;
			} else {
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.removeAttribute("uid");
				session.setAttribute("aid", aid);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}

	}

	//获取所有的材料
	public List<Material> getAllMaterial() {
		System.out.println("getAllMaterial...service...");
		
		try{
			List<Material> materialList = adminDao.getAllMaterial();
			return materialList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

	}

	//通过作者获取稿件
	public Set<Material> getMaterialByAuthor(String nickname) {
		System.out.println("getMaterialByAuthor...service...");
		
		try{
			Set<Material> materialSet = adminDao.getMaterialByNickname(nickname);
			return materialSet;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	//根据状态获取稿件
	public List<Material> getMaterialByState(String sstate) {
		System.out.println("getMaterialByState...service...");
		
		try{
			int state = Integer.parseInt(sstate);
			List<Material> materialList = adminDao.getMaterialByState(state);
			return materialList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	//根据材料名进行模糊查询
	public List<Material> getMaterialByName(String materialName) {
		System.out.println("getMaterialByName...service...");
		
		try{
			List<Material> materialSet = adminDao.getMaterialByName(materialName);
			return materialSet;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}		
	}
}
