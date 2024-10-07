package vn.iotstar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns= {"/image"})
public class DownloadImageController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5692878611530457394L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String fileName = req.getParameter("fname");
	    
	    if (fileName == null || fileName.isEmpty()) {
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "File name is missing");
	        return;
	    }

	    File file = new File(Constant.DIR, fileName);
	    resp.setContentType("image/jpeg");

	    
	    if (file.exists() && file.isFile()) {
	        resp.setContentLength((int) file.length());
	        try (FileInputStream fileInputStream = new FileInputStream(file);
	             OutputStream outputStream = resp.getOutputStream()) {
	            IOUtils.copy(fileInputStream, outputStream); 
	            outputStream.flush(); 
	        } catch (IOException e) {
	            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error reading file");
	        }
	    } else {
	        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
	    }
	}
}
