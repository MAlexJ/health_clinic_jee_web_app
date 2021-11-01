package com.malex.services.impl;

import com.malex.dao.CategoryDao;
import com.malex.dao.impl.CategoryDaoImpl;
import com.malex.entities.Category;
import com.malex.services.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }
}
