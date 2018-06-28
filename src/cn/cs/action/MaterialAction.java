package cn.cs.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.cs.entity.Classify;
import cn.cs.entity.Material;
import cn.cs.service.MaterialService;
import cn.cs.util.OrderUtil;
import cn.cs.util.Redundant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class MaterialAction extends ActionSupport {
	
	private MaterialService materialService;
	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}
	
	//下载
	public String downFile() throws IOException{
		System.out.println("downFile...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");					//防止中文乱码
		
		FileInputStream in = null;
		OutputStream os = null;
		
		try{
			String mid = request.getParameter("mid");		//材料id
			String mUrl = materialService.getMUrl(mid);		//获取材料的url
			String mName = mUrl.substring(7, mUrl.length());
			
			//防止中文乱码
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
			    mName = URLEncoder.encode(mName, "UTF-8");  
			} else {  
				mName = new String(mName.getBytes("UTF-8"), "ISO-8859-1");  
			} 
			
			File uploadFile = new File(ServletActionContext.getServletContext().getRealPath("\\"));
			String rmUrl = uploadFile.getPath() + "\\" + mUrl;
			
			//System.out.println("mName...>>>>>>>>>>>>" + mName);
		    response.setHeader("Content-Disposition", "attachment;filename=\"" + mName + "\"");
		    
		    //读取要下载的文件，保存到文件输入流
		    in = new FileInputStream(rmUrl);  
	        os = response.getOutputStream();
	        
	        // 创建输出流 ,向文件中写数据
            byte[] b=new byte[1024];//每次写入的大小  
            int i=0;  
            while((i=in.read(b))>0){  
                os.write(b,0,i);  
            } 
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			in.close();
			os.close();
		}
		
		return null;
	}
	
	//处理稿件
	public String changeState() throws IOException { 
		System.out.println("changeState...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		try{
			String state = request.getParameter("state");
			String mid = request.getParameter("mid");
			boolean flag = materialService.changeState(mid, state);
			if(flag == false){
				json.put("msg", "0");
			} else {
				json.put("msg", "1");
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
	
	//得到所有分类的稿件
	public String getClassifyMaterial() throws IOException{
		System.out.println("getEmpolyMaterial...action...");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		JSONObject json = new JSONObject();
		JSONArray json2;
		try{
			List<Classify> classifyList = materialService.getClassifyList();
			if(classifyList == null){
				json.put("msg", "0");
			} else {
				if(session.getAttribute("aid") == null){				//不是管理员
					System.out.println("aid  是null");
					for(Classify c : classifyList){
						//筛选出已录用的
						c.setMaterialSet(Redundant.haveEmployed(c.getMaterialSet()));
						Redundant.redundant(c.getMaterialSet());	//去除冗余
					}
				} else {
					System.out.println("aid 不是null");
					for(Classify c : classifyList){
						Redundant.redundant(c.getMaterialSet());	//去除冗余
					}
				}

				json2 = JSONArray.fromObject(classifyList, jsonConfig);
				json.put("msg", json2);
				json.put("cnt", classifyList.size());
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
	
	//查看稿件详情
	public String getDetail() throws IOException{
		System.out.println("getDetail...action...");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject json = new JSONObject(); 
		JSONObject json2;
		try{
			String mid = request.getParameter("mid");
			Material material = materialService.getDetail(mid);
			if(material == null){
				json.put("msg", "0");
			} else {
				Redundant.redundant(material);			//去除冗余		
				if(session.getAttribute("aid") == null){
					//不是管理员，要去掉所属作者的未录用书籍
					Redundant.haveEmployed(material);
				}
				
				json2 = JSONObject.fromObject(material, jsonConfig);
				json.put("msg", json2);
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
	
}
