package com.ducph.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ducph.controller.model.NewsModel;
import com.ducph.dao.INewsDAO;
import com.ducph.service.INewsService;

public class NewsService implements INewsService {

    @Inject
    private INewsDAO newsDao;

    @Override
    public List<NewsModel> findByCategoryId(long categoryId) {
        return newsDao.findByCategoryId(categoryId);
    }

    @Override
    public NewsModel save(NewsModel news) {
        news.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        // Set username to "CreatedBy" field when user logged in
        Long newsId = newsDao.save(news);
        return this.findById(newsId);
    }

    @Override
    public NewsModel findById(long id) {
        return this.newsDao.findById(id);
    }

    @Override
    public NewsModel update(long newsId, NewsModel updateNews) {
        NewsModel oldNews = this.findById(newsId);
        updateNews.setCreatedDate(oldNews.getCreatedDate());
        updateNews.setCreatedBy(oldNews.getCreatedBy());
        updateNews.setModifiedDate(oldNews.getModifiedDate());
        updateNews.setModifiedBy(oldNews.getModifiedBy());
        this.newsDao.update(newsId, updateNews);

        return this.findById(newsId);
    }

    @Override
    public void deleteById(long id) {
        this.newsDao.delete(id);
    }

    @Override
    public void deleteByIds(long[] ids) {
        for (long id : ids) {
            this.newsDao.delete(id);
        }
    }
}
