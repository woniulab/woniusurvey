package com.woniuxy.survey.core;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
	public static void main(String[] args) {
		// System.out.println((int)Math.ceil((float)21/10));
		// AliyunSendSMS.doSend("18682558655", 123456);
	}

	public static String generateDateTime(String format) {
		SimpleDateFormat formatter = 
				new SimpleDateFormat(format);
		Date date = new Date();
		String now = formatter.format(date.getTime());
		return now;
	}
	
	public static int generateRandom(int min, int max) {
		Random myrand = new Random();
		int temp = myrand.nextInt(min);
		int random = temp + (max - min) + 1;
		return random;
	}
	
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，不满用0补足
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code.toUpperCase();
	}

}
