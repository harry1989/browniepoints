package main.java.browniepoints.model;

public class User {
	public User(Integer uid, String email, String name, Integer points, String phone) {
		super();
		this.uid = uid;
		this.email = email;
		this.name = name;
		this.points = points;
		this.phone = phone;
	}


	public User(String email, String name, Integer points, String phone) {
		super();
		this.email = email;
		this.name = name;
		this.points = points;
		this.phone = phone;
	}


	private Integer uid;
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	private String email;
	private String name;
	private Integer points;
	private String phone;
	
	public Integer getUid() {
		return uid;
	}
	
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPoints() {
		return points;
	}
	
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	public boolean hasUid() {
		return this.uid != null;
	}
}
