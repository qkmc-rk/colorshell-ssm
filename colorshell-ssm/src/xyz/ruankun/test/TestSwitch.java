/**
* @author sherivey.Ruan  
* @date 2018年5月4日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
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
