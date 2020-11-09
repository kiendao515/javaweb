package vn.hust.edu.kiendao.controller;

import com.google.gson.Gson;
import vn.hust.edu.kiendao.dao.ProductDao;
import vn.hust.edu.kiendao.dao_impl.ProductDaoImpl;
import vn.hust.edu.kiendao.model.JsonResult;
import vn.hust.edu.kiendao.model.Product;
import vn.hust.edu.kiendao.service.ProductService;
import vn.hust.edu.kiendao.service_impl.ProductServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", value="/api/v1/pthroduct/*")
public class ProductController extends HttpServlet {
    private ProductService productService= new ProductServiceImpl();
    private Gson gson= new Gson();
    private JsonResult jsonResult = new JsonResult();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String rs = null;
        Product product =gson.fromJson(request.getReader(),Product.class);
        try {
            Product newProduct =  productService.insert(product);
            rs = gson.toJson(jsonResult.jsonsuccess(newProduct));
        } catch (SQLException e) {
            e.printStackTrace();
            rs = gson.toJson(jsonResult.jsonfail("Không thể thêm mới sản phẩm "));
        }

        response.getWriter().println(rs);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = null;
        String pathInfo = request.getPathInfo();
        if(pathInfo != null){
            if(pathInfo.equals("/find-all")){
                try {
                    List<Product> productList= productService.findAll();
                    rs = gson.toJson(jsonResult.jsonsuccess(productList));
                } catch (SQLException e) {
                    e.printStackTrace();
                    rs = gson.toJson(jsonResult.jsonfail("Api có lỗi "));
                }

            }else if(pathInfo.equals("/find-by-id")){
                int id =Integer.parseInt(request.getParameter("id"));
                try {
                    Product product= productService.findById(id);
                    rs = gson.toJson(jsonResult.jsonsuccess(product));
                } catch (SQLException e) {
                    e.printStackTrace();
                    rs = gson.toJson(jsonResult.jsonfail("Lỗi không thể tìm kiểm sản phẩm "));
                }
            }
        }
        response.getWriter().println(rs);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            rs = gson.toJson(jsonResult.jsonsuccess(productService.deleted(id)));
        } catch (Exception e) {
            e.printStackTrace();
            rs = gson.toJson(jsonResult.jsonfail("không thể xóa sản phẩm "));
        }
        response.getWriter().println(rs);

    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = null;
        Product product= gson.fromJson(request.getReader(),Product.class);
        try {
            rs = gson.toJson(jsonResult.jsonsuccess(productService.update(product)));
        } catch (SQLException e) {
            e.printStackTrace();
            rs= gson.toJson(jsonResult.jsonfail("Không thể cập nhập thay đổi "));
        }

        response.getWriter().println(rs);

    }
}
