/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.controller;

import java.util.ArrayList;
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
import xyz.ruankun.dao.UserMapper;
import xyz.ruankun.model.User;
import xyz.ruankun.service.UserService;

@Controller
@RequestMapping(value = "/admin/user")
public class AdminUserController {

	@Autowired
	UserService userService;
	
	/**
	 * 有些获取数据的过程为了简化，直接导入mapper
	 */
	@Autowired
	UserMapper userMpper;
	
	/**
	 * 注册一个终端用户
	 * @param mail
	 * @param password
	 * @param neckname
	 * @return
	 */
	@RequestMapping(value="/reg",method=RequestMethod.POST)//PUT
	@ResponseBody
	public String regist(@RequestParam String mail,
			 @RequestParam  String password,
			 @RequestParam(required = false)  String neckname,
			 @RequestParam String token) {
		ReturnBean<String> returnBean = new ReturnBean<>();
		//鉴权
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		//数据校验
		if(mail == null || mail.equals("")) {
			//mail为空
			returnBean.fail("mail is null");
			return JSON.toJSONString(returnBean);
		}else if(password == null || password.equals("")) {
			//password为空
			returnBean.fail("password is null");
			return JSON.toJSONString(returnBean);
		}else {
			//可以注册了
			Integer rs = userService.regist(mail, password, neckname);
			if(rs != null && rs > 0) {
				returnBean.success("注册成功！");
				return JSON.toJSONString(returnBean);
			}else {
				returnBean.fail("注册失败");
				return JSON.toJSONString(returnBean);
			}
		}
	}
	
	/**
	 * 删除一个终端用户
	 * @param id
	 * @param token 管理员的令牌信息
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String deleteUser(@PathVariable Integer id,@RequestParam String token) {
		ReturnBean<String> returnBean = new ReturnBean<>();
		//鉴权
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		Integer rs = userService.deleteUserById(id);
		if(rs != null && rs > 0){
			//删除成功
			returnBean.success("删除用户成功");
			return JSON.toJSONString(returnBean);
		}else {
			returnBean.fail("删除用户失败！");
			return JSON.toJSONString(returnBean);
		}
	}
	
	/**
	 * 修改一个终端用户的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@ResponseBody
	public String updateUser(@PathVariable Integer id, @RequestParam String token,
			@RequestParam(required=false) String neckname, 
			@RequestParam(required=false) String role,
			@RequestParam(required=false) String password) {
		ReturnBean<String> returnBean = new ReturnBean<>();
		//鉴权
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		Integer rs = userService.updateUser(id,neckname,role,password);
		if(rs != null && rs > 0){
			//删除成功
			returnBean.success("更新用户成功");
			return JSON.toJSONString(returnBean);
		}else {
			returnBean.fail("更新用户失败！");
			return JSON.toJSONString(returnBean);
		}
	}
	
	/**
	 * 获得所有用户的信息，除了自己的信息
	 * @param token
	 * @param start
	 * @param limit
	 * @return 用户列表
	 */
	@ResponseBody
	@RequestMapping("/all")
	public String getUsers(@RequestParam String token,
			@RequestParam(required=false) Integer start,
			@RequestParam(required=false) Integer limit) {
		ReturnBean<String> returnBean = new ReturnBean<>();
		ReturnBean<List<User>> returnBean2 = new ReturnBean<>();
		//鉴权
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("鉴权失败");
			return JSON.toJSONString(returnBean);
		}
		//服务
		List<User> users = userService.getAll(start,limit);
		List<User> usersExceptSelf = new ArrayList<>();
		for (User user : users) {
			if(!(user.getToken()).equals(token)) {
				usersExceptSelf.add(user);
			}
		}
		returnBean2.success(usersExceptSelf);
		return JSON.toJSONString(returnBean2);
	}
	
}
