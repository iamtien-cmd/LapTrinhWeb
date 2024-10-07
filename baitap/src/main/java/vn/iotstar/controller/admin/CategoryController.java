package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.model.CategoryModel;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import static vn.iotstar.utils.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/insert", "/admin/category/add",
		"/admin/category/update", "/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = -7118185900565619848L;
	public ICategoryService cateservice = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("categories")) {
			List<CategoryModel> list = cateservice.getAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("insert") || url.contains("add")) {
			
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		} else if (url.contains("update") || url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id")); 
			CategoryModel cate = cateservice.findById(id);
			req.setAttribute("cate", cate);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id")); 
			System.out.println(id);
			cateservice.delete(id);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("search")) {
			req.getRequestDispatcher("/views/admin/categorySearch.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String fname="";
		if (url.contains("/admin/category/update")) {
			int categoryid = Integer.parseInt(req.getParameter("categoryid")); 
			String categoryname = req.getParameter("categoryname");
			String active = req.getParameter("active");
			boolean activee = Boolean.parseBoolean(active);
			
			CategoryModel category = new CategoryModel();
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			category.setActive(activee);
			// lưu hình cũ
			CategoryModel cateId = cateservice.findById(categoryid);
			String fileold = cateId.getImages();
			// xử lý images
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if(part.getSize()>0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "."+ ext;
					// up load file
					part.write(uploadPath + "/" +fname);
					// ghi tên file vào data
					category.setImages(fname);
				}
				else {
					category.setImages(fileold);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			cateservice.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} 
		else if (url.contains("/admin/category/insert")) {
			CategoryModel category = new CategoryModel();
			String categoryname = req.getParameter("categoryname");
			String active = req.getParameter("active");
			boolean activee = Boolean.parseBoolean(active);
			category.setCategoryname(categoryname);
			category.setActive(activee);
			String uploadPath = UPLOAD_DIRECTORY;
		
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if(part.getSize()>0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "."+ ext;
					// up load file
					part.write(uploadPath + "/" +fname);
					// ghi tên file vào data
					category.setImages(fname);
				}
				else {
					category.setImages("bong.png");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			System.out.println(categoryname + " " + fname+" " + active );
			cateservice.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			
		} else if (url.contains("search")) {
			req.getRequestDispatcher("/views/admin/categorySearch.jsp").forward(req, resp);
		}
	}

	
	
}
