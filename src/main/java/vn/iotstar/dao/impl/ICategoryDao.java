package vn.iotstar.dao.impl;

import java.util.List;

import vn.iotstar.entity.Category;

public interface ICategoryDao {
	void delete(int cateid) throws Exception;
	void update(Category category);
	void insert(Category category);
	Category findByCategoryname(String name) throws Exception;
	Category findById(int cateid);
	List<Category> findAll();
	List<Category> searchByName(String catname);
	List<Category> findAll(int page, int pagesize);
	int count();
	
}
