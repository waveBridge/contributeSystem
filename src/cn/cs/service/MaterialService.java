package cn.cs.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;   //注意事务的配置引入的包一定不要错

import cn.cs.dao.MaterialDao;
import cn.cs.entity.Classify;
import cn.cs.entity.Material;
import cn.cs.util.MailUtil;
import cn.cs.util.TimeUtil;

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
	
	//得到所有分类的已录用的稿件
	public List<Classify> getClassifyList() {
		System.out.println("getEmpolyMaterial...service...");
		
		try{
			List<Classify> classifyList = materialDao.getClassifyList();
			return classifyList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	//查看稿件详情
	public Material getDetail(String mids) {
		System.out.println("getDetail..service...");
		
		try{
			int mid = Integer.parseInt(mids);
			Material material = materialDao.getDetail(mid);
			return material;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}		
	}
	
	
}
