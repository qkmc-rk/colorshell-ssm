/**
* @author sherivey.Ruan  
* @date 2018��4��23��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.test;

import java.util.Date;

import org.junit.Test;

import xyz.ruankun.util.MD5Encoder;

public class TestMD5 {

	@Test
	public void mss() {
		String password = "ruankun520";
		//��������м��ܲ��� md5���� 128 bit
		String password2 = MD5Encoder.encode(password);
		
		//��ȡʱ���
		Date now = new Date();
		Long nowTime = now.getTime();
		password += nowTime;
		String token = MD5Encoder.encode(password);
		
		System.out.println(password2);
		System.out.println(token);
	}
}
