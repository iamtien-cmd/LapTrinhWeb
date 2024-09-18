package vn.iotstar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.model.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
import vn.iotstar.utils.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 507572562622801492L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        // Kiểm tra cookie "Nhớ tôi"
        Cookie[] cookies = req.getCookies();
        String savedUsername = null;
        String savedPassword = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
                    savedUsername = cookie.getValue();
                } else if (Constant.COOKIE_PASSWORD.equals(cookie.getName())) {
                    savedPassword = cookie.getValue();
                }
            }
        }

        if (savedUsername != null && savedPassword != null) {
            UserService service = new UserServiceImpl();
            User user = service.login(savedUsername, savedPassword);
            if (user != null) {
                session = req.getSession(true);
                session.setAttribute("account", user);
                resp.sendRedirect(req.getContextPath() + "/waiting");
                return;
            }
        }

        // Chuyển hướng về trang đăng nhập nếu không có session hoặc cookie
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Cấu hình mã hóa UTF-8
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy các tham số từ request
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        boolean isRememberMe = "on".equals(remember);

        // Kiểm tra tài khoản và mật khẩu không được để trống
        if (username.isEmpty() || password.isEmpty()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Gọi dịch vụ người dùng để xác thực
        UserService service = new UserServiceImpl();
        User user = service.login(username, password);

        // Kiểm tra kết quả đăng nhập
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            if (isRememberMe) {
                saveRememberMe(resp, username, password);
            } else {
                deleteRememberMeCookies(resp);
            }

            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    // Lưu cookie "Nhớ tôi"
    private void saveRememberMe(HttpServletResponse response, String username, String password) {
        Cookie usernameCookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        usernameCookie.setMaxAge(30 * 60); // Cookie có hiệu lực trong 30 phút
        response.addCookie(usernameCookie);

        Cookie passwordCookie = new Cookie(Constant.COOKIE_PASSWORD, password);
        passwordCookie.setMaxAge(30 * 60); // Cookie có hiệu lực trong 30 phút
        response.addCookie(passwordCookie);
    }

    // Xóa cookie "Nhớ tôi"
    private void deleteRememberMeCookies(HttpServletResponse response) {
        Cookie usernameCookie = new Cookie(Constant.COOKIE_REMEMBER, null);
        usernameCookie.setMaxAge(0);
        response.addCookie(usernameCookie);

        Cookie passwordCookie = new Cookie(Constant.COOKIE_PASSWORD, null);
        passwordCookie.setMaxAge(0);
        response.addCookie(passwordCookie);
    }
}
