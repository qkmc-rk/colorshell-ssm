/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import xyz.ruankun.custombean.NowDataBean;
import xyz.ruankun.custombean.PrevBean;
import xyz.ruankun.custombean.ReturnBean;
import xyz.ruankun.model.User;
import xyz.ruankun.service.DataService;
import xyz.ruankun.service.UserService;

@Controller
@RequestMapping(value="/terminal")
public class TerminalController {

	@Autowired
	DataService dataService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 返回当前时间的（最近期内）的光温水气肥数据
	 * @param token 令牌信息
	 * @param request 取得拦截器里面的数据时使用
	 * @return
	 */
	@RequestMapping(value="/now",method=RequestMethod.GET)
	@ResponseBody
	public String now(@RequestParam String token,HttpServletRequest request) {
		Integer code = (Integer) request.getAttribute("code");//1 为管理员  0 为普通用户 -1为不存在的用户
		if(code != null && code.intValue() == 1) {
			//admin
			return JSON.toJSONString(PrevBean.getPrevBeanRoleWrong("角色错误"));
		}else if(code != null && code.intValue() == 0) {
			//user
			NowDataBean nowData = dataService.getCurrentData();
			if(nowData != null) {
				//返回数据
				ReturnBean<NowDataBean> returnBean = new ReturnBean<>();
				returnBean.success(nowData);
				return JSON.toJSONString(returnBean);
			}else {
				//返回错误信息
				ReturnBean<String> returnBean = new ReturnBean<>();
				returnBean.success("没有查找到当前时间数据");
				return JSON.toJSONString(returnBean);
			}
		}else if(code != null && code.intValue() == -1){
			//no user
			return JSON.toJSONString(PrevBean.getPrevBeanAuthWrong("认证失败"));
		}else {
			//wrong
			return JSON.toJSONString(PrevBean.getPrevBeanAuthWrong("未知错误"));
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam String mail,
			@RequestParam String password) {
		User user = userService.login(mail,password);
		if(user == null) {
			//登录失败
			ReturnBean<String> returnBean = new ReturnBean<>();
			returnBean.success("无论如何，登录失败了");
			return JSON.toJSONString(returnBean);
		}else {
			//登录成功
			ReturnBean<String> returnBean = new ReturnBean<>();
			returnBean.success(user.getToken());
			return JSON.toJSONString(returnBean);
		}
	}
}
