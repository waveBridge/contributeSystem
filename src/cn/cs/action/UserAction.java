package cn.cs.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.cs.entity.User;
import cn.cs.service.UserService;
import net.sf.json.JSONObject;

public class UserAction extends ActionSupport {

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//登录
	public String login() throws IOException{
		System.out.println("login...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			boolean flag = userService.login(username, password);
			if(flag == true){
				json.put("msg", "1");							//允许登录
			} else {
				json.put("msg", "0");							//用户名活着密码错误
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("msg", "0");								//错误
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	//获取邮箱验证码
	public String getVCode() throws IOException {
		System.out.println("getVCode...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		try{
			String email = request.getParameter("email");
			System.out.println(email);
			boolean flag = userService.getVCode(email);
			if(flag == true) {
				json.put("msg","1");                 //生成了验证码并发送给了用户
			} else {
				json.put("msg","0");                 //未获取到
			}
			
		}catch (Exception e) {
			json.put("msg","0");
		}finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	//用户注册
	public String register() throws IOException {
		System.out.println("register...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String nickname = request.getParameter("nickname");
			String email 	= request.getParameter("email");
			String vcode 	= request.getParameter("vcode");
			String address 	= request.getParameter("address");
			String company 	= request.getParameter("company");
			String phone 	= request.getParameter("phone");
			
			
			//先查找该用户名是否被注册
			boolean flag = userService.searchUser(username);
			
			if(flag == true) { 
				json.put("msg","3");                			  //用户名重复
			} else {
				//看验证码是否正确以及是否失效
				flag = userService.cmpVCode(vcode);
				
				if(flag == true){
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					user.setNickname(nickname);
					user.setEmail(email);
					user.setAddress(address);
					user.setPhone(phone);
					user.setCompany(company);
					
					boolean flag2 =userService.register(user);
					
					if(flag2 == true) {
						json.put("msg", "1");             //注册成功
					
					} else {
						json.put("msg","0");              //注册失败
					}
				} else {
					System.out.println("验证码匹配失败");
					json.put("msg", "2");                 //验证码匹配失败
				}
			}
			
		} catch (Exception e) {
			System.out.println("注册异常");
			json.put("msg", "0");                  	      //注册 异常
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		
		return null;
	}
	
}
