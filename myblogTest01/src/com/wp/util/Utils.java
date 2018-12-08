package com.wp.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import sun.misc.BASE64Encoder;

public class Utils {
	public static boolean isEmpty(String str){
		return null == str || str.length() == 0 || "".equals(str)
				|| str.matches("\\s*");
	}
	
	public static boolean isNotEmpty(String content){
		return !isEmpty(content);
	}
	
	/**
	 * 
	 * md5加密
	 * com.wp.util 
	 * 方法名：md5Base64
	 * 创建人：wenpeng
	 * 时间：2015年11月26日-下午4:28:45 
	 * @param str
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String md5Base64(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			return base64Encode(md5.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String base64Encode(byte[] b) {
		if (b == null) {
			return null;
		}
		return new BASE64Encoder().encode(b);
	}
	
	public static String conversionSpecialCharacters(String string) {
		return string.replaceAll("\\\\", "/");
	}
	/**
	 * 
	 * 获取文件后缀名
	 * com.wp.util 
	 * 方法名：getExt
	 * 创建人：wenpeng
	 * 时间：2016年1月10日-下午3:54:53 
	 * @param name  文件名称
	 * @param flag true加点 false 没有点
	 * @return String
	 * @exception  getExt("165as.4d.txt", true) === .txt
	 * @since  1.0.0
	 */
	public static String getExt(String name,boolean flag) {
		String result = "";
		if(isNotEmpty(name)) {
			if(flag) {
				result = name.substring(name.lastIndexOf("."),name.length());
			}else {
				result = name.substring(name.lastIndexOf(".")+1,name.length());
			}
			return result;
		}else {
			return result;
		}
		
	}
	/**
	 * 
	 * 格式化时间
	 * com.wp.util.tld 
	 * 方法名：formatDate
	 * 创建人：wenpeng
	 * 时间：2015年12月10日-上午10:16:29 
	 * @param date
	 * @param str
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String formatDate(Date date, String str) {
		if(date != null) {
			String dateString = new SimpleDateFormat(str).format(date);
			return dateString;
		}else {
			return "";
		}
	}
	/**
	 * 
	 * 获取随机名称
	 * com.wp.util 
	 * 方法名：generateFileName
	 * 创建人：wenpeng
	 * 时间：2016年1月10日-下午5:35:46 
	 * @param fileName 文件名称
	 * @param randomNum 随机数字
	 * @param dataPattern 时间格式化的样式
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	// 为上传文件自动分配文件名称，避免重复
	public static String generateFileName(String fileName,int randomNum,String dataPattern) {
		// 获得当前时间
		DateFormat format = new SimpleDateFormat(dataPattern);
		// 转换为字符串
		String formatDate = format.format(new Date());
		// 随机生成文件编号
		int random = new Random().nextInt(randomNum);
		// 获得文件后缀名称
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		// 组成一个新的文件名称
		return formatDate + random + extension;
	}
	/**
	 * 根据File文件的长度统计文件的大小
	 * 
	 * @param size
	 *            File的长度 file.lenght()
	 * @return 返回文件大小
	 */
	public static String countFileSize(long fileSize) {
		String fileSizeString = "";
		try {
			DecimalFormat df = new DecimalFormat("#.00");
			long fileS = fileSize;
			if (fileS == 0) {
				fileSizeString = "0KB";
			} else if (fileS < 1024) {
				fileSizeString = df.format((double) fileS) + "B";
			} else if (fileS < 1048576) {
				fileSizeString = df.format((double) fileS / 1024) + "KB";
			} else if (fileS < 1073741824) {
				fileSizeString = df
						.format(((double) fileS / 1024 / 1024) - 0.01)
						+ "MB";
			} else {
				fileSizeString = df.format((double) fileS / 1024 / 1024 / 1024)
						+ "G";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileSizeString;
	}
	public static void main(String[] args) {
		System.out.println(md5Base64(md5Base64("wxiaopadmin..")+"wp"));
		System.out.println(getExt("165as.4d.txt", true));
		System.out.println(generateFileName("test.txt",20,"yyyyMMddHHmmss"));
		
	}
}
