package vn.iotstar.model;

import java.io.Serializable;

public class CategoryModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5906536254741381152L;
	public CategoryModel()
	{
		
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public CategoryModel(int categoryid, String categoryname, String images) {
	
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.images = images;
	}
	@Override
	public String toString() {
		return "categoryModel [categoryid=" + categoryid + ", categoryname=" + categoryname + ", images=" + images
				+ "]";
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	private int categoryid;
	private String categoryname;
	private String images;
	private boolean active;
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setStatus(int status) {
		// TODO Auto-generated method stub
		
	}
	public void edit(CategoryModel newCategory) {
		// TODO Auto-generated method stub
		
	}
	
	

}
