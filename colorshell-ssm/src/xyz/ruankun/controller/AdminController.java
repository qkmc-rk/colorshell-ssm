/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
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
 * 描述管理员登录等相关活动的controller
 * @author Sherivey.Ruan
 *
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	/**
	 * 登录请求
	 * @param mail 用户邮箱
	 * @param password  用户密码
	 * @return  token信息
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(String mail,String password) {
		User user = adminService.login(mail, password);
		if(user != null) {
			//登录成功了
			ReturnBean<String> returnBean = new ReturnBean<>();
			returnBean.success(user.getToken());
			return JSON.toJSONString(returnBean);
		}else {
			ReturnBean<String> returnBean = new ReturnBean<>();
			returnBean.fail("登录失败！");
			return JSON.toJSONString(returnBean);
		}
	}
	
}
