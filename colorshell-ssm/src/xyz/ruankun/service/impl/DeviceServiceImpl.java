/**
* @author sherivey.Ruan  
* @date 2018年4月27日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.ruankun.consts.DeviceConsts;
import xyz.ruankun.dao.DeviceMapper;
import xyz.ruankun.model.Device;
import xyz.ruankun.service.DeviceService;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceMapper deviceMapper;
	
	@Override
	public Integer regist(Device device) {
		String mac = device.getMac();
		Device device2 = null;
		try {
			device2 = deviceMapper.selectByMac(mac);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(device2 == null) {
			//没有找到,开始注册
			if(deviceMapper.insertSelective(device) > 0) {
				return DeviceConsts.DEVICE_REG_SUCCESS;
			}else {
				return DeviceConsts.DEVICE_REG_ERROR;
			}
		}else {
			//找到了
			return DeviceConsts.DEVICE_REG_REPEAT;
		}
	}

	@Override
	public Integer deleteOneDevice(Integer deviceId) {
		Integer rs = null;
		try {
			rs = deviceMapper.deleteByPrimaryKey(deviceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Integer updateDevice(Integer deviceId, String mac, String type) {
		
		Device device = deviceMapper.selectByPrimaryKey(deviceId);
		
		device.setMac(mac);
		
		device.setType(type);
		
		Integer rs = null;
		
		try {
			rs = deviceMapper.updateByPrimaryKeySelective(device);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public List<Device> getAll() {
		List<Device> devices = deviceMapper.selectAll();
		return devices;
	}
}
