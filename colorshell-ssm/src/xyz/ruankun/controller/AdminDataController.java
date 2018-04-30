/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
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
 * ������������ˮ�������������ַ��controller
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
	 * ��ȡ�¶ȣ�һ���������ȫ��
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
		//��Ȩ
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		//�ж�from to now  ���ȼ���now > from to
		if(now != null) {
			//��ǰ�¶�
			Temperatrue temperatrue = dataService.getCurrentTemp();
			ReturnBean<Temperatrue> returnBean2 = new ReturnBean<>();
			returnBean2.success(temperatrue);
			return JSON.toJSONString(returnBean2);
		}else if(from != null && from.intValue() >= 0 && to != null && to.intValue() > 0) {
			//�¶�����
			List<Temperatrue> temps = dataService.getSomeTempRecords(from,to);
			ReturnBean<List<Temperatrue>> returnBean2 = new ReturnBean<>();
			returnBean2.success(temps);
			return JSON.toJSONString(returnBean2);
		}else {
			//�����¶�����
			List<Temperatrue> temps = dataService.getSomeTempRecords(null,null);
			ReturnBean<List<Temperatrue>> returnBean2 = new ReturnBean<>();
			returnBean2.success(temps);
			return JSON.toJSONString(returnBean2);
		}
	}
	
	/**
	 * ��ȡhumidity��һ���������ȫ��
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
		//��Ȩ
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		//�ж�from to now  ���ȼ���now > from to
		if(now != null) {
			//��ǰʪ��
			Humidity humidity = dataService.getCurrentHumidity();
			ReturnBean<Humidity> returnBean2 = new ReturnBean<>();
			returnBean2.success(humidity);
			return JSON.toJSONString(returnBean2);
		}else if(from != null && from.intValue() >= 0 && to != null && to.intValue() > 0) {
			//ʪ������
			List<Humidity> humidities = dataService.getSomeHumidityRecords(from,to);
			ReturnBean<List<Humidity>> returnBean2 = new ReturnBean<>();
			returnBean2.success(humidities);
			return JSON.toJSONString(returnBean2);
		}else {
			//����ʪ������
			List<Humidity> humidities = dataService.getSomeHumidityRecords(null,null);
			ReturnBean<List<Humidity>> returnBean2 = new ReturnBean<>();
			returnBean2.success(humidities);
			return JSON.toJSONString(returnBean2);
		}
	}
	
	/**
	 * ��ȡillumination��һ���������ȫ��
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
		//��Ȩ
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		//�ж�from to now  ���ȼ���now > from to
		if(now != null) {
			//��ǰ��������
			Illumination illumination = dataService.getCurrentIllumination();
			ReturnBean<Illumination> returnBean2 = new ReturnBean<>();
			returnBean2.success(illumination);
			return JSON.toJSONString(returnBean2);
		}else if(from != null && from.intValue() >= 0 && to != null && to.intValue() > 0) {
			//ʪ������
			List<Illumination> illuminations = dataService.getSomeIlluminationRecords(from,to);
			ReturnBean<List<Illumination>> returnBean2 = new ReturnBean<>();
			returnBean2.success(illuminations);
			return JSON.toJSONString(returnBean2);
		}else {
			//����ʪ������
			List<Illumination> illuminations = dataService.getSomeIlluminationRecords(null,null);
			ReturnBean<List<Illumination>> returnBean2 = new ReturnBean<>();
			returnBean2.success(illuminations);
			return JSON.toJSONString(returnBean2);
		}
	}
	
	
	/**
	 * ��ȡgas��һ���������ȫ��
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
		//��Ȩ
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		//�ж�from to now  ���ȼ���now > from to
		if(now != null) {
			//��ǰ��������
			Gas gas = dataService.getCurrentGas();
			ReturnBean<Gas> returnBean2 = new ReturnBean<>();
			returnBean2.success(gas);
			return JSON.toJSONString(returnBean2);
		}else if(from != null && from.intValue() >= 0 && to != null && to.intValue() > 0) {
			//ʪ������
			List<Gas> gases = dataService.getSomeGasRecords(from,to);
			ReturnBean<List<Gas>> returnBean2 = new ReturnBean<>();
			returnBean2.success(gases);
			return JSON.toJSONString(returnBean2);
		}else {
			//����ʪ������
			List<Gas> gases = dataService.getSomeGasRecords(null,null);
			ReturnBean<List<Gas>> returnBean2 = new ReturnBean<>();
			returnBean2.success(gases);
			return JSON.toJSONString(returnBean2);
		}
	}
	
	/**
	 * ��ȡph��һ���������ȫ��
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
		//��Ȩ
		ReturnBean<String> returnBean = new ReturnBean<>();
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		//�ж�from to now  ���ȼ���now > from to
		if(now != null) {
			//��ǰ��������
			Ph ph = dataService.getCurrentPh();
			ReturnBean<Ph> returnBean2 = new ReturnBean<>();
			returnBean2.success(ph);
			return JSON.toJSONString(returnBean2);
		}else if(from != null && from.intValue() >= 0 && to != null && to.intValue() > 0) {
			//ʪ������
			List<Ph> phes = dataService.getSomePhRecords(from,to);
			ReturnBean<List<Ph>> returnBean2 = new ReturnBean<>();
			returnBean2.success(phes);
			return JSON.toJSONString(returnBean2);
		}else {
			//����ʪ������
			List<Ph> phes = dataService.getSomePhRecords(null,null);
			ReturnBean<List<Ph>> returnBean2 = new ReturnBean<>();
			returnBean2.success(phes);
			return JSON.toJSONString(returnBean2);
		}
	}
}
