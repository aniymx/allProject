package com.wp.auto;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wp.util.Utils;
import com.wp.util.WpFileUtils;

/**
 * 
 * 工程名:blog
 * 包名:com.wp.auto
 * 类名:AutoProject	自动构建模块
 * 创建人:wenpeng
 * Email:1091654568@qq.com
 * 时间：2017年4月2日-上午9:46:29 
 */
public class AutoProject {

	private static String beanName = "Log"; 
	private static String smallBeanName = beanName.toLowerCase();
	
	//包信息
	private static String beanPackage = "com.wp.bean";
	private static String daoPackage = "com.wp.dao.admin";
	private static String servicePackage = "com.wp.service.admin";
	private static String webPackage = "com.wp.web.admin";
	
	//创建者信息
	private static String author = "wenpeng";
	private static String email = "1091654568@qq.com";
	private static String description = "日志模块";
	private static String createTime = new SimpleDateFormat("yyyy年MM月dd日-下午hh:mm:ss").format(new Date());
	//目录结构
	private static String srcPath = "src/";
	private static String beanPath = "com/wp/bean/";
	private static String daoPath = "com/wp/dao/admin/";
	private static String servicePath = "com/wp/service/admin/";
	private static String serviceImplPath = "com/wp/service/admin/"+smallBeanName;
	private static String webPath = "com/wp/web/admin/"+smallBeanName+"/";
	private static String pagePath = "/WebRoot/WEB-INF/pages/admin/"+smallBeanName+"/";
	/*模板目录*/
	private static String beanTemplate = "template/bean.txt";
	private static String daoTemplate = "template/dao.txt";
	private static String daoSqlTemplate = "template/daoSql.txt";
	private static String serviceTemplate = "template/service.txt";
	private static String serviceImplTemplate = "template/serviceImpl.txt";
	private static String webTemplate = "template/web.txt";
	private static String listTemplate = "template/list.txt";
	private static String listTmTemplate = "template/template.txt";
	/*
	 * 创建bean
	 */
	public static void createBean(){
		String rootPath = getRoot(srcPath+beanPath);
		String content = WpFileUtils.readFileByLines(beanTemplate);
		content = replaceTemplate(content);
		WpFileUtils.createNewFile(rootPath+"/"+beanName+".java", content);
	}
	/*
	 * 创建dao
	 */
	public static void createDao(){
		String rootPath = getRoot(srcPath+daoPath); 
		String content = WpFileUtils.readFile(daoTemplate, "UTF-8");
		content = replaceTemplate(content);
		WpFileUtils.mkdir(rootPath+"/"+smallBeanName);
		WpFileUtils.createNewFile(rootPath+"/"+smallBeanName+"/I"+beanName+"Dao"+".java",content);
		
		String sqlContent = WpFileUtils.readFileByLines(daoSqlTemplate);
		sqlContent = replaceTemplate(sqlContent);
		WpFileUtils.createNewFile(rootPath+"/"+smallBeanName+"/"+smallBeanName+".xml",sqlContent);
	}
	/*
	 * 创建service
	 */
	public static void createService(){
		String rootPath = getRoot(srcPath+servicePath);
		String implPath = getRoot(srcPath+serviceImplPath);
		String serviceContent = WpFileUtils.readFileByLines(serviceTemplate);
		serviceContent = replaceTemplate(serviceContent);
		WpFileUtils.mkdir(implPath);
		System.out.println(rootPath+"/"+smallBeanName+"/I"+beanName+"Service"+".java");
		WpFileUtils.createNewFile(rootPath+"/"+smallBeanName+"/I"+beanName+"Service"+".java",serviceContent);
		String serviceImplCon = WpFileUtils.readFileByLines(serviceImplTemplate);
		serviceImplCon = replaceTemplate(serviceImplCon);
		WpFileUtils.createNewFile(rootPath+"/"+smallBeanName+"/"+beanName+"Service"+".java", serviceImplCon);
	}
	
	/*
	 * 创建controller
	 */
	public static void createWeb(){
		String rootPath = getRoot(srcPath+webPath);
		String content = WpFileUtils.readFileByLines(webTemplate);
		content = replaceTemplate(content);
		WpFileUtils.mkdir(rootPath);
		WpFileUtils.createNewFile(rootPath+"/"+beanName+"Controller"+".java", content);
	}
	public static void createPage(){
		String rootPath = getRoot(pagePath);
		WpFileUtils.mkdir(rootPath);
		String list = WpFileUtils.readFileByLines(listTemplate);
		String template = WpFileUtils.readFileByLines(listTmTemplate);
		list = replaceTemplate(list);
		template = replaceTemplate(template);
		WpFileUtils.createNewFile(rootPath+"/list.jsp", list);
		WpFileUtils.createNewFile(rootPath+"/template.jsp", template);
		
	}
	public static String replaceTemplate(String content){
		if(Utils.isNotEmpty(content)){
			content = content.replaceAll("\\[beanPackage\\]",beanPackage)
					.replaceAll("\\[daoPackage\\]",daoPackage)
					.replaceAll("\\[servicePackage\\]",servicePackage)
					.replaceAll("\\[webPackage\\]",webPackage)
					.replaceAll("\\[description\\]",description)
					.replaceAll("\\[author\\]",author)
					.replaceAll("\\[createTime\\]",createTime)
					.replaceAll("\\[email\\]",email)
					.replaceAll("\\[smallBeanName\\]",smallBeanName)
					.replaceAll("\\[beanName\\]",beanName);
			return content;
		}else{
			return "";
		}
	}
	
	public static String getRoot(String path){
		return new File(System.getProperty("user.dir"),path).getAbsolutePath();
	}
	public static void main(String[] args) throws IOException {
		createBean();
		createDao();
		createService();
		createWeb();
		createPage();
		//System.out.println(new SimpleDateFormat("yyyy年MM月dd日-下午hh:mm:ss").format(new Date()));
	}
}
