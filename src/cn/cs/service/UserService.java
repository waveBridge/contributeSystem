package cn.cs.service;

import java.util.Calendar;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;   //注意事务的配置引入的包一定不要错

import cn.cs.dao.UserDao;
import cn.cs.entity.User;
import cn.cs.util.MailUtil;
import cn.cs.util.TimeUtil;
import javassist.bytecode.stackmap.BasicBlock.Catch;

@Transactional
public class UserService {
	private UserDao userDao;
	private MailUtil mailUtil;
	private TimeUtil timeUtil;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setMailUtil(MailUtil mailUtil) {
		this.mailUtil = mailUtil;
	}
	public void setTimeUtil(TimeUtil timeUtil) {
		this.timeUtil = timeUtil;
	}
	
	//登录
	public boolean login(String username, String password) {
		System.out.println("login...service...");
		
		try{
			int uid = userDao.searchUser(username, password);
			if(uid == 0){
				return false;
			} else {
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("uid", uid);
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
	}
	
	
	//发送邮件获取验证码
	public boolean getVCode(String email) {
		System.out.println("getVCode...service...");
		//随机生成5验证码
	    Integer x =(int)((Math.random()*9+1)*10000);  
	    String text = x.toString(); 
		boolean flag = mailUtil.sendMail(email, text);
		if(flag == true){
			//发送成功，把验证码和时间记录
			String nowTime = timeUtil.getTime();
			
			//存入session  验证码#时间
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("vcodeTime",text+"#"+nowTime);
			System.out.println(session.getAttribute("vcodeTime"));
			return true;
			
		} else {
			return false;
		}
	}
	
	//查找用户
	public boolean searchUser(String username) {
		// TODO 自动生成的方法存根
		return false;
	}
	
	
	public boolean cmpVCode(String vcode) {
		// TODO 自动生成的方法存根
		return false;
	}
	
	
	public boolean register(User user) {
		// TODO 自动生成的方法存根
		return false;
	}
	
	
	
	
}
