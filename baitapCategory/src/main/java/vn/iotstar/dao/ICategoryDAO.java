package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.model.CategoryModel;

public interface ICategoryDAO {
	void insert(CategoryModel category);
	void edit(CategoryModel category);
	void delete(int id);
	CategoryModel get(int id);
	List<CategoryModel> findByName(String name);
	List<CategoryModel> getAll();
	List<CategoryModel> search(String keyword);
	List<CategoryModel> findAll();
	CategoryModel findById(int id);
}
