/**
* @author sherivey.Ruan  
* @date 2018年5月4日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.service;

import xyz.ruankun.model.Gas;
import xyz.ruankun.model.Humidity;
import xyz.ruankun.model.Illumination;
import xyz.ruankun.model.Ph;
import xyz.ruankun.model.Temperatrue;

public interface DeviceDataService{

	/**
	 * 设置数据，只有一条数据的时候
	 * @param data
	 * @return
	 */
	Integer setData(Illumination data);
	Integer setData(Gas data);
	Integer setData(Ph data);
	
	/**
	 * 设置数据，当有两条数据的时候
	 * @param data1
	 * @param data2
	 * @return
	 */
	Integer setDatas(Temperatrue data1,Humidity  data2);
}
