package vn.iotstar.service;

import java.util.List;

import vn.iotstar.model.CategoryModel;

public interface ICategoryService {
	void insert(CategoryModel category);
	void update(CategoryModel category);
	void delete(int id);
	CategoryModel get(int id);
	CategoryModel get(String name);
	List<CategoryModel> getAll();
	List<CategoryModel> findAll();
	CategoryModel findById(int id);
	List<CategoryModel> findByName(String keyword);
	
}
