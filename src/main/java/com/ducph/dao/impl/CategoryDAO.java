package com.ducph.dao.impl;

import java.util.List;

import com.ducph.controller.model.CategoryModel;
import com.ducph.dao.ICategoryDAO;
import com.ducph.mapper.CategoryMapper;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

    @Override
    public List<CategoryModel> findAll() {
        String sql = "SELECT * FROM categories";
        return this.query(sql, new CategoryMapper());
    }
}
