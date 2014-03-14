package com.lixiaodai.bookDinner.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

	public class DateUtil {

		private final static SimpleDateFormat dateFormat = new SimpleDateFormat();
	public static java.util.Date addDate(Date date, int day){
		java.util.Calendar c = java.util.Calendar.getInstance();
		  c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		  return c.getTime();
	}
	/**
	 * 返回毫秒
	 * @param date
	 * @return 返回毫秒
	 */
	private static long getMillis(Date date) {
		  java.util.Calendar c = java.util.Calendar.getInstance();
		  c.setTime(date);
		  return c.getTimeInMillis();
		 }
	
	public static long getDifferDay(Date startDate){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        /**
         * 两个日期相差的豪秒数
         */
        long dif =0;
		try {
			
			dif = df.parse(df.format(addDate(startDate, 4)).toString()).getTime() - df.parse(df.format(new Date()).toString()).getTime();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		long dayDiff = dif/(long)(24*3600000);
		return dayDiff;
	}
	/**
	 * 获得年月2012-08-25liudsh
	 */
	 public static String parseMonth(Date inDate)
	    {
		 	String result;
			String cMm;
			String nMm;
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String baseDate = sd.format(inDate);
	        int yy = Integer.parseInt(baseDate.substring(0, 4));
	        int mm = Integer.parseInt(baseDate.substring(5, 7));
//	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	        int nextMm = mm +1;
	        int nextYy = yy;
	        if(nextMm > 12){
	        	nextMm = 1;
	        	nextYy = yy + 1;
	        }
	        if(nextMm < 10){
	        	nMm = "0"+nextMm;
	        }else{
	        	nMm = ""+nextMm;
	        }
	        if(mm < 10){
	        	cMm = "0"+mm;
	        }else{
	        	cMm = ""+mm;
	        }
	        result = yy+"-"+cMm+"-01,"+nextYy+"-"+nMm+"-01";
	        return result;
	    }
	public static Integer formularMonth(Date sdate, Date edate) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(sdate);
		int startMonth=calendar.get(Calendar.MONTH);
		calendar.setTime(edate);
		int endMonth=calendar.get(Calendar.MONTH);
		return endMonth-startMonth+1;
	}
	
	public static boolean isInMonth(Date phaseDate, Timestamp endData) {
		Date firstDay=getFirstDayOfLastMonth(endData);
		Date lastDay=getLastDayOfLastMonth(endData);
		if(phaseDate.after(firstDay)&&phaseDate.before(lastDay)){
			return true;
		}
		return false;
	}
	    
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = null;
		try {
			String month=(calendar.get(Calendar.MONTH) + 1)<10?"0"+(calendar.get(Calendar.MONTH) + 1):(calendar.get(Calendar.MONTH) + 1)+"";
			d = sdf.parse(calendar.get(Calendar.YEAR) + ""
					+ month + "01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = null;
		try {
			String month=(calendar.get(Calendar.MONTH) + 1)<10?"0"+(calendar.get(Calendar.MONTH) + 1):(calendar.get(Calendar.MONTH) + 1)+"";
			d = sdf.parse(calendar.get(Calendar.YEAR) + ""
					+ month + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static Date getFirstDayOfLastMonth(Date date) {
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);	
		return getFirstDayOfMonth(c.getTime());
	}
	
	public static Date getLastDayOfLastMonth(Date date) {
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);	
		return getLastDayOfMonth(c.getTime());
	}

	/**
	 * 根据传入的格式化字符串，格式化传入的日期为一个字符串
	 * @param date 要格式化的日期
	 * @param formatString 格式化字符串
	 * @return 将日期格式化后的字符串
	 */
	public static String formatDate(Date date,String formatString){
		dateFormat.applyPattern(formatString);
		return dateFormat.format(date);
	}
	
	/**
	 * 根据传入的原始字符串、格式化字符串将原始字符串转化为Date类型
	 * @param sourceStr 原始字符串
	 * @param formatStr 格式化字符串
	 * @return 原始字符串根据格式化字符串转化为的日期
	 * @throws ParseException 日期转化异常
	 */
	public static Date parseDate(String sourceStr,String formatStr) throws ParseException{
		dateFormat.applyPattern(formatStr);
		return dateFormat.parse(sourceStr);
	}
}
