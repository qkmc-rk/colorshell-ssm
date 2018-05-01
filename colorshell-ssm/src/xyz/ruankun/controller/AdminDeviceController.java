/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import xyz.ruankun.consts.RoleConsts;
import xyz.ruankun.custombean.ReturnBean;
import xyz.ruankun.model.Device;
import xyz.ruankun.service.DeviceService;
import xyz.ruankun.service.UserService;
import xyz.ruankun.util.MD5Encoder;

@Controller
@RequestMapping(value="/admin/device")
public class AdminDeviceController {

	@Autowired
	UserService userService;
	
	@Autowired
	DeviceService deviceService;
	
	
	/**
	 * �豸ע��
	 * @param token ����Ա��Կ
	 * @param mac �豸�������ַ��
	 * @param type �豸�ɼ�������
	 * @return �Ƿ�ע���豸�ɹ�
	 */
	@RequestMapping(value="/reg",method=RequestMethod.PUT)
	@ResponseBody
	public String regDevice(@RequestParam String token,
			@RequestParam  String mac,
			@RequestParam  String type) {
		//��Ȩ
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		//��������
		//1.����У��
		if(mac == null || mac.equals("")) {
			returnBean.fail("mac��ַ����");
			return JSON.toJSONString(returnBean);
		}
		if(type == null || type.equals("") || !type.equals("temperature") || !type.equals("ph")
				|| !type.equals("illumination") || !type.equals("humidity")
				|| !type.equals("gas")){
			returnBean.fail("�豸���ʹ���");
			return JSON.toJSONString(returnBean);
		}
		//2.ҵ��״̬
		Device device = new Device();
		device.setMac(mac);
		device.setType(type);
		String deviceToken = MD5Encoder.encode(String.valueOf((new Date().getTime())));
		device.setToken(deviceToken);
		
		Integer rs = deviceService.regist(device);
		switch (rs) {
		case 1:
			returnBean.success("�豸ע��ɹ�");
			break;
		case 0:
			returnBean.fail("�豸ע��ʧ��");
			break;
		case -1:
			returnBean.fail("�豸�Ѿ�����");
			break;
		}
		return JSON.toJSONString(returnBean);
	}
	
	/**
	 * ɾ��һ���豸
	 * @param token ����Ա��token
	 * @param deviceId   �豸��id����
	 * @return �Ƿ�ɾ���ɹ�
	 */
	@ResponseBody
	@RequestMapping(value="/{deviceId}",method=RequestMethod.DELETE)
	
	public String deleteDevice(@RequestParam String token, @PathVariable Integer deviceId) {
		//��Ȩ
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		Integer rs = deviceService.deleteOneDevice(deviceId);
		if(rs > 0) {
			//ɾ���ɹ�
			returnBean.success("ɾ���豸�ɹ�");
			return JSON.toJSONString(returnBean);
		}else {
			//ɾ��ʧ��
			returnBean.fail("ɾ���豸ʧ��");
			return JSON.toJSONString(returnBean);
		}
	} 
	
	@RequestMapping(value="/{deviceId}",method=RequestMethod.POST)
	public String updateDevice(@PathVariable Integer deviceId,@RequestParam String token,
			@RequestParam String mac,@RequestParam String type) {
		//��Ȩ
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		
		Integer rs = deviceService.updateDevice(deviceId,mac,type);
		if(rs > 0) {
			//ɾ���ɹ�
			returnBean.success("�����豸�ɹ�");
			return JSON.toJSONString(returnBean);
		}else {
			//ɾ��ʧ��
			returnBean.fail("�����豸ʧ��");
			return JSON.toJSONString(returnBean);
		}
	}
	
	/**
	 * ��ѯ�����豸��Ϣ
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String getAll(@RequestParam String token) {
		//��Ȩ
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		List<Device> devices = deviceService.getAll();
		
		ReturnBean<List<Device>> returnBean2 = new ReturnBean<>();
		returnBean2.success(devices);
		return JSON.toJSONString(returnBean2);
	}
}
