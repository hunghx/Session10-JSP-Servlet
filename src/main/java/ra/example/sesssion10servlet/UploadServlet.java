package ra.example.sesssion10servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,  // 10 MB   vung nhớ tạm trên server
        maxFileSize = 1024 * 1024 * 50,       // 50 MB    kích thước tối đa của 1 file
        maxRequestSize = 1024 * 1024 * 100)   // kích thuước toi da của request
@WebServlet(name = "UploadServlet", value = "/UploadServlet")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lấy ra file upload
        String pathUpload = getServletContext().getRealPath("/uploads");
        File file = new File(pathUpload);
        System.out.println(pathUpload);
        if (!file.exists()){
            file.mkdirs();
        }
        Collection<Part> images = request.getParts(); // lây danh sách các file gửi lên
        List<String> list = new ArrayList<>();
        for (Part part : images) {
            if (part.getName().equals("images")) {
                String fileName = part.getSubmittedFileName(); // lấy đường dẫn sau khi uppload
                list.add(fileName);
                // upload
                Files.copy(part.getInputStream(), Paths.get(pathUpload+File.separator+fileName)) ; //upload vào đường dẫn ch dịnh
            }
        }
        request.setAttribute("listUrl" ,list); // truyền thuộc tinhsh sang trang jsp
        request.getRequestDispatcher("/image.jsp").forward(request,response);
    }
}
 
