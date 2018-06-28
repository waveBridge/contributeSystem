package cn.cs.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.cs.entity.Classify;
import cn.cs.entity.Material;
import cn.cs.entity.User;

public class UserDaoImpl implements UserDao {
	
	private HibernateTemplate hibernateTemplate; 
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//根据用户名密码查询用户
	@Override
	public User searchUser(String username, String password) {
		System.out.println("searchUser...dao...");
		
		try{
			@SuppressWarnings("unchecked")
			List<User> userList = (List<User>)hibernateTemplate.find("from User where username = ? and password = ?", username, password);
			if(userList == null || userList.size() == 0){
				return null;
			} else {
				return userList.get(0);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
	}

	//根据用户名查询用户
	@Override
	public int searchUser(String username) {
		System.out.println("searchUser...dao...");
		try{
			@SuppressWarnings("unchecked")
			List<User> userList = (List<User>)hibernateTemplate.find("from User where username = ?", username);
			if(userList.size() == 0){
				return 0;
			} else {
				return userList.get(0).getUid();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			return 0;
		}

	}

	//添加用户
	@Override
	public int addUser(User user) {
		System.out.println("addUser...dao...");
		try{
			int flag = (int)hibernateTemplate.save(user);
			return flag;
		}catch (Exception e) {
			System.out.println(e.toString());
			return 0;
		}
		
	}

	//根据用户id获取材料
	@Override
	public List<Material> getMaterials(int uid, int state) {
		System.out.println("getMaterials...dao...");
		
		try{		
			if(state == 3){
				@SuppressWarnings("unchecked")
				List<Material> materials = (List<Material>) hibernateTemplate.find("from Material where uid = ?", uid);
				return materials;
			} else {
				String q = "from Material where uid = ? and state = ?";
				@SuppressWarnings("unchecked")
				List<Material>	materials = (List<Material>) hibernateTemplate.find(q, uid, state);
				return materials;
			}
			
//			User user = hibernateTemplate.get(User.class, uid);
//			if(user == null) {
//				return null;
//			} else {
//				Set<Material> materialSet = user.getMaterialSet();
//				return materialSet;
//			}
	
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	//上传文件
	@Override
	public boolean upFile(int uid,int cid, Material material) {
		System.out.println("upFile...dao...");
		
		try{
			User user = hibernateTemplate.get(User.class, uid);
			user.getMaterialSet().add(material);
			material.setUser(user);
			
			Classify classify = hibernateTemplate.get(Classify.class, cid);
			classify.getMaterialSet().add(material);
			material.setClassify(classify);
			
			hibernateTemplate.save(user);
			hibernateTemplate.save(classify);
			hibernateTemplate.save(material);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}

	}

	//改变稿件
	@Override
	public boolean changeFile(int mid, String materialName, String rePath, String date) {
		System.out.println("changeFile...dao...");
		
		try{
			Material material = hibernateTemplate.get(Material.class, mid);
			material.setDate(date);
			material.setMaterialName(materialName);
			material.setState(0);
			material.setUrl(rePath);
			hibernateTemplate.update(material);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	//修改密码
	@Override
	public boolean changePass(int uid, String oldPass, String newPass) {
		System.out.println("changePass...dao...");
		
		try{
			User user = hibernateTemplate.get(User.class, uid);
			if(user.getPassword().equals(oldPass)){
				user.setPassword(newPass);
				hibernateTemplate.update(user);
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}		
	}

	//修改个人信息
	@Override
	public boolean changeInfo(String nickname, String address, String resume, int uid) {
		System.out.println("changeInfo...dao...");
		
		try{
			User user = hibernateTemplate.get(User.class, uid);
			user.setNickname(nickname);
			user.setAddress(address);
			user.setResume(resume);
			hibernateTemplate.update(user);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	//根据稿件名进行搜索
	@Override
	public List<Material> getMaterialByName(int uid, String materialName) {
		System.out.println("getMaterialByName...dao...");
		
		try{
			String q = "from Material where materialName like ? and uid = ?";
			@SuppressWarnings("unchecked")
			List<Material> materialList = (List<Material>) hibernateTemplate.find(q, "%" + materialName + "%", uid);
			return materialList;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}		
	}

	//获得当前用户的基本信息
	@Override
	public User getUserInfo(int uid) {
		System.out.println("getUserInfo...dao...");
		
		try{
			User user = hibernateTemplate.get(User.class, uid);
			return user;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
}
