package vn.hust.edu.kiendao.dao_impl;

import vn.hust.edu.kiendao.dao.CategoryDao;
import vn.hust.edu.kiendao.model.Category;
import vn.hust.edu.kiendao.model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

    private MyConnection myConnection = new MyConnection();

    //Hàm getObject có nhiệm vụ chuyển các thông tin của một bản ghi về
    //thông tin của một đối tượng.
    //Hàm nhận vào một đối tượng resultSet(một bản ghi) và trả về một
    //đối tượng (của java) category chứa thông tin tương ứng
    @Override
    public Category getObject(ResultSet resultSet) throws SQLException {
        Category category = null;
        resultSet.getInt("id"); //1
        resultSet.getString("name"); //Dây điện
        resultSet.getBoolean("deleted"); //false
        //C1: sử dụng contructor full tham số để tạo ra đối tượng
        //chứa các giá trị của các thuộc tính tương ứng ở bản ghi
        category = new Category(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getBoolean("deleted"));
        //C2: tạo ra một đối tượng category bằng contructor mặc định
        //và sử dụng các setter để đặt giá trị cho đối tượng (điển
        // hình cho thằng product có nhiều thuộc tính)
        return category;
    }

    //hàm có nhiệm vụ chuyển một list bản ghi (resultSet) về
    //một list đối tượng Java
//    @Override
//    public List<Category> getList(ResultSet resultSet) throws SQLException {
//        List<Category> list = new ArrayList<>();
//        //sử dụng .next để cho con trỏ resultSet qua tất cả các bản ghi
//        //.next trả về cho mình là true nếu có bản ghi, false không có bản ghi
//        ///sử dụng vòng lặp while vì không biết chính xác có bao nhiều bản ghi
//        //Ngay đầu tiền khi vừa thực hiện câu lệnh thì đối result nó chỉ
//        //1 list bản ghi trả về sau đây sử dụng hàm .next() lần đâu tiền
//        //để trỏ con trỏ resultSet về bản ghi đâu tiên.
//        while(resultSet.next()) {
//            //ở đây đối tượng resultSet là 1 bản ghi
//            //sử dụng hàm getObject đã xây dựng sẵn để lấy về đối tượng
//            //tương ứng
//            Category category = getObject(resultSet);
//            if(category != null) list.add(category);
//        }
//        return list;
//    }

    //Hàm findAll sẽ lấy tất cả các bản ghi được lưu trong bảng tương ứng
    @Override
    public List<Category> findAll() throws SQLException {
        //select * (tất cả các trường) from table (table là tên bảng
        // tương ứng muốn lấy bản ghì) where (điều kiện tìm kiêm các bản
        // ghi) deleted = false (chỉ lấy các bản ghi chưa bị xóa)
        String sql = "select * from category where deleted = false";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    //truyền vào một số int và trả về cho mình đối tượng chứa thông tin
    //của bản ghi có id tương ứng và bản ghi này chưa được xóa
    @Override
    public Category findById(int id) throws SQLException {
        Category category = null;
        //kiểm trả boolean trước để tối ưu thời gian tìm kiếm bản ghi
        String sql = "select * from category where deleted = false and id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //kiểm trả xem bản ghi đầu tiên có tồn tại hay không
        //nếu tồn tại mới sử dụng hàm getObject để lấy ra đối tượng.
        if(resultSet.next()) {
            category = getObject(resultSet);
        }
        return category;
    }

    @Override
    public Category insert(Category category) throws SQLException {
        Category newCategory = null;
        String sql = "insert into category (name, deleted) values (?,?)";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1,category.getName());
        preparedStatement.setBoolean(2,category.isDeleted());
        int rs = preparedStatement.executeUpdate();
        //nếu insert thành công thì rs là số lượng bản ghi vừa thay đổi dữ liệu
        if(rs > 0) {
            //sử dụng hàm getGeneratedKeys để trả về cho mình một
            //đối tượng resultSet đối tượng này chứa id tương ứng
            //mình vừa thêm vào (kiểu long).
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            //kiểm trả xem có bản ghi trả  về hay không
            if(resultSet.next()) {
                //sử dụng getLong(số thứ tự cột) (vì ở đây kết
                // quả trả về chỉ có 1 ổ)
                newCategory = findById((int) resultSet.getLong(1));
            }
        }
        return newCategory;
    }

    @Override
    public boolean update(Category category) throws SQLException {
        boolean result = false;
        String sql = "update category set name = ? where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setInt(2, category.getId());
        int rs = preparedStatement.executeUpdate();
        if(rs > 0) result = true;
        return result;
    }

    @Override
    public boolean deleted(int id) throws SQLException {
        boolean result = false;
        String sql = "update category set deleted = true where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        if(rs > 0) result = true;
        return result;
    }
}
