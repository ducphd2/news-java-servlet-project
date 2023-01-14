package com.ducph.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.ducph.dao.IGenericDAO;
import com.ducph.mapper.RowMapper;

public class AbstractDAO<T> implements IGenericDAO<T> {
    public Connection getConnection() {
        ResourceBundle database = ResourceBundle.getBundle("db");
        try {
            Class.forName(database.getString("DRIVER_NAME"));
            String url = database.getString("DATABASE_URL");
            String user = database.getString("USER");
            String password = database.getString("PASSWORD");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
        List<T> results = new ArrayList<T>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            this.setParameters(statement, params);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void setParameters(PreparedStatement statement, Object... params) {
        for (int i = 0; i < params.length; i++) {
            Object param = params[i];
            int index = i + 1;
            try {
                if (param instanceof Long) {
                    statement.setLong(index, (Long) param);
                } else if (param instanceof String) {
                    statement.setString(index, (String) param);
                } else if (param instanceof Boolean) {
                    statement.setBoolean(index, (Boolean) param);
                } else if (param instanceof Float) {
                    statement.setFloat(index, (Float) param);
                } else if (param instanceof Double) {
                    statement.setDouble(index, (Double) param);
                } else if (param instanceof Timestamp) {
                    statement.setTimestamp(index, (Timestamp) param);
                } else if (param instanceof Integer) {
                    statement.setInt(index, (Integer) param);
                } else if (param == null) {
                    statement.setNull(index, Types.NULL);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            this.setParameters(statement, params);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Long insert(String sql, Object... params) {
        Long id = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            this.setParameters(statement, params);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
}
