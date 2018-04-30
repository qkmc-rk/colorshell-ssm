/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import xyz.ruankun.consts.RoleConsts;
import xyz.ruankun.custombean.ReturnBean;
import xyz.ruankun.model.Gas;
import xyz.ruankun.model.Humidity;
import xyz.ruankun.model.Illumination;
import xyz.ruankun.model.Ph;
import xyz.ruankun.model.Temperatrue;
import xyz.ruankun.service.DataService;
import xyz.ruankun.service.UserService;

/**
 * 用于描述光温水气肥数据请求地址的controller
 * @author Sherivey.Ruan
 *
 */
@Controller
@RequestMapping(value="/admin/data")
public class AdminDataController {

	@Autowired
	UserService userService;
	
	@Autowired
	DataService dataService;
	
	/**
	 * 获取温度，一个，多个，全部
	 * @param token
	 * @param from
	 * @param to
	 * @param now
	 * @return
	 */
	@RequestMapping(value="/temperature",method=RequestMethod.GET)
	@ResponseBody
	public String temperature(@RequestParam String token,@RequestParam(required=false) Integer from,
			@RequestParam(required=false) Integer to,
			@RequestParam(required=false) String now) {
		//鉴权
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		//判断from to now  优先级：now > from to
		if(now != null) {
			//当前温度
			Temperatrue temperatrue = dataService.getCurrentTemp();
			ReturnBean<Temperatrue> returnBean2 = new ReturnBean<>();
			returnBean2.success(temperatrue);
			return JSON.toJSONString(returnBean2);
		}else if(from != null && from.intValue() >= 0 && to != null && to.intValue() > 0) {
			//温度区间
			List<Temperatrue> temps = dataService.getSomeTempRecords(from,to);
			ReturnBean<List<Temperatrue>> returnBean2 = new ReturnBean<>();
			returnBean2.success(temps);
			return JSON.toJSONString(returnBean2);
		}else {
			//所有温度数据
			List<Temperatrue> temps = dataService.getSomeTempRecords(null,null);
			ReturnBean<List<Temperatrue>> returnBean2 = new ReturnBean<>();
			returnBean2.success(temps);
			return JSON.toJSONString(returnBean2);
		}
	}
	
	/**
	 * 获取humidity，一个，多个，全部
	 * @param token
	 * @param from
	 * @param to
	 * @param now
	 * @return
	 */
	@RequestMapping(value="/humidity",method=RequestMethod.GET)
	@ResponseBody
	public String humidity(@RequestParam String token,@RequestParam(required=false) Integer from,
			@RequestParam(required=false) Integer to,
			@RequestParam(required=false) String now) {
		//鉴权
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		//判断from to now  优先级：now > from to
		if(now != null) {
			//当前湿度
			Humidity humidity = dataService.getCurrentHumidity();
			ReturnBean<Humidity> returnBean2 = new ReturnBean<>();
			returnBean2.success(humidity);
			return JSON.toJSONString(returnBean2);
		}else if(from != null && from.intValue() >= 0 && to != null && to.intValue() > 0) {
			//湿度区间
			List<Humidity> humidities = dataService.getSomeHumidityRecords(from,to);
			ReturnBean<List<Humidity>> returnBean2 = new ReturnBean<>();
			returnBean2.success(humidities);
			return JSON.toJSONString(returnBean2);
		}else {
			//所有湿度数据
			List<Humidity> humidities = dataService.getSomeHumidityRecords(null,null);
			ReturnBean<List<Humidity>> returnBean2 = new ReturnBean<>();
			returnBean2.success(humidities);
			return JSON.toJSONString(returnBean2);
		}
	}
	
	/**
	 * 获取illumination，一个，多个，全部
	 * @param token
	 * @param from
	 * @param to
	 * @param now
	 * @return
	 */
	@RequestMapping(value="/illumination",method=RequestMethod.GET)
	@ResponseBody
	public String illumination(@RequestParam String token,@RequestParam(required=false) Integer from,
			@RequestParam(required=false) Integer to,
			@RequestParam(required=false) String now) {
		//鉴权
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		//判断from to now  优先级：now > from to
		if(now != null) {
			//当前光照数据
			Illumination illumination = dataService.getCurrentIllumination();
			ReturnBean<Illumination> returnBean2 = new ReturnBean<>();
			returnBean2.success(illumination);
			return JSON.toJSONString(returnBean2);
		}else if(from != null && from.intValue() >= 0 && to != null && to.intValue() > 0) {
			//湿度区间
			List<Illumination> illuminations = dataService.getSomeIlluminationRecords(from,to);
			ReturnBean<List<Illumination>> returnBean2 = new ReturnBean<>();
			returnBean2.success(illuminations);
			return JSON.toJSONString(returnBean2);
		}else {
			//所有湿度数据
			List<Illumination> illuminations = dataService.getSomeIlluminationRecords(null,null);
			ReturnBean<List<Illumination>> returnBean2 = new ReturnBean<>();
			returnBean2.success(illuminations);
			return JSON.toJSONString(returnBean2);
		}
	}
	
	
	/**
	 * 获取gas，一个，多个，全部
	 * @param token
	 * @param from
	 * @param to
	 * @param now
	 * @return
	 */
	@RequestMapping(value="/gas",method=RequestMethod.GET)
	@ResponseBody
	public String gas(@RequestParam String token,@RequestParam(required=false) Integer from,
			@RequestParam(required=false) Integer to,
			@RequestParam(required=false) String now) {
		//鉴权
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		//判断from to now  优先级：now > from to
		if(now != null) {
			//当前光照数据
			Gas gas = dataService.getCurrentGas();
			ReturnBean<Gas> returnBean2 = new ReturnBean<>();
			returnBean2.success(gas);
			return JSON.toJSONString(returnBean2);
		}else if(from != null && from.intValue() >= 0 && to != null && to.intValue() > 0) {
			//湿度区间
			List<Gas> gases = dataService.getSomeGasRecords(from,to);
			ReturnBean<List<Gas>> returnBean2 = new ReturnBean<>();
			returnBean2.success(gases);
			return JSON.toJSONString(returnBean2);
		}else {
			//所有湿度数据
			List<Gas> gases = dataService.getSomeGasRecords(null,null);
			ReturnBean<List<Gas>> returnBean2 = new ReturnBean<>();
			returnBean2.success(gases);
			return JSON.toJSONString(returnBean2);
		}
	}
	
	/**
	 * 获取ph，一个，多个，全部
	 * @param token
	 * @param from
	 * @param to
	 * @param now
	 * @return
	 */
	@RequestMapping(value="/ph",method=RequestMethod.GET)
	@ResponseBody
	public String ph(@RequestParam String token,@RequestParam(required=false) Integer from,
			@RequestParam(required=false) Integer to,
			@RequestParam(required=false) String now) {
		//鉴权
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		//判断from to now  优先级：now > from to
		if(now != null) {
			//当前光照数据
			Ph ph = dataService.getCurrentPh();
			ReturnBean<Ph> returnBean2 = new ReturnBean<>();
			returnBean2.success(ph);
			return JSON.toJSONString(returnBean2);
		}else if(from != null && from.intValue() >= 0 && to != null && to.intValue() > 0) {
			//湿度区间
			List<Ph> phes = dataService.getSomePhRecords(from,to);
			ReturnBean<List<Ph>> returnBean2 = new ReturnBean<>();
			returnBean2.success(phes);
			return JSON.toJSONString(returnBean2);
		}else {
			//所有湿度数据
			List<Ph> phes = dataService.getSomePhRecords(null,null);
			ReturnBean<List<Ph>> returnBean2 = new ReturnBean<>();
			returnBean2.success(phes);
			return JSON.toJSONString(returnBean2);
		}
	}
}
