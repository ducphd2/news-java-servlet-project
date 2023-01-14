package com.ducph.dao;

import java.util.List;

import com.ducph.controller.model.NewsModel;

public interface INewsDAO extends IGenericDAO<NewsModel> {
    List<NewsModel> findByCategoryId(long categoryId);

    NewsModel findById(long id);

    Long save(NewsModel newsModel);

    void delete(long id);

    void update(long newsId, NewsModel newsModel);
}
