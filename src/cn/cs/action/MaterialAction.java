package cn.cs.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.cs.service.MaterialService;
import net.sf.json.JSONObject;

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
			String mName = materialService.getMName(mid);	//获取投稿名
			
			//防止中文乱码
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
			    mName = URLEncoder.encode(mName, "UTF-8");  
			} else {  
				mName = new String(mName.getBytes("UTF-8"), "ISO-8859-1");  
			} 
			
			File uploadFile = new File(ServletActionContext.getServletContext().getRealPath("\\"));
			String rmUrl = uploadFile.getPath() + "\\" + mUrl;
			
			System.out.println("mName...>>>>>>>>>>>>" + mName);
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
	
}
