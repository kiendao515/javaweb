package vn.hust.edu.kiendao.service_impl;

import vn.hust.edu.kiendao.dao.CategoryDao;
import vn.hust.edu.kiendao.dao_impl.CategoryDaoImpl;
import vn.hust.edu.kiendao.model.Category;
import vn.hust.edu.kiendao.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() throws SQLException {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) throws SQLException {
        return id > 0 ? categoryDao.findById(id): null;
    }

    @Override
    public Category insert(Category category) throws SQLException {
        category.setDeleted(false);
        return categoryDao.insert(category);
    }

    @Override
    public boolean update(Category category) throws SQLException {
        return category.getId() > 0 ? categoryDao.update(category): false;
    }

    @Override
    public boolean deleted(int id) throws SQLException {
        return id > 0 ? categoryDao.deleted(id) : false;
    }
}
