package com.ducph.service;

import java.util.List;

import com.ducph.controller.model.NewsModel;

public interface INewsService {
    List<NewsModel> findByCategoryId(long categoryId);

    NewsModel findById(long id);

    NewsModel save(NewsModel news);

    NewsModel update(long newsId, NewsModel news);

    void deleteById(long id);

    void deleteByIds(long[] ids);
}
