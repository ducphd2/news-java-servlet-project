package com.ducph.dao.impl;

import java.util.List;

import com.ducph.controller.model.NewsModel;
import com.ducph.dao.INewsDAO;
import com.ducph.mapper.NewsMapper;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

    @Override
    public List<NewsModel> findByCategoryId(long categoryId) {
        String sql = "SELECT * FROM news WHERE category_id = ?";
        return this.query(sql, new NewsMapper(), categoryId);
    }

    @Override
    public Long save(NewsModel newsModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO news (title, content, ");
        sql.append("category_id, thumbnail, short_description, ");
        sql.append("created_date, created_by) ");
        sql.append("VALUES(?, ?, ?, ?, ?, ?, ?)");
        return this.insert(sql.toString(), newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryId(),
                newsModel.getThumbnail(), newsModel.getShortDescription(), newsModel.getCreatedDate(),
                newsModel.getCreatedBy());
    }

    @Override
    public NewsModel findById(long id) {
        String sql = "SELECT * FROM news WHERE id = ?";
        List<NewsModel> listNews = this.query(sql, new NewsMapper(), id);
        return listNews.isEmpty() ? null : listNews.get(0);
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM news WHERE id = ?";
        this.update(sql, id);
    }

    @Override
    public void update(long newsId, NewsModel newsModel) {
        StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?, ");
        sql.append("short_description = ?, content = ?, category_id = ?, ");
        sql.append("created_date = ?, created_by = ?, modified_date = ?, modified_by = ? ");
        sql.append("WHERE id = ?");
        this.update(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail(), newsModel.getShortDescription(),
                newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedDate(), newsModel.getCreatedBy(),
                newsModel.getModifiedDate(), newsModel.getModifiedBy(), newsId);
    }
}
