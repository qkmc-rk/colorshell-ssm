/**
* @author sherivey.Ruan  
* @date 2018年5月4日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import xyz.ruankun.custombean.ReturnBean;
import xyz.ruankun.model.Device;
import xyz.ruankun.model.Gas;
import xyz.ruankun.model.Humidity;
import xyz.ruankun.model.Illumination;
import xyz.ruankun.model.Ph;
import xyz.ruankun.model.Temperatrue;
import xyz.ruankun.service.DeviceDataService;
import xyz.ruankun.service.DeviceService;

/**
 * 功能：采集硬件端数据
 * @author Sherivey.Ruan
 *
 */
@Controller("/device/data")
public class DeviceDataController {

	@Autowired
	DeviceService deviceService;
	
	@Autowired
	DeviceDataService deviceDataService;
	
	/**
	 *  设备传过来的数据可以存储到数据库
	 * @param token  设备的秘钥信息
	 * @param type  数据类型
	 * @param data1  数据1
	 * @param data2  数据2
	 * @return 是否增加数据成功
	 */
	@RequestMapping(value="/{type}",method=RequestMethod.GET)
	@ResponseBody
	public String setData(@RequestParam String token,
			@PathVariable String type,
			@RequestParam Double data1,
			@RequestParam(required=false) Double data2) {
		ReturnBean<String> returnBean = new ReturnBean<>();
		//数据校验
		if(token == null || token.equals("")) {
			returnBean.fail("token is not correct");
			return JSON.toJSONString(returnBean);
		}
		if(type == null || (!type.equals("00") && !type.equals("01") && !type.equals("10") && !type.equals("11"))) {
			returnBean.fail("type is not correct");
			return JSON.toJSONString(returnBean);
		}
		if(data1 == null) {
			returnBean.fail("data is not correct");
			return JSON.toJSONString(returnBean);
		}
		//鉴权
		if(deviceService.getOneDeviceByToken(token) == null) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		//业务流程
		Device device = deviceService.getOneDeviceByToken(token);
		switch(type) {
		case "00":{
			//温湿度
			if(data1 != null && data2 != null){
				Temperatrue temperature = new Temperatrue();
				Humidity humidity = new Humidity();
				temperature.setDeviceid(device.getId());
				temperature.setTemperature(data1);
				humidity.setDeviceid(device.getId());
				humidity.setHumidity(data2);
				
				deviceDataService.setDatas(temperature, humidity);
			}
		}
		break;
		case "01":{
			//光照
			Illumination illumination = new Illumination();
			illumination.setDeviceid(device.getId());
			illumination.setIllumination((int)data1.doubleValue());
			deviceDataService.setData(illumination);
		}
		break;
		case "10":{
			//气体
			Gas gas = new Gas();
			gas.setDeviceid(device.getId());
			gas.setGas(data1);
			deviceDataService.setData(gas);
		}
		break;
		case "11":{
			//ph值
			Ph ph = new Ph();
			ph.setDeviceid(device.getId());
			ph.setPh(data1);
			deviceDataService.setData(ph);
		}
		break;
		}
		
		returnBean.success("success");
		return JSON.toJSONString(returnBean);
	}
	
}
