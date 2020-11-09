package vn.hust.edu.kiendao.controller;

import com.google.gson.Gson;
import vn.hust.edu.kiendao.model.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * -filesizethreshold : neu kich thuoc 1 filw qua dung luong cau hinh thi se dc luu truc tiep tren o dia ma ko thong qua
 * bo dem
 * - maxRequestSize : dung luong toi da cua 1 request (1 request co the chua nhieu file )
 * - maxFileSize: dung luong toi da cua 1 file
 */
@WebServlet(name = "UploadFileController", value="/api/v1/upload-file/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 *2 , maxRequestSize = 1024 * 1024 * 50, maxFileSize =1024 * 1024 * 50 )
public class UploadFileController extends HttpServlet {
    private Gson gson= new Gson();
    private JsonResult jsonResult= new JsonResult();
    public static final String save_directory= "file-upload";
    private String getFileName(Part part){
        String fileNameRs = null;
//thuộc tính header của đối tượng part tương ứng với key content-disposition
// thì sẽ chưa một một chuỗi có định dạng tương tự
// form-data; name="file"; filename="C:\a\file1.zip"
//từ chuỗi này mình lấy ra tên file và phần mở rộng của file.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s: items) {
            if(s.trim().startsWith("filename")) {
                //filename="C:\file1.zip"
                fileNameRs = s.substring(s.indexOf("=") + 2, s.length() - 1);
                fileNameRs = fileNameRs.replace("\\", "/");
                int i = fileNameRs.lastIndexOf("/");
                fileNameRs = fileNameRs.substring(i + 1);
            }
        }
        return fileNameRs;
    }
    private File getfolderUpload(){
        // kiem soat thu muc muon luu file
        String appPath = "A:\\";
        // 1 file nam trong 1 thu muc
         appPath+=save_directory + File.separator+ new Date().getTime();
         // xay ra 2th
        File folderUpload = new File(appPath);
        if(!folderUpload.exists()){
            folderUpload.mkdirs();
        }
        return folderUpload;
        // tbu muc muon upload vao thi phai tao ra 1 bien static folder
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String rs = null;
       try{
           /**
            * lay ra dc cac file nguoi dung truyen len
            * luu cac file
            */
           String message= null;
           List<String> stringList= new ArrayList<>();
           Collection<Part> collections =  request.getParts();
           if(collections != null){
               for (Part part: collections) {
                   // thuc hien luu file va ghi du lieu cua file
                   String fileName =getFileName(part);
                   // khi luu file thi luu ow dau
                   if(fileName != null){
                       // chi ra duong dan tuyet doi ma muon luu file
                       // phai co ham xu ly thu muc luu tru file getFolderUpload()
                       String filePath =getfolderUpload().getAbsolutePath() +File.separator+fileName;
                       System.out.println("Write file"+filePath);
                       part.write(filePath);
                       stringList.add(save_directory+"/"+fileName);
                   }
               }
               rs = gson.toJson(jsonResult.jsonsuccess(stringList));
           }else {
               rs= gson.toJson(jsonResult.jsonsuccess("file not found "));
           }
       } catch (Exception ex){
           ex.printStackTrace();
           rs = gson.toJson(jsonResult.jsonfail("Khong the upload file "));
       }
       response.getWriter().println(rs);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
