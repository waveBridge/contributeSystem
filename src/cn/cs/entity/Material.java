package cn.cs.entity;

public class Material {
	private Integer mid;
	private String  materialName;
	private String materialResume;
	private String  url;
	private Integer state;
	private String  date;
	private User 	user;
	private Classify classify;
	
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Classify getClassify() {
		return classify;
	}
	public void setClassify(Classify classify) {
		this.classify = classify;
	}
	public String getMaterialResume() {
		return materialResume;
	}
	public void setMaterialResume(String materialResume) {
		this.materialResume = materialResume;
	}
}
