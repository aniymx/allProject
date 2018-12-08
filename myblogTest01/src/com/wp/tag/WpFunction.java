package com.wp.tag;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.wp.tag.ip.TmIpUtil;
import com.wp.util.Utils;

public class WpFunction {
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
	 * 获取时间差
	 * com.wp.util.tld 
	 * 方法名：getTimeFormat
	 * 创建人：wenpeng
	 * 时间：2015年12月10日-上午10:16:43 
	 * @param startTime
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getTimeFormat(Date startTime){
		try{
			long startTimeMills = startTime.getTime();
			long endTimeMills = System.currentTimeMillis();
			long diff = (endTimeMills - startTimeMills)/1000;//秒
			long day_diff  = (long) Math.floor(diff/86400);//天
			StringBuffer buffer = new StringBuffer();
			if(day_diff<0){
				return "[error],时间越界...";
			}else{
				if(day_diff==0 && diff<60){
					if(diff==0)diff=1;
					buffer.append(diff+"秒前");
				}else if(day_diff==0 && diff<120){
					buffer.append("1 分钟前");
				}else if(day_diff==0 && diff<3600){
					buffer.append(Math.round(Math.floor(diff/60))+"分钟前");
				}else if(day_diff==0 && diff<7200){
					buffer.append("1小时前");
				}else if(day_diff==0 && diff<86400){
					buffer.append(Math.round(Math.floor(diff/3600))+"小时前");
				}else if(day_diff==1){
					buffer.append("1天前");
				}else if(day_diff<7){
					buffer.append(day_diff+"天前");
				}else if(day_diff <30){
					buffer.append(Math.round(Math.ceil( day_diff / 7 )) + " 星期前");
				}else if(day_diff >=30 && day_diff<=179 ){
					buffer.append(Math.round(Math.ceil( day_diff / 30 )) + "月前");
				}else if(day_diff >=180 && day_diff<365){
					buffer.append("半年前");
				}else if(day_diff>=365){
					buffer.append(Math.round(Math.ceil( day_diff /30/12))+"年前");
				}
			}
			return buffer.toString();
		}catch(Exception ex){
			return "";
		}
	}
	/**
	 * 
	 * 转换字母
	 * com.wp.util.tld 
	 * 方法名：getCharacter
	 * 创建人：wenpeng
	 * 时间：2015年12月25日-下午9:23:52 
	 * @param x
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getCharacter(int x) {
		return String.valueOf((char)64+x);
	}
	
	/**
	 * 
	 * 将数字转换成对应的中文
	 * com.wp.util.tld 
	 * 方法名：chinesCharacter
	 * 创建人：wenpeng
	 * 时间：2015年12月25日-下午10:26:50 
	 * @param num
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String chinesCharacter(int num){
	    String resultNumber = null;
	    if(num > 10000 || num < 0){
	        return "";
	    }
	    HashMap chnNumbers = new HashMap();
	    chnNumbers.put(0, "零");
	    chnNumbers.put(1, "一");
	    chnNumbers.put(2, "二");
	    chnNumbers.put(3, "三");
	    chnNumbers.put(4, "四");
	    chnNumbers.put(5, "五");
	    chnNumbers.put(6, "六");
	    chnNumbers.put(7, "七");
	    chnNumbers.put(8, "八");
	    chnNumbers.put(9, "九");
	 
	    HashMap unitMap = new HashMap();
	    unitMap.put(1, "");
	    unitMap.put(10, "十");
	    unitMap.put(100, "百");
	    unitMap.put(1000, "千");
	    int[] unitArray = {1000, 100, 10, 1};
	 
	    StringBuilder result = new StringBuilder();
	    int i = 0;
	    while(num > 0){
	        int n1 = num / unitArray[i];
	        if(n1 > 0){
	            result.append(chnNumbers.get(n1)).append(unitMap.get(unitArray[i]));
	        }
	        if(n1 == 0){
	            if(result.lastIndexOf("零") != result.length()-1){
	                result.append("零");
	            }
	        }
	        num = num % unitArray[i++];
	        if(num == 0){
	            break;
	        }
	    }
	    resultNumber = result.toString();
	    if(resultNumber.startsWith("零")){
	        resultNumber = resultNumber.substring(1);
	    }
	    if(resultNumber.startsWith("一十")){
	        resultNumber = resultNumber.substring(1);
	    }
	    return resultNumber;
	}
	/**
	 * 
	 * 转换为中文金额
	 * com.wp.util.tld 
	 * 方法名：getMoney
	 * 创建人：wenpeng
	 * 时间：2015年12月25日-下午10:36:28 
	 * @param money
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getMoney(Double money) {
		TransRMB t2r = new TransRMB();
		return t2r.cleanZero(t2r.splitNum(t2r.roundString(String.valueOf(money))));
	}
	/**
	 * 
	 * 得到ip地址的位置
	 * com.wp.util.tld 
	 * 方法名：getIpAddress
	 * 创建人：wenpeng
	 * 时间：2015年12月25日-下午11:05:22 
	 * @param ip
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getIpAddress(String ip) {
		if(Utils.isEmpty(ip))return "匿名";
		return TmIpUtil.ipLocation(ip);
	}
	/**
	 * 
	 * 检测内容里是否包含搜索的字符串
	 * com.wp.util.tld 
	 * 方法名：indexOf
	 * 创建人：wenpeng
	 * 时间：2017年3月23日-上午11:31:45 
	 * @param content
	 * @param search
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public static int indexOf(String content,String search){
		return content.indexOf(search);
	}
	public static void main(String[] args) {
		System.out.println(getIpAddress("111.124.203.219"));
		System.out.println(indexOf("asdasd", "132"));
	}
//	public static void main(String[] args) throws ParseException {
//		System.out.println(getTimeFormat(new SimpleDateFormat("yyyy-MM-dd").parse("2015-11-10")));
//		System.out.println(getCharacter(65));
//	}
}
