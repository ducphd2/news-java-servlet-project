package com.ducph.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.ducph.controller.model.CategoryModel;
import com.ducph.dao.ICategoryDAO;
import com.ducph.service.ICategoryService;

public class CategoryService implements ICategoryService {

    @Inject
    private ICategoryDAO categoryDao;

    @Override
    public List<CategoryModel> findAll() {
        return categoryDao.findAll();
    }
}
