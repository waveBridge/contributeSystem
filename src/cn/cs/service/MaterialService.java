package cn.cs.service;

import java.io.File;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;   //注意事务的配置引入的包一定不要错

import cn.cs.dao.MaterialDao;
import cn.cs.dao.UserDao;
import cn.cs.entity.Material;
import cn.cs.entity.User;
import cn.cs.util.MailUtil;
import cn.cs.util.TimeUtil;
import javassist.bytecode.stackmap.BasicBlock.Catch;

@Transactional
public class MaterialService {
	private MaterialDao materialDao;
	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}
	
	private MailUtil mailUtil;
	private TimeUtil timeUtil;
	public void setMailUtil(MailUtil mailUtil) {
		this.mailUtil = mailUtil;
	}
	public void setTimeUtil(TimeUtil timeUtil) {
		this.timeUtil = timeUtil;
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

	
	//改变稿件的状态
	public boolean changeState(String mmid, String sstate) {
		System.out.println("changeState...service...");
		
		try{
			//先改变状态 
			int mid = Integer.parseInt(mmid);
			int state = Integer.parseInt(sstate);
			String email = materialDao.changeState(mid, state);
			String materialName = materialDao.getMName(mid);
			
			System.out.println("改变结束>>>>>>>>>>>>>>>>>>mid=" + mid + ">>>>>>>>>>email=" + email);
			
			//改变成功才发邮件
			if(!email.equals("-1")){
				String text = null;
				if(state == 0){
					text = "待处理" ;
				} else if(state == 1) {
					text = "已录用";
				} else if(state == 2) {
					text = "已退稿";
				} else if(state == 3) {
					text = "建议修改";
				}
				
				boolean flag = mailUtil.sendMailState(email, materialName, text);
				if(flag == true){
					return true;
				}
			}
			return false;
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	
	
}
