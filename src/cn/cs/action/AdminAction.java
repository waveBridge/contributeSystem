package cn.cs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.cs.entity.Material;
import cn.cs.service.AdminService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class AdminAction extends ActionSupport {
	
	private AdminService adminService;
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	//管理员登录
	public String adminLogin() throws IOException{
		System.out.println("adminLogin...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		try{
			String adminName = request.getParameter("adminName");
			String password = request.getParameter("password");
			boolean flag = adminService.login(adminName, password);
			if(flag == true){
				json.put("msg", "1");							//登陆成功
			} else {
				json.put("msg", "0");							//失败
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
	
	//查看所有稿件
	public String getAllMaterial() throws IOException{
		System.out.println("getAllMaterial...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		//设置jsonConfig是为了摆脱死循环，因为是级联关系
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);  
		
		JSONObject json = new JSONObject();
		JSONArray json2;
		try{
			List<Material> materialList = adminService.getAllMaterial();
			if(materialList == null){
				json.put("msg", "-1");						    //错误
			} else {
				json.put("cnt", materialList.size());			//放入信息
				json2 = JSONArray.fromObject(materialList,jsonConfig);
				json.put("msg", json2);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("msg", "-1");								//错误
		} finally {
			out.write(json.toString());
			out.flush();
			out.close();
		}
		
		return null;
	}
	
}
