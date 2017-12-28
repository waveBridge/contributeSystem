package cn.cs.dao;

import java.util.List;
import java.util.Set;

import cn.cs.entity.Material;

public interface AdminDao {

	boolean searchAdmin(String adminName, String password);

	List<Material> getAllMaterial();

}
