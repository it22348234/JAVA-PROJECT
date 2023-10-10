package model;

public class User {
	protected int id;
	public User( String username, Boolean isPremium, String name, String password,String profileImageUrl) {
		super();
		this.username = username;
		this.isPremium = isPremium;
		this.name = name;
		this.password = password;
		this.profileImageUrl = profileImageUrl;
	}
	protected String username;
	protected Boolean isPremium;
	protected String name;
	protected String password;
	protected String profileImageUrl;
	
	
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getIsPremium() {
		return isPremium;
	}
	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
