package com.wp.web.admin.statichtml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.wp.util.Utils;

/**
 * 
 * 工程名:wp_admin
 * 包名:com.wp.web.statichtml
 * 类名:StaticHtmlUtils	静态化工具类
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年3月29日-下午3:11:30 
 */
public class StaticHtmlUtils {
	
	/**
	 * 
	 * 静态化方法
	 * com.wp.web.statichtml 
	 * 方法名：staticContent
	 * 创建人：wenpeng
	 * 时间：2017年3月29日-下午3:37:46 
	 * @param id	内容ID
	 * @param cid	栏目ID
	 * @param link	静态链接地址
	 * @param templatePath	模板路径
	 * @param request	
	 * @param response
	 * @return String	
	 * @exception 
	 * @since  1.0.0
	 */
	public static  String  staticContent(Integer id,Integer cid,String link,String templatePath,HttpServletRequest request,HttpServletResponse response){
		//写入的静态目标文件
		File targetFile  = null;
		//返回的相对路径
		String fileNamePath =null;
		if(Utils.isNotEmpty(link)){//你已经生成了静态页面，就用原来生成静态页面文件进行覆盖操作
			String rootPath = request.getRealPath("/");//获取根目录
			File  rootFile = new File(rootPath);
			targetFile = new File(rootFile,link);//目标文件
			File parentPath = targetFile.getParentFile();//防止父级目录丢失，而造成静态化失败，判断是否父亲文件夹存在
			if(!parentPath.exists())parentPath.mkdirs();//如果不存在，就创建父级目录
			fileNamePath =  link;//返回相对路径
		}else{
			//如果没有静态页面
			//长生一个新的目录
			String filePath = "pages/c/"+Utils.formatDate(new Date(), "yyyy/MM/dd");
			//获取服务器根目录
			String rootPath = request.getRealPath(filePath);
			File rootFile = new File(rootPath);
			//如果不存在，就创建
			if(!rootFile.exists())rootFile.mkdirs();
			//长生新的静态文件名称
			String name = getRandomString(10)+".html";
			targetFile = new File(rootFile,name);
			//返回的名称
			fileNamePath =  filePath +"/"+ name;
		}
		
		
		FileOutputStream fos = null;
		ServletContext sc = request.getServletContext();
		//组装返回的路径
		try {
			//设定你的模板路径,内容模板三个，显示风格都不一样，
			RequestDispatcher rd = sc.getRequestDispatcher(templatePath);
			final ByteArrayOutputStream os = new ByteArrayOutputStream();
			final ServletOutputStream stream = new ServletOutputStream() {//匿名内部类，回调函数
				public void write(byte[] data, int offset, int length) {
					os.write(data, offset, length);
				}
				public void write(int b) throws IOException {
					os.write(b);
				}
				@Override
				public boolean isReady() {
					return false;
				}
				@Override
				public void setWriteListener(WriteListener writeListener) {
				}
				
			};
			final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"utf-8"));
			HttpServletResponse rep = new HttpServletResponseWrapper(response) {
				public ServletOutputStream getOutputStream() {
					return stream;
				}

				public PrintWriter getWriter() {
					return pw;
				}
			};
			
			//将id放入到作用域中
			request.setAttribute("id", id);
			request.setAttribute("channelId", cid);
			
			rd.include(request, rep);//发起请求
			pw.flush();
			fos = new FileOutputStream(targetFile);
			System.out.println(os);
			os.writeTo(fos);//讲源代码写入磁盘文件中
			return fileNamePath;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (ServletException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static String getRandomString(int length) {
		StringBuffer bu = new StringBuffer();
		String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
				"d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		Random random = new Random();
		while (bu.length() < length) {
			String temp = arr[random.nextInt(57)];
			if (bu.indexOf(temp) == -1) {
				bu.append(temp);
			}
		}
		return bu.toString();
	}

}
