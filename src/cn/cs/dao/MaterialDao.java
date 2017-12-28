package cn.cs.dao;

public interface MaterialDao {

	String getMUrl(int uid);

	String getMName(int mid);

	String changeState(int mid, int state);

}
