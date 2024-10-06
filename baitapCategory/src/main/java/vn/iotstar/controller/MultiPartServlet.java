package vn.iotstar.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
import vn.iotstar.utils.Constant;


@WebServlet("/multiPartServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10, // 10MB
                 maxRequestSize = 1024 * 1024 * 50) // 50MB
public class MultiPartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String SAVE_DIRECTORY = "upload";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SecurityException {
    	 response.setContentType("text/html");
    	response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
    	
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("account");
		 String fullname = request.getParameter("fullname");
         String phone = request.getParameter("phone");
        String uploadPath = Constant.UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
        String alertMsg ="";
        // String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        // upload vào thư mục project
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
        	
        	
    		if (u == null) {
    			 request.setAttribute("alert", "Bạn chưa đăng nhập");
    			 request.getRequestDispatcher("/views/multiPartServlet.jsp").forward(request, response);
    			 return;
    			 
    		}
    		else
    			if (fullname=="" || phone == "")
    			{
    				alertMsg ="Thông tin không được để trống";
         			request.setAttribute("alert", alertMsg);
         			request.getRequestDispatcher("/views/multiPartServlet.jsp").forward(request, response);
         			return;
    			}
    		else { 
             UserService service = new UserServiceImpl();
             
             if (service.checkExistPhone(phone)) {
     			alertMsg ="Số điện thoại đã tồn tại!";
     			request.setAttribute("alert", alertMsg);
     			request.getRequestDispatcher("/views/multiPartServlet.jsp").forward(request, response);
     			return;
     		}
        	
            String fileName = "";
           
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
                part.write(uploadPath + File.separator + fileName); // Thêm File.separator để tách đường dẫn đúng      
            }
           
            boolean isSuccess = service.updateProfile(u.getUserName(),fullname,phone,fileName);
            if (isSuccess){
            	alertMsg = "File " + fileName + " đã được tải lên thành công!";
            	request.setAttribute("alert", alertMsg);
    		} 
    		else {
    			alertMsg = "System error!";
    			request.setAttribute("alert", alertMsg);
    			request.getRequestDispatcher("/views/multiPartServlet.jsp").forward(request, response);
    			return;
    		}}
        } catch (FileNotFoundException fne) {
            request.setAttribute("alert", "Có lỗi xảy ra: " + fne.getMessage());
        }
        getServletContext().getRequestDispatcher("/views/result.jsp").forward(request, response);
        
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return Constant.DEFAULT_FILENAME;
    }
}
