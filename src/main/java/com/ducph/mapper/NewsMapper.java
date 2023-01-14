package com.ducph.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ducph.controller.model.NewsModel;

public class NewsMapper implements RowMapper<NewsModel> {

    @Override
    public NewsModel mapRow(ResultSet resultSet) {
        try {
            NewsModel news = new NewsModel();
            news.setId(resultSet.getLong("id"));
            news.setTitle(resultSet.getString("title"));
            news.setContent(resultSet.getString("content"));
            news.setCategoryId(resultSet.getLong("category_id"));
            news.setThumbnail(resultSet.getString("thumbnail"));

            if (resultSet.getTimestamp("created_date") != null) {
                news.setCreatedDate(resultSet.getTimestamp("created_date"));
            }
            if (resultSet.getString("created_by") != null) {
                news.setCreatedBy(resultSet.getString("created_by"));
            }
            if (resultSet.getTimestamp("modified_date") != null) {
                news.setModifiedDate(resultSet.getTimestamp("modified_date"));
            }
            if (resultSet.getString("modified_by") != null) {
                news.setModifiedBy(resultSet.getString("modified_by"));
            }

            return news;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
