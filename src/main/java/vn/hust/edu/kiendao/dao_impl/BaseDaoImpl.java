package vn.hust.edu.kiendao.dao_impl;

import vn.hust.edu.kiendao.dao.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    @Override
    public List<T> getList(ResultSet resultSet) throws SQLException {
        List<T> list = new ArrayList<>();
        //sử dụng .next để cho con trỏ resultSet qua tất cả các bản ghi
        //.next trả về cho mình là true nếu có bản ghi, false không có bản ghi
        ///sử dụng vòng lặp while vì không biết chính xác có bao nhiều bản ghi
        //Ngay đầu tiền khi vừa thực hiện câu lệnh thì đối result nó chỉ
        //1 list bản ghi trả về sau đây sử dụng hàm .next() lần đâu tiền
        //để trỏ con trỏ resultSet về bản ghi đâu tiên.
        while(resultSet.next()) {
            //ở đây đối tượng resultSet là 1 bản ghi
            //sử dụng hàm getObject đã xây dựng sẵn để lấy về đối tượng
            //tương ứng
            T t = getObject(resultSet);
            if(t != null) list.add(t);
        }
        return list;
    }
}
