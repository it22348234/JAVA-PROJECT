package model;

public class TVSeries {
	public TVSeries(int id, String mainBanner,String videLink, String title, String category, String description) {
		super();
		this.id = id;
		this.mainBanner = mainBanner;
		this.videLink = videLink;
		this.title = title;
		this.category = category;
		this.description = description;
	}
	protected int id;
	protected String mainBanner;
	protected String videLink;
	protected String title;
	protected String category;
	protected String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMainBanner() {
		return mainBanner;
	}
	public void setMainBanner(String mainBanner) {
		this.mainBanner = mainBanner;
	}
	public String getVideLink() {
		return videLink;
	}
	public void setVideLink(String videLink) {
		this.videLink = videLink;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
