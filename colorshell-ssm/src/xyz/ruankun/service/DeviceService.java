/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.service;

import java.util.List;

import xyz.ruankun.model.Device;

public interface DeviceService {

	/**
	 * 注册设备，需要查重
	 * @param device
	 * @return
	 */
	Integer regist(Device device);

	/**
	 * 删除一个设备
	 * @param deviceId 设备的id号码
	 */
	Integer deleteOneDevice(Integer deviceId);

	
	/**
	 * 更新一个设备
	 * @param deviceId 设备Id
	 * @param mac 要更新的设备的mac地址
	 * @param type  要更新的设备的type
	 * @return  设备是否更新成功
	 */
	Integer updateDevice(Integer deviceId, String mac, String type);

	List<Device> getAll();
	
	/**
	 * 通过设备的token查找一个设备
	 * @param token
	 * @return device
	 */
	Device getOneDeviceByToken(String token);

}
