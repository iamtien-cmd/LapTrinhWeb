package vn.iotstar.service.impl;

import java.io.File;
import java.util.List;

import vn.iotstar.dao.ICategoryDAO;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.model.CategoryModel;
import vn.iotstar.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	ICategoryDAO categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(CategoryModel category) {
		// TODO Auto-generated method stub
		categoryDao.insert(category);
	}

	@Override
	public void update(CategoryModel newCategory) {
		CategoryModel cate = new CategoryModel();
		cate = categoryDao.findById(newCategory.getCategoryid());
		if (cate != null) {
			newCategory.edit(newCategory);
		}
	}

	@Override
	public void delete(int id) {
		CategoryModel cate = new CategoryModel();
		cate = categoryDao.findById(id);
		if (cate != null) {
			categoryDao.delete(id);
		}
	}

	@Override
	public CategoryModel get(int id) {
		return categoryDao.get(id);
	}


	@Override
	public List<CategoryModel> getAll() {
		// TODO Auto-generated method stub
		return  categoryDao.getAll();
	}

	
	

	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	public CategoryModel findById(int id) {
		// TODO Auto-generated method stub
		return categoryDao.findById(id);
	}

	@Override
	public List<CategoryModel> findByName(String keyword) {
		// TODO Auto-generated method stub
		return categoryDao.findByName(keyword);
	}

	@Override
	public CategoryModel get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
