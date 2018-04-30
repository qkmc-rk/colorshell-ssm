/**
* @author sherivey.Ruan  
* @date 2018年4月23日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.test;

import java.util.Date;

import org.junit.Test;

import xyz.ruankun.util.MD5Encoder;

public class TestMD5 {

	@Test
	public void mss() {
		String password = "ruankun520";
		//对密码进行加密操作 md5加密 128 bit
		String password2 = MD5Encoder.encode(password);
		
		//获取时间戳
		Date now = new Date();
		Long nowTime = now.getTime();
		password += nowTime;
		String token = MD5Encoder.encode(password);
		
		System.out.println(password2);
		System.out.println(token);
	}
}
