package vn.iotstar.model;

import java.io.Serializable;

public class CategoryModel implements Serializable{
	@Override
	public String toString() {
		return "CategoryModel [categoryid=" + categoryid + ", categoryname=" + categoryname + ", images=" + images + ", active="
				+ active + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -1309566883832857739L;
	private int categoryid;
	private String categoryname;
	private String images;
	private boolean active;
	public CategoryModel(int categoryid, String categoryname, String images) {
		
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.images = images;
	}
	public CategoryModel() {
		super();
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int cate_id) {
		this.categoryid = cate_id;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String cate_name) {
		this.categoryname = cate_name;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String Images) {
		this.images = Images;
	}
	
}
