/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
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
	 * 设备注册
	 * @param token 管理员秘钥
	 * @param mac 设备的物理地址号
	 * @param type 设备采集的类型
	 * @return 是否注册设备成功
	 */
	@RequestMapping(value="/reg",method=RequestMethod.PUT)
	@ResponseBody
	public String regDevice(@RequestParam String token,
			@RequestParam  String mac,
			@RequestParam  String type) {
		//鉴权
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		//正常服务
		//1.数据校验
		if(mac == null || mac.equals("")) {
			returnBean.fail("mac地址错误");
			return JSON.toJSONString(returnBean);
		}
		if(type == null || type.equals("") || !type.equals("temperature") || !type.equals("ph")
				|| !type.equals("illumination") || !type.equals("humidity")
				|| !type.equals("gas")){
			returnBean.fail("设备类型错误");
			return JSON.toJSONString(returnBean);
		}
		//2.业务状态
		Device device = new Device();
		device.setMac(mac);
		device.setType(type);
		String deviceToken = MD5Encoder.encode(String.valueOf((new Date().getTime())));
		device.setToken(deviceToken);
		
		Integer rs = deviceService.regist(device);
		switch (rs) {
		case 1:
			returnBean.success("设备注册成功");
			break;
		case 0:
			returnBean.fail("设备注册失败");
			break;
		case -1:
			returnBean.fail("设备已经存在");
			break;
		}
		return JSON.toJSONString(returnBean);
	}
	
	/**
	 * 删除一个设备
	 * @param token 管理员的token
	 * @param deviceId   设备的id号码
	 * @return 是否删除成功
	 */
	@ResponseBody
	@RequestMapping(value="/{deviceId}",method=RequestMethod.DELETE)
	
	public String deleteDevice(@RequestParam String token, @PathVariable Integer deviceId) {
		//鉴权
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		Integer rs = deviceService.deleteOneDevice(deviceId);
		if(rs > 0) {
			//删除成功
			returnBean.success("删除设备成功");
			return JSON.toJSONString(returnBean);
		}else {
			//删除失败
			returnBean.fail("删除设备失败");
			return JSON.toJSONString(returnBean);
		}
	} 
	
	@RequestMapping(value="/{deviceId}",method=RequestMethod.POST)
	public String updateDevice(@PathVariable Integer deviceId,@RequestParam String token,
			@RequestParam String mac,@RequestParam String type) {
		//鉴权
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		
		Integer rs = deviceService.updateDevice(deviceId,mac,type);
		if(rs > 0) {
			//删除成功
			returnBean.success("更新设备成功");
			return JSON.toJSONString(returnBean);
		}else {
			//删除失败
			returnBean.fail("更新设备失败");
			return JSON.toJSONString(returnBean);
		}
	}
	
	/**
	 * 查询所有设备信息
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String getAll(@RequestParam String token) {
		//鉴权
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		List<Device> devices = deviceService.getAll();
		
		ReturnBean<List<Device>> returnBean2 = new ReturnBean<>();
		returnBean2.success(devices);
		return JSON.toJSONString(returnBean2);
	}
}
