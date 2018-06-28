package cn.cs.dao;

import java.util.List;

import cn.cs.entity.Material;
import cn.cs.entity.User;

public interface UserDao {
	public User searchUser(String username, String password);

	public int searchUser(String username);

	public int addUser(User user);

	public List<Material> getMaterials(int uid, int state);

	public boolean upFile(int uid, int cid, Material material);

	public boolean changeFile(int mid, String materialName, String rePath, String date);

	public boolean changePass(int uid, String oldPass, String newPass);

	public boolean changeInfo(String nickname, String address, String resume, int uid);

	public List<Material> getMaterialByName(int uid, String materialName);

	public User getUserInfo(int uid);

}
