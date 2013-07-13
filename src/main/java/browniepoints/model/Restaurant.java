package main.java.browniepoints.model;

public class Restaurant {
	@Override
	public String toString() {
		return "Restaurant [rid=" + rid + ", name=" + name + ", latitude="
				+ latitude + ", longitude=" + longitude + ", place=" + place
				+ ", phone=" + phone + ", cuisine=" + cuisine + ", owner_uid="
				+ owner_uid + "]";
	}

	private Integer rid;
	private String name;
	private Double latitude;
	private Double longitude;
	private String place;
	private String phone;
	private String cuisine;
	private Integer owner_uid;

	public Restaurant() {
	}

	public Restaurant(String name, Double latitude, Double longitude,
			String place, String phone, String cuisine, Integer owner_uid) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.place = place;
		this.phone = phone;
		this.cuisine = cuisine;
		this.owner_uid = owner_uid;
	}

	public Restaurant(Integer rid, String name, Double latitude,
			Double longitude, String place, String phone, String cuisine,
			Integer owner_uid) {
		super();
		this.rid = rid;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.place = place;
		this.phone = phone;
		this.cuisine = cuisine;
		this.owner_uid = owner_uid;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public Integer getOwner_uid() {
		return owner_uid;
	}

	public void setOwner_uid(Integer owner_uid) {
		this.owner_uid = owner_uid;
	}

	public boolean hasRid() {
		return this.rid != null;
	}
}