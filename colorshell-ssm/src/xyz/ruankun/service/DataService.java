/**
* @author sherivey.Ruan  
* @date 2018年4月26日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.service;

import java.util.List;

import xyz.ruankun.custombean.NowDataBean;
import xyz.ruankun.model.Gas;
import xyz.ruankun.model.Humidity;
import xyz.ruankun.model.Illumination;
import xyz.ruankun.model.Ph;
import xyz.ruankun.model.Temperatrue;

public interface DataService {

	NowDataBean getCurrentData();

	/**
	 * 获取当前的温度值
	 * @return
	 */
	Temperatrue getCurrentTemp();

	/**
	 * 查询一段数据
	 * @param from
	 * @param to
	 * @return
	 */
	List<Temperatrue> getSomeTempRecords(Integer from, Integer to);
	
	/**
	 * 获取当前的湿度值
	 * @return
	 */
	Humidity getCurrentHumidity();

	/**
	 * 查询一段湿度数据
	 * @param from
	 * @param to
	 * @return
	 */
	List<Humidity> getSomeHumidityRecords(Integer from, Integer to);
	
	/**
	 * 获取当前的光照值
	 * @return
	 */
	Illumination getCurrentIllumination();

	/**
	 * 查询一段光照数据
	 * @param from
	 * @param to
	 * @return
	 */
	List<Illumination> getSomeIlluminationRecords(Integer from, Integer to);
	
	/**
	 * 获取当前的光照值
	 * @return
	 */
	Gas getCurrentGas();

	/**
	 * 查询一段光照数据
	 * @param from
	 * @param to
	 * @return
	 */
	List<Gas> getSomeGasRecords(Integer from, Integer to);
	
	
	
	
	
	/**
	 * 获取当前的光照值
	 * @return
	 */
	Ph getCurrentPh();

	/**
	 * 查询一段光照数据
	 * @param from
	 * @param to
	 * @return
	 */
	List<Ph> getSomePhRecords(Integer from, Integer to);
}
