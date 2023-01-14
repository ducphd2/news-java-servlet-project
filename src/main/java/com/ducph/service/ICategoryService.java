package com.ducph.service;

import java.util.List;

import com.ducph.controller.model.CategoryModel;

public interface ICategoryService {
    List<CategoryModel> findAll();
}
