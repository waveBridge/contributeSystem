package cn.cs.dao;

import java.util.List;

import cn.cs.entity.Classify;
import cn.cs.entity.Material;

public interface MaterialDao {

	String getMUrl(int uid);

	String getMName(int mid);

	String changeState(int mid, int state);

	List<Classify> getClassifyList();

	Material getDetail(int mid);

}
