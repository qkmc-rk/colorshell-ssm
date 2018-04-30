/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import xyz.ruankun.custombean.ReturnBean;
import xyz.ruankun.model.User;
import xyz.ruankun.service.AdminService;

/**
 * ��������Ա��¼����ػ��controller
 * @author Sherivey.Ruan
 *
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	/**
	 * ��¼����
	 * @param mail �û�����
	 * @param password  �û�����
	 * @return  token��Ϣ
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(String mail,String password) {
		User user = adminService.login(mail, password);
		if(user != null) {
			//��¼�ɹ���
			ReturnBean<String> returnBean = new ReturnBean<>();
			returnBean.success(user.getToken());
			return JSON.toJSONString(returnBean);
		}else {
			ReturnBean<String> returnBean = new ReturnBean<>();
			returnBean.fail("��¼ʧ�ܣ�");
			return JSON.toJSONString(returnBean);
		}
	}
	
}
