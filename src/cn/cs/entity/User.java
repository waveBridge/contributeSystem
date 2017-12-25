package cn.cs.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
	private Integer uid;
	private String  username;
	private String  password;
	private String  nickname;
	private String  address;
	private String  company;
	private String  phone;
	private String  email;
	private Set<Material> materialSet = new HashSet<Material>();
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Material> getMaterialSet() {
		return materialSet;
	}
	public void setMaterialSet(Set<Material> materialSet) {
		this.materialSet = materialSet;
	}
	
}
