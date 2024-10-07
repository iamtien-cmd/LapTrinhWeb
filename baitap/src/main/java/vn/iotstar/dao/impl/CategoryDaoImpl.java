package vn.iotstar.dao.impl;
import vn.iotstar.dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.dao.ICategoryDAO;

import vn.iotstar.model.CategoryModel;

public class CategoryDaoImpl extends DBConnection implements ICategoryDAO{
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public void insert(CategoryModel category) {
		String sql = "INSERT INTO categories(categoryname,images,active) VALUES (?,?,?)";
		try {
			con =new DBConnection().getConnection();
		 ps = con.prepareStatement(sql);
		ps.setString(1,category.getCategoryname());
		ps.setString(2,category.getImages());
		ps.setBoolean(3, category.isActive());
		ps.execute();
		con.close();
		ps.close();
	
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}

	@Override
	public void edit(CategoryModel category) {
		String sql = "UPDATE categories SET categoryname = ?, images=?, active = ? WHERE categoryid = ?";
		try {
			con =new DBConnection().getConnection();
		 ps = con.prepareStatement(sql);
		ps.setString(1, category.getCategoryname());
		ps.setString(2, category.getImages());
		ps.setBoolean(3, category.isActive());
		ps.setInt(4, category.getCategoryid());
		ps.executeUpdate();
		con.close();
		ps.close();
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM categories WHERE categoryid = ?";
		try {
			con =new DBConnection().getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		con.close();
		ps.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}

	@Override
	public CategoryModel get(int id) {
		String sql = "SELECT * FROM categories WHERE categoryid = ? ";
		try {
			con =new DBConnection().getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
		CategoryModel category = new CategoryModel();
		category.setCategoryid(rs.getInt("categoryid"));
		category.setCategoryname(rs.getString("categoryname"));
		category.setImages(rs.getString("images"));
		category.setActive(rs.getBoolean("active"));
		return category;
		}} catch (Exception e) {
		e.printStackTrace();}
		return null;

	}

	

	@Override
	public List<CategoryModel> getAll() {
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		String sql = "SELECT * FROM Categories";
		try {
			con =new DBConnection().getConnection();
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
		CategoryModel category = new CategoryModel();
		category.setCategoryid(rs.getInt("categoryid"));
		category.setCategoryname(rs.getString("categoryname"));
		category.setImages(rs.getString("images"));
		category.setActive(rs.getBoolean("active"));
		categories.add(category);
		}} catch (Exception e) {
		e.printStackTrace();}
		return categories;

	}

	@Override
	public List<CategoryModel> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryModel> findAll() {
		String sql = "select * from categories";
		List<CategoryModel> list = new ArrayList<>();
		try {
			con =new DBConnection().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			CategoryModel category = new CategoryModel();
			category.setCategoryid(rs.getInt("categoryid"));
			category.setCategoryname(rs.getString("categoryname"));
			category.setImages(rs.getString("images"));
			category.setActive(rs.getBoolean("active"));
			list.add(category);
		}
		con.close();
		ps.close();
		rs.close();
		return list;
	} 
		catch (Exception e) {
		e.printStackTrace();
	}
		return list;
	}
	@Override
	public CategoryModel findById(int id) {
		String sql = "select * from categories where categoryid=?";
		try {
			con =new DBConnection().getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while(rs.next()) {
			CategoryModel category = new CategoryModel();
			category.setCategoryid(rs.getInt("categoryid"));
			category.setCategoryname(rs.getString("categoryname"));
			category.setImages(rs.getString("images"));
			category.setActive(rs.getBoolean("active"));
			return category;
		}
		con.close();
		ps.close();
		rs.close();
	} 
		catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	
	}
	public List<CategoryModel> findByName(String keyword) {
		String sql = "select * from categories where categoryname like ?";
		List<CategoryModel> list = new ArrayList<>();
		try {
			con =new DBConnection().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1,"%" + keyword + "%");
		
		rs = ps.executeQuery();

		while(rs.next()) {
			CategoryModel category = new CategoryModel();
			category.setCategoryid(rs.getInt("categoryid"));
			category.setCategoryname(rs.getString("categoryname"));
			category.setImages(rs.getString("images"));
			category.setActive(rs.getBoolean("active"));
			list.add(category);
		}
		con.close();
		ps.close();
		rs.close();
		return list;
	} 
		catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	
	}


}
