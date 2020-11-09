package vn.hust.edu.kiendao.dao_impl;

import vn.hust.edu.kiendao.dao.ProductDao;
import vn.hust.edu.kiendao.model.MyConnection;
import vn.hust.edu.kiendao.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
    private MyConnection myConnection= new MyConnection();
    @Override
    public Product getObject(ResultSet resultSet) throws SQLException {
        Product product =null;
        product= new Product(resultSet.getInt("id"),resultSet.getString("name"),
                resultSet.getDouble("price"),resultSet.getString("image"),resultSet.getString("" +
                "specification"),resultSet.getString("introduction"),resultSet.getBoolean("sold_out"),
                resultSet.getInt("guarantee"),resultSet.getInt("bought"),resultSet.getDate("create_date"),
                resultSet.getInt("promotion"),resultSet.getBoolean("deleted")
                ,resultSet.getInt("menu_id"));
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String sql="select * from product where deleted=false";
        PreparedStatement preparedStatement=myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public Product findById(int id) throws SQLException {
        Product product= null;
        String sql="select * from products where deleted= false and id=?";
        PreparedStatement preparedStatement=myConnection.prepare(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet= preparedStatement.executeQuery();
        if(resultSet.next()){
            product = getObject(resultSet);
        }
        return product;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        Product product1= null;
        String sql="insert into products (name,price,image,specification,introduction,sold_out,guarantee,bought,create_date,promotion,deleted,menu_id) value(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement=myConnection.prepareUpdate(sql);
        preparedStatement.setString(1,product.getName());
        preparedStatement.setDouble(2,product.getPrice());
        preparedStatement.setString(3,product.getImageUrl());
        preparedStatement.setString(4,product.getSpecification());
        preparedStatement.setString(5,product.getIntro());
        preparedStatement.setBoolean(6,product.isSoldOut());
        preparedStatement.setInt(7,product.getGuarantee());
        preparedStatement.setInt(8,product.getBought());
        preparedStatement.setDate(9, (java.sql.Date) new Date());
        preparedStatement.setInt(10,product.getPromotion());
        preparedStatement.setBoolean(11,product.isDeleted());
        preparedStatement.setInt(12,product.getMenuId());
        // return thenumber of updated record
        int rs=preparedStatement.executeUpdate();
        if(rs>0){
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int id=resultSet.getInt(1);
                product1=findById(id);
            }
        }
        return product1;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        boolean result=false;
        String sql="update products set name =? where id=?";
        PreparedStatement preparedStatement= myConnection.prepareUpdate(sql);
        preparedStatement.setString(1,product.getName());
        preparedStatement.setInt(2,product.getId());
        int rs=preparedStatement.executeUpdate();
        if(rs>0){
            result=true;
        }
        return result;
    }

    @Override
    public boolean deleted(int id) throws SQLException {
        boolean deleted=false;
        String sql="update products set deleted=true where id=?";
        PreparedStatement preparedStatement=myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1,id);
        int rs= preparedStatement.executeUpdate();
        if(rs>0){
            deleted=true;
        }
        return deleted;
    }




}
