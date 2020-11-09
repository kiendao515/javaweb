package vn.hust.edu.kiendao.controller;

import com.google.gson.Gson;
import vn.hust.edu.kiendao.model.Category;
import vn.hust.edu.kiendao.model.JsonResult;
import vn.hust.edu.kiendao.service.CategoryService;
import vn.hust.edu.kiendao.service_impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryController",value = "/api/v1/category/*")
public class CategoryController extends HttpServlet {
    private CategoryService categoryService= new CategoryServiceImpl();
    private Gson gson= new Gson();

    private JsonResult jsonResult= new JsonResult();

    // cung caaps chuwcs nanwg them ban ghi

    /**
     * các cách truyền ttin từ client lên server
     * - truyền băng query string : chỉ truyền dc dữ liệu nguyên thủy ko truyền dc object
     * - truyền thông tin bằng body : truyền được dữ liệu dạng object '
     * - do đang làm vieccj với json nên cần chuyền về 1 đối tượng java
     * - getReader là bộ đọc body
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String rs = null;
       Category category = gson.fromJson(request.getReader(),Category.class);
        try {
            Category newcategory =  categoryService.insert(category);
            rs= gson.toJson(jsonResult.jsonsuccess(newcategory));
        } catch (SQLException e) {
            e.printStackTrace();
            rs= gson.toJson(jsonResult.jsonfail("Thêm category thất bại "));
        }
        response.getWriter().println(rs);
    }
// cung cap chuc nang tim kiem ban ghi
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * neu path-info /find-all, /find-by-id thi lay ra cac ban ghiahihi
         * ahihi
         * ahihi
         * ahihi
         * ahihi 
         */
       String rs=null;
       String pathInfo = request.getPathInfo();
       if(pathInfo != null){
           if(pathInfo.equals("/find-all")){
               try {
                   List<Category> categoryList= categoryService.findAll();
                   rs= gson.toJson(jsonResult.jsonsuccess(categoryList));
               } catch (SQLException e) {
                   e.printStackTrace();
                   rs = gson.toJson(jsonResult.jsonfail("Fail to load api find-all"));
               }
           }else if(pathInfo.equals("/find-by-id")){
               int id= Integer.parseInt(request.getParameter("id"));
               try {
                   Category category=  categoryService.findById(id);
                   rs= gson.toJson(jsonResult.jsonsuccess(category));
               } catch (SQLException e) {
                   e.printStackTrace();
                   rs= gson.toJson(jsonResult.jsonfail("fail to load api find-by-id"));
               }
           }else{
               response.sendError(403,"Lỗi ");
           }
       }else  {
           response.sendError(403,"Api ko ton tai ");
       }
        response.getWriter().println(rs);

    }
    // cung cấp chức năng xóa bản ghi
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String rs = null;
        try {
            int id =Integer.parseInt(request.getParameter("id"));
            rs= gson.toJson(jsonResult.jsonsuccess(categoryService.deleted(id)));
        } catch (Exception e) {
            e.printStackTrace();
            rs= gson.toJson(jsonResult.jsonfail("Khoong the xoa ban ghi "));
        }
        response.getWriter().println(rs);

    }
    // cung cấp chức năg sửa bản ghi
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs= null;
        Category category= gson.fromJson(request.getReader(),Category.class);
        try {
            Boolean update= categoryService.update(category);
            rs = gson.toJson(jsonResult.jsonsuccess(update));

        } catch (SQLException e) {
            e.printStackTrace();
            rs = gson.toJson(jsonResult.jsonfail("Update không thành công "));
        }
        response.getWriter().println(rs);
    }
}
