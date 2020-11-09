package vn.hust.edu.kiendao.service_impl;

import vn.hust.edu.kiendao.model.Product;
import vn.hust.edu.kiendao.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
   private ProductService productService = new ProductServiceImpl();
    @Override
    public List<Product> findAll() throws SQLException {
        return productService.findAll() ;
    }

    @Override
    public Product findById(int id) throws SQLException {
        return id>0 ? productService.findById(id) : null;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        product.setDeleted(false);
        return productService.insert(product);
    }

    @Override
    public boolean update(Product product) throws SQLException {
        return product.getId()>0 ? productService.update(product) : false;
    }

    @Override
    public boolean deleted(int id) throws SQLException {
        return id>0? productService.deleted(id): false;
    }
}
