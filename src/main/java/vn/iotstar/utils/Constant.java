package vn.iotstar.utils;

import java.io.InputStream;

public class Constant {
	public static final String SESSION_USERNAME = "username";
	public static final String COOKIE_REMEMBER ="username";
	public static final String COOKIE_PASSWORD = "password";
	
	public static final String DEFAULT_FILENAME = "default.file";
	public static final String UPLOAD_DIRECTORY = "D:\\LapTrinhWeb\\upload";
	public static final String DIR = "D:\\LapTrinhWeb\\upload";
	
	public static final int MAX_FILE_SIZE = 1024 *1024 *40;
	public static final int MAX_REQUEST_SIZE = 1024 * 1024 *50;
	
	public static final String CLOUDINARY_CLOUD_NAME = " dbjz16ctt";
	public static final String CLOUDINARY_API_KEY = "576632571682623";
	public static final String CLOUDINARY_API_SECRET ="ikPEbngxnKwAw - XkvR1WVEaQZcI";
	
	public static byte[] inputStreamToByteArray(InputStream istream) {
		try {
			byte[] targetArray = new byte[istream.available()];
			istream.read(targetArray);
			return targetArray;
		} catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}
}
