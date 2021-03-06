package cn.cs.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.cs.entity.Material;
import cn.cs.entity.User;
import cn.cs.service.UserService;
import cn.cs.util.OrderUtil;
import cn.cs.util.Redundant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class UserAction extends ActionSupport {

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private File upload;                  //上传文件
	private String uploadFileName;        //上传文件名
	private String uploadContentType; 	  //上传文件类型
    private long maximumSize;  
    private String allowedTypes; 
    
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public long getMaximumSize() {
		return maximumSize;
	}
	public void setMaximumSize(long maximumSize) {
		this.maximumSize = maximumSize;
	}
	public String getAllowedTypes() {
		return allowedTypes;
	}
	public void setAllowedTypes(String allowedTypes) {
		this.allowedTypes = allowedTypes;
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
		//JSONObject json2;
		//JsonConfig jsonConfig = new JsonConfig();
		//jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>username"+username+"   passwrod"+password);
			
			User user = userService.login(username, password);
			if(user != null){
				//user = Redundant.redundant(user);
				//json2 = JSONObject.fromObject(user, jsonConfig);
				json.put("msg", "1");							//允许登录
			} else {
				json.put("msg", "0");							//用户名或着密码错误
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
	
	//获取当前用户基本信息
	public String getUserInfo() throws IOException{
		System.out.println("getUserInfo...action...");		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		JSONObject json2;
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		try{
			User user = userService.getUserInfo();
			if(user != null){
				user = Redundant.redundant(user);
				json2 = JSONObject.fromObject(user, jsonConfig);
				json.put("msg", json2);							
			} else {
				json.put("msg", "0");							
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("msg", "0");
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
	
	//修改密码
	public String changePass() throws IOException{
		System.out.println("changePass...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		try{
			String oldPass = request.getParameter("oldPass");
			String newPass = request.getParameter("newPass");
			
			boolean flag = userService.changePass(oldPass, newPass);
			if(flag == true){
				json.put("msg", "1");
			} else {
				json.put("msg", "0");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("msg", "0");
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		return null;
	}
	
	//修改个人信息
	public String changeInfo() throws IOException{
		System.out.println("changeInfo...action...");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();		
		try{
			String nickname = request.getParameter("nickname");
			String address = request.getParameter("address");
			String resume = request.getParameter("resume");
			
			boolean flag = userService.changeInfo(nickname, address, resume);
			if(flag == false){
				json.put("msg", "0");
			} else {
				json.put("msg", "1");
				json.put("nickname", nickname);
				json.put("address", address);
				json.put("resume", resume);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("msg", "0");
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		return null;
	}
	
	
	//获取当前该用户的稿件
	public String getMaterial() throws IOException {
		System.out.println("upFile...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		//设置jsonConfig是为了摆脱死循环，因为是多对多级联关系
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		JSONObject json = new JSONObject();
		JSONArray json2;
		
		try{
			String state = request.getParameter("state");
			List<Material> materialList = userService.getMaterials(state);
			materialList = Redundant.redundant(materialList);
			materialList = OrderUtil.sort(materialList);
			if(materialList == null){
				json.put("msg", "-1");								//null
			} else{
				json.put("cnt", materialList.size());				//材料数量
				json2 = JSONArray.fromObject(materialList, jsonConfig);
				json.put("msg", json2);								//材料列表
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("msg", "-1");
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	//在当前用户的稿件中，按照稿件名搜索
	public String userGetMByName() throws IOException{
		System.out.println("userGetMByName...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		//设置jsonConfig是为了摆脱死循环，因为是多对多级联关系
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		JSONObject json = new JSONObject();
		JSONArray json2;
		
		try{
			String materialName = request.getParameter("materialName");
			List<Material> materialList = userService.getMaterialByName(materialName);
			if(materialList == null){
				json.put("msg", "0");
			} else {
				materialList = Redundant.redundant(materialList);
				materialList = OrderUtil.sort(materialList);
				
				json2 = JSONArray.fromObject(materialList, jsonConfig);
				json.put("msg", json2);
				json.put("cnt", materialList.size());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("msg", "0");
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	//由于技术限制，所以多了这么个action。上传稿件的页面，前端先访问此action，看是否为第一次到达页面。
	public String upFileFlag() throws IOException{
		System.out.println("upFileFlag...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		try{
			HttpSession session = request.getSession();
			String upFileFlag = (String) session.getAttribute("upFileFlag");
			if(upFileFlag == null || (!upFileFlag.equals("1"))){
				//为空， 或者不是"1"，那么就表示是用户第一次来到此页面
				json.put("flag", "0");
			} else if(upFileFlag.equals("1")){
				//为"1"，说明用户已经上传过文件，此时要给前端返回信息
				json.put("flag", "1");
				json.put("msg", session.getAttribute("upFileMsg"));
				session.removeAttribute("upFileFlag");		//同时要清空，不然再次上传文件时会出错
				session.removeAttribute("upFileMsg");
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("flag", "0");
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	//上传稿件
	public String upFile() throws IOException{
		System.out.println("upFile...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		try{
			String materialName = request.getParameter("materialName");
			String materialResume = request.getParameter("materialResume");
			int cid = Integer.parseInt(request.getParameter("cid"));
			
			System.out.println(uploadFileName + " " + uploadContentType + " " + cid);	
			materialName += "." + uploadFileName.split("\\.")[1];				//给文件名加上后缀					
			
			String flag = userService.upFile(maximumSize, allowedTypes, upload , materialName,
												materialResume, cid, uploadContentType);
			json.put("msg", flag);		//0失败  1成功  -1文件过大  -2文件类型不匹配
			System.out.println("最后的flag为 " + flag);
			session.setAttribute("upFileFlag", "1");		//用户已经上传文件
			session.setAttribute("upFileMsg", flag);		//具体信息在 "upFileMsg"中
			response.sendRedirect("upload.html");	//跳转到上传的页面，此时在session中已存有信息
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("msg", "0");
			session.setAttribute("upFileFlag", "1");		//用户已经上传文件
			session.setAttribute("upFileMsg", "0");			//但是上传失败了
			response.sendRedirect("upload.html");	//跳转到上传的页面，此时在session中已存有信息
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		return null;
	}
	
	//修改稿件（未使用）
	public String changeFile() throws IOException{
		System.out.println("changeFile...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		try{
			String mid = request.getParameter("mid");
			String flag = userService.changeFile(maximumSize,allowedTypes,upload,uploadFileName,uploadContentType,mid);
			json.put("msg", flag);			//0失败  1成功  -1文件过大  -2文件类型不匹配
			
			response.sendRedirect("Writer-search.html");
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("msg", "0");
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		
		return null;
	}
	
	//登出
	public String logout() throws IOException{
		System.out.println("changeFile...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		try{
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.removeAttribute("uid");
			session.removeAttribute("aid");
			json.put("msg", "1");
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("msg", "0");
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		return null;
	}
	
}
