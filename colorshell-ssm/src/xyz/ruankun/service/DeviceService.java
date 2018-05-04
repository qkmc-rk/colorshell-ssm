/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.service;

import java.util.List;

import xyz.ruankun.model.Device;

public interface DeviceService {

	/**
	 * ע���豸����Ҫ����
	 * @param device
	 * @return
	 */
	Integer regist(Device device);

	/**
	 * ɾ��һ���豸
	 * @param deviceId �豸��id����
	 */
	Integer deleteOneDevice(Integer deviceId);

	
	/**
	 * ����һ���豸
	 * @param deviceId �豸Id
	 * @param mac Ҫ���µ��豸��mac��ַ
	 * @param type  Ҫ���µ��豸��type
	 * @return  �豸�Ƿ���³ɹ�
	 */
	Integer updateDevice(Integer deviceId, String mac, String type);

	List<Device> getAll();
	
	/**
	 * ͨ���豸��token����һ���豸
	 * @param token
	 * @return device
	 */
	Device getOneDeviceByToken(String token);

}
