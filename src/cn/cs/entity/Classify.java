package cn.cs.entity;

import java.util.HashSet;
import java.util.Set;

public class Classify {
	private Integer cid;
	private String classifyName;
	private Set<Material> materialSet = new HashSet<Material>();
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public Set<Material> getMaterialSet() {
		return materialSet;
	}
	public void setMaterialSet(Set<Material> materialSet) {
		this.materialSet = materialSet;
	}

}
