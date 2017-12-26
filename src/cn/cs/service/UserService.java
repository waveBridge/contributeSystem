package cn.cs.service;

import java.io.File;
import java.util.Calendar;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;   //注意事务的配置引入的包一定不要错

import cn.cs.dao.UserDao;
import cn.cs.entity.Material;
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
		System.out.println("search...service...");
		
		try{
			int uid = userDao.searchUser(username);
			if(uid == 0){
				return false;								//没找到
			} else {
				return true;								//找到了
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;									//错误
		}
		
	}
	
	//比较验证码是否正确以及是否失效
	public boolean cmpVCode(String vcode) {
		System.out.println("cmpVCode...service...");
		
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			String vcodeTime =  (String) session.getAttribute("vcodeTime");
			String vcodeTimeArray[] = vcodeTime.split("#");
			
			//先比较验证码是否正确
			if(vcodeTimeArray[0].equals(vcode)) {
				boolean flag = timeUtil.cmpTime(vcodeTimeArray[1]);	
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
	
	
	//注册
	public boolean register(User user) {
		System.out.println("register...service...");
		
		int flag = userDao.addUser(user);
		
		if(flag != 0) {
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("uid", flag);                //用户id放入 session
			return true;
		} else {
			return false;
		}
	}
	
	//查看当前用户的所有材料
	public Set<Material> getMaterials() {
		System.out.println("getMaterials...service...");
		
		try{
			//获取用户id
			HttpSession session = ServletActionContext.getRequest().getSession();
			int uid = (int)session.getAttribute("uid");
			
			//根据uid获取材料
			Set<Material> materialSet = userDao.getMaterials(uid);
			return materialSet;
			
		} catch (Exception e) {
			System.out.println(e.toString());					//错误
			return null;
		}

	}
	
	//上传文件
	public String upFile(long maximumSize, String allowedTypes, File upload, String uploadFileName,
			String uploadContentType, String materialName) {
		System.out.println("upFile...service...");
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		try{
			//新建文件
			File uploadFile = new File(ServletActionContext.getServletContext().getRealPath("upload"));
			if(!uploadFile.exists()) {  
	            uploadFile.mkdir();  
	        } 
			
			//验证文件大小
	        if (maximumSize < upload.length()) {  
	            return "-1";                       //文件过大
	            
	        } else {
	        	//验证文件格式
	        	boolean flag = false;  
	            String[]  allowedTypesStr = allowedTypes.split(",");    //这是在配置文件中 按照逗号隔开
	            
	            for (int i = 0; i < allowedTypesStr.length; i++) {  
	                if (uploadContentType.equals(allowedTypesStr[i])) {  
	                    flag = true;                              
	                }  
	            }  
	            
	            if (flag == false) {  
	                return "-2";                 					    //文件格式错误
	            } else {
	            	String path = uploadFile.getPath()+ "\\" + uploadFileName;
	            	
	            	//准备上传
		            FileUtils.copyFile(upload, new File(path));  
		            //删除临时文件 
		            upload.delete(); 
		            
		            //相对路径
		            String rePath = "upload" + "\\" + uploadFileName;
		            
		            Material material = new Material();
		            material.setMaterialName(materialName);
		            material.setState(0);							//待审核
		            material.setUrl(rePath);
		            
		            //写入数据库
		            boolean flag2 = userDao.upFile((int)session.getAttribute("uid"), material);
		            if(flag2 == false) {
		            	return "0";                  //失败
		            } else { 
		            	System.out.println(ServletActionContext.getServletContext().getContextPath());
		            	return "1";               //成功
		            }
		      
	            }
	        }
		} catch (Exception e) {
			System.out.println(e.toString());
			return "0";
		}
	
	}
	
}
