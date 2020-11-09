package vn.hust.edu.kiendao.filter;

import vn.hust.edu.kiendao.model.MyConnection;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * muc dich cua api:cung cap cac thao tac cho nguoi dung den database
 * muon thao tac vs db thi can phai ket noi can thu vien jdbc
 * thay vi viec trong cac servlet api viet cau lenh ket noi thi chuyen ve api filter
 */
@WebFilter(filterName = "ApiFilter",urlPatterns = "/api/*")
public class ApiFilter implements Filter {
    private  MyConnection myConnection= new MyConnection();
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");
        try {
            myConnection.connectDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
