/**
* @author sherivey.Ruan  
* @date 2018��5��4��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.test;

import org.junit.Test;

public class TestSwitch {

	@Test
	public void m() {
		String type = "11";
		switch(type) {
		case "00":
			System.out.println("1");
			break;
		case "01":
			System.out.println("11");
			break;
		case "10":
			System.out.println("111");
			break;
		case "11":
			System.out.println("1111");
			break;
		}
	}
}
