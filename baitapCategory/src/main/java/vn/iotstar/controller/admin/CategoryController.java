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
import vn.iotstar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,

		maxFileSize = 1024 * 1024 * 5,

		maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/insert", "/admin/category/update",
		"/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118185900565619848L;
	public ICategoryService cateservice = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		List<CategoryModel> list = cateservice.findAll();
		req.setAttribute("listcate", list);
		
		if (url.contains("insert")) {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		} else if (url.contains("categories")) {
			
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel cate = cateservice.get(id);
			req.setAttribute("cate", cate);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			cateservice.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("search")) {
			req.getRequestDispatcher("/views/admin/categorySearch.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("insert")) {
		
			String categoryname = req.getParameter("categoryname");
			String image = req.getParameter("images");
			String active = req.getParameter("active");
			CategoryModel cate = new CategoryModel();
			cate.setCategoryname(categoryname);

			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					cate.setImages(image);
				} else {
					cate.setImages(Constant.DEFAULT_FILENAME);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			cate.setActive(active.equals("true"));
			cateservice.insert(cate);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			String image = req.getParameter("images");
			String active = req.getParameter("active");

			CategoryModel cate = new CategoryModel();
			cate.setCategoryid(id);
			cate.setCategoryname(categoryname);
			cate.setImages(image);
			cate.setActive(active.equals("true"));
			CategoryModel cateold = new CategoryModel();
			cateold = cateservice.get(id);
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					cate.setImages(fname);
				} else {
					cate.setImages(cateold.getImages());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cateservice.update(cate);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("search")) {
			req.getRequestDispatcher("/views/admin/categorySearch.jsp").forward(req, resp);
		}

	}

}
