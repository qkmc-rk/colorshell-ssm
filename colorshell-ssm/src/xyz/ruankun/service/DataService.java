/**
* @author sherivey.Ruan  
* @date 2018��4��26��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
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
	 * ��ȡ��ǰ���¶�ֵ
	 * @return
	 */
	Temperatrue getCurrentTemp();

	/**
	 * ��ѯһ������
	 * @param from
	 * @param to
	 * @return
	 */
	List<Temperatrue> getSomeTempRecords(Integer from, Integer to);
	
	/**
	 * ��ȡ��ǰ��ʪ��ֵ
	 * @return
	 */
	Humidity getCurrentHumidity();

	/**
	 * ��ѯһ��ʪ������
	 * @param from
	 * @param to
	 * @return
	 */
	List<Humidity> getSomeHumidityRecords(Integer from, Integer to);
	
	/**
	 * ��ȡ��ǰ�Ĺ���ֵ
	 * @return
	 */
	Illumination getCurrentIllumination();

	/**
	 * ��ѯһ�ι�������
	 * @param from
	 * @param to
	 * @return
	 */
	List<Illumination> getSomeIlluminationRecords(Integer from, Integer to);
	
	/**
	 * ��ȡ��ǰ�Ĺ���ֵ
	 * @return
	 */
	Gas getCurrentGas();

	/**
	 * ��ѯһ�ι�������
	 * @param from
	 * @param to
	 * @return
	 */
	List<Gas> getSomeGasRecords(Integer from, Integer to);
	
	
	
	
	
	/**
	 * ��ȡ��ǰ�Ĺ���ֵ
	 * @return
	 */
	Ph getCurrentPh();

	/**
	 * ��ѯһ�ι�������
	 * @param from
	 * @param to
	 * @return
	 */
	List<Ph> getSomePhRecords(Integer from, Integer to);
}
