package cn.cs.service;

import java.util.Calendar;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;   //注意事务的配置引入的包一定不要错

import cn.cs.dao.UserDao;
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
		
		
		return false;
	}
	
	
	
	
}
