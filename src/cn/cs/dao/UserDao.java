package cn.cs.dao;

import cn.cs.entity.User;

public interface UserDao {
	public int searchUser(String username, String password);

	public int searchUser(String username);

	public int addUser(User user);
}
