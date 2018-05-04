/**
* @author sherivey.Ruan  
* @date 2018��5��4��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.service;

import xyz.ruankun.model.Gas;
import xyz.ruankun.model.Humidity;
import xyz.ruankun.model.Illumination;
import xyz.ruankun.model.Ph;
import xyz.ruankun.model.Temperatrue;

public interface DeviceDataService{

	/**
	 * �������ݣ�ֻ��һ�����ݵ�ʱ��
	 * @param data
	 * @return
	 */
	Integer setData(Illumination data);
	Integer setData(Gas data);
	Integer setData(Ph data);
	
	/**
	 * �������ݣ������������ݵ�ʱ��
	 * @param data1
	 * @param data2
	 * @return
	 */
	Integer setDatas(Temperatrue data1,Humidity  data2);
}
