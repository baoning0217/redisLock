package com.xishanqu.redislock.utils;

import org.springframework.util.ObjectUtils;

/**
 * @author hequan 2019年3月21日
 */

public class SequenceUtils {

	private final static String PAD_CHAR = "0";

	/**
	 * generate primary key for entity
	 * @param len
	 * @return
	 */
	public static String genSequence(int len){
		String datePrefix = DateUtils.getCurrentDate(DateUtils.YYYYMMDDHHMMSSSSS);
		String randomNum = random(len);
		return datePrefix + randomNum;
	}
	
	public static String genKey(String prefix,int len){
		String key = genSequence(len);
		if(!ObjectUtils.isEmpty(prefix)){
			key = prefix + key;
		}
		return key;
	}

	public static String genKey(String prefix){
		String key = genSequence(6);
		if(!ObjectUtils.isEmpty(prefix)){
			key = prefix + key;
		}
		return key;
	}

	/**
	 * generate a random number by length
	 * @param len
	 * @return
	 */
	public static String random(int len){
		if(len <= 0){
			return "";
		}
		long num = getRandom(len);
		String ran = num + "";
		if(ran.length() < len){
			int offset = len - ran.length();
			StringBuffer sb = new StringBuffer();
			for(int i = 0;i<offset; i++){
				sb.append(PAD_CHAR);
			}
			ran = sb.toString() + ran;
		}
		return ran;
		
	}

	public static long getRandom(int len) {
		double r = Math.random();
		for (int i = 0; i < len; i++) {
			r = r * 10;
		}
		long ret = (long) r;
		return ret;
	}

}
