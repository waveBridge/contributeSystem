package cn.cs.dao;

import java.util.Set;

import cn.cs.entity.Material;
import cn.cs.entity.User;

public interface UserDao {
	public int searchUser(String username, String password);

	public int searchUser(String username);

	public int addUser(User user);

	public Set<Material> getMaterials(int uid);

	public boolean upFile(int uid, Material material);

	public boolean changeFile(int mid, String materialName, String rePath, String date);
}
