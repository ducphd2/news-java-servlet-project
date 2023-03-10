package com.ducph.dao;

import java.util.List;

import com.ducph.controller.model.CategoryModel;

public interface ICategoryDAO extends IGenericDAO<CategoryModel> {
    List<CategoryModel> findAll();
}
