/**
* @author sherivey.Ruan  
* @date 2018��5��4��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
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
 * ���ܣ��ɼ�Ӳ��������
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
	 *  �豸�����������ݿ��Դ洢�����ݿ�
	 * @param token  �豸����Կ��Ϣ
	 * @param type  ��������
	 * @param data1  ����1
	 * @param data2  ����2
	 * @return �Ƿ��������ݳɹ�
	 */
	@RequestMapping(value="/{type}",method=RequestMethod.GET)
	@ResponseBody
	public String setData(@RequestParam String token,
			@PathVariable String type,
			@RequestParam Double data1,
			@RequestParam(required=false) Double data2) {
		ReturnBean<String> returnBean = new ReturnBean<>();
		//����У��
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
		//��Ȩ
		if(deviceService.getOneDeviceByToken(token) == null) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		//ҵ������
		Device device = deviceService.getOneDeviceByToken(token);
		switch(type) {
		case "00":{
			//��ʪ��
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
			//����
			Illumination illumination = new Illumination();
			illumination.setDeviceid(device.getId());
			illumination.setIllumination((int)data1.doubleValue());
			deviceDataService.setData(illumination);
		}
		break;
		case "10":{
			//����
			Gas gas = new Gas();
			gas.setDeviceid(device.getId());
			gas.setGas(data1);
			deviceDataService.setData(gas);
		}
		break;
		case "11":{
			//phֵ
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
