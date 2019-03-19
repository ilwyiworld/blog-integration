package com.bestyiworld.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * 时间操作工具
 */
public class DateUtil {

	/**
	 * 生成ISO-8601 规范的时间格式
	 * @param date
	 * @return
	 */
	public static String formatISO8601DateString(Date date){
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		return  DateFormatUtils.format(date, pattern);
	}
	
	
	/**
	 * 获取反时间戳
	 * @return
	 */
	public static Long getCurrentReverseTime(){
		long longTime = System.currentTimeMillis()*1000000 + CalculateUtil.getNext(999999);
		return Long.MAX_VALUE - longTime;
	}
	
	/**
	 * 获取原时间戳
	 * @param reverseTime
	 * @return
	 */
	public static Long recoverReverseTime(Long reverseTime){
		long longTime = Long.MAX_VALUE - reverseTime;
		return longTime/1000000;
	}
	/**
	 * 生成页面普通展示时间
	 * @param date
	 * @return
	 */
	public static String formatNormalDateString(Date date){
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 生成yyyyMMddHHmmss格式
	 * @param date
	 * @return
	 */
	public static String formatNormalDateStringId(Date date){
		String pattern = "yyyyMMddHHmmss";
		return DateFormatUtils.format(date, pattern);
	}

}