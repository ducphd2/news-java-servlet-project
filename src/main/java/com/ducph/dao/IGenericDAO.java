package com.ducph.dao;

import java.util.List;

import com.ducph.mapper.RowMapper;

public interface IGenericDAO<T> {
    List<T> query(String sql, RowMapper<T> rowMapper, Object... params);

    void update(String sql, Object... params);

    Long insert(String sql, Object... params);
}
