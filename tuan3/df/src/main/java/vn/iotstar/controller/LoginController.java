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
import vn.iotstar.dao.UserService;
import vn.iotstar.dao.impl.*;




@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 507572562622801492L;
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			 throws ServletException, IOException {
			  HttpSession session = req.getSession(false);
			  if (session != null && session.getAttribute("account") != null) {
			  resp.sendRedirect(req.getContextPath()+ "/waiting");
			  return;
			  }
			// Check cookie
			  Cookie[] cookies = req.getCookies();
			  if (cookies != null) {
			  for (Cookie cookie : cookies) {
			  if (cookie.getName().equals("username")) {
			  session = req.getSession(true);
			  session.setAttribute("username", cookie.getValue());
			  resp.sendRedirect(req.getContextPath()+ "/waiting");
			  return;
			  }
			  }
			  }
			  req.getRequestDispatcher("/login.jsp").forward(req, resp);
			  }
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 resp.setContentType("text/html");
		 resp.setCharacterEncoding("UTF-8");
		 req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		 String password = req.getParameter("password");
		 String alertMsg="";
		 boolean isRememberMe = false;
		 String remember = req.getParameter("remember");

		 if("on".equals(remember)){
			 isRememberMe = true; 
		 }
		 if(username.isEmpty() || password.isEmpty()){
			 alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			  req.setAttribute("alert", alertMsg);
			  req.getRequestDispatcher("/login.jsp").forward(req, resp);
			  return;
			  }

		  UserService service = new UserServiceImpl();

		  User user = service.login(username, password);

		  if(user!=null){
			  HttpSession session = req.getSession(true);
			  session.setAttribute("account", user);
			  if(isRememberMe){
				  saveRemeberMe(resp, username);
			  }

			  resp.sendRedirect(req.getContextPath()+"/waiting");
		  }
		  else{
			  alertMsg = username + "  " + password + " Tài khoản hoặc mật khẩu không đúng";
			  req.setAttribute("alert", alertMsg);
			  req.getRequestDispatcher("/login.jsp").forward(req, resp);
		  }
		}
		private void saveRemeberMe(HttpServletResponse response, String username){
		 Cookie cookie = new Cookie("username", username);
		 cookie.setMaxAge(30*60);
		 response.addCookie(cookie);
		 }
}

