package vn.hust.edu.kiendao.model;

import vn.hust.edu.kiendao.common.AppConfig;

import java.sql.*;

public class MyConnection {
    public static Connection connection = null;
    //kiểm tra đã tồn tại thư viện hay chưa
    public void driverTest() throws ClassNotFoundException {
        try {
            Class.forName(AppConfig.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("JDBC Driver not found" + e.getMessage());
        }
    }

    public Connection connectDB() throws ClassNotFoundException, SQLException {
        if(connection==null){
            driverTest();
            try {
                connection = DriverManager.getConnection(AppConfig.URL_DATABASE,
                        AppConfig.USERNAME, AppConfig.PASSWORD);
                System.out.println("Connect DB successfully");
            } catch (SQLException throwables) {
                throw new SQLException("Connect DB fail " + throwables.getMessage());
            }
        }
        return connection;
    }

    public PreparedStatement prepare(String sql) {
        System.out.println(">> " + sql);
        try {
            return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public PreparedStatement prepareUpdate(String sql) {
        System.out.println(">> " + sql);
        try {
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    //sau khi thao tác với database xong thì thực hiện giải phóng tài nguyên
    public void closeConnection() throws SQLException {
        if(connection != null) {
            connection.close();
            System.out.println("Connection is closed");
        }
    }
}
