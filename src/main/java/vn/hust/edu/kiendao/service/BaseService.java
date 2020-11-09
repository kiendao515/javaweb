package vn.hust.edu.kiendao.service;

import java.sql.SQLException;
import java.util.List;

public interface BaseService<T> {
    List<T> findAll() throws SQLException;

    T findById(int id) throws SQLException;

    T insert(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean deleted(int id) throws SQLException;
}