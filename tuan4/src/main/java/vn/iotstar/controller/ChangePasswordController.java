package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
import vn.iotstar.model.User;

@WebServlet(urlPatterns = {"/ForgetPass"})
public class ChangePasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        // Chuyển tiếp đến trang ForgetPass.jsp
        req.getRequestDispatcher("/views/ForgetPass.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        /*if (session == null || session.getAttribute("account") == null) {
            // Nếu không có session, chuyển về trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
            return;
        }*/

        // Lấy thông tin người dùng hiện tại từ session
        User user = (User) session.getAttribute("account");

        // Lấy thông tin từ form
        String username = req.getParameter("username"); // Lấy từ session thay vì từ form để đảm bảo người dùng hiện tại
        String newpassword = req.getParameter("newpassword");
        String confirm = req.getParameter("confirmpassword");

        // Kiểm tra các trường nhập liệu
        if (username.isEmpty() || newpassword.isEmpty() || confirm.isEmpty()) {
            req.setAttribute("alert", "Bạn chưa nhập đầy đủ thông tin.");
            req.getRequestDispatcher("/views/ForgetPass.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu có khớp không
        if (!newpassword.equals(confirm)) {
            req.setAttribute("alert", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
            req.getRequestDispatcher("/views/ForgetPass.jsp").forward(req, resp);
            return;
        }

        // Thực hiện cập nhật mật khẩu
        UserService userService = new UserServiceImpl();
        boolean isUpdated = userService.updatePassword(username, newpassword);

        // Kiểm tra kết quả cập nhật
        if (isUpdated) {
            req.setAttribute("alert", "Mật khẩu đã được đổi thành công.");
            // Sau khi đổi mật khẩu thành công, chuyển về trang đăng nhập
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Đã xảy ra lỗi khi đổi mật khẩu.");
            req.getRequestDispatcher("/views/ForgetPass.jsp").forward(req, resp);
        }
    }
}
