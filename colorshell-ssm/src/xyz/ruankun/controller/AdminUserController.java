/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
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
	 * ��Щ��ȡ���ݵĹ���Ϊ�˼򻯣�ֱ�ӵ���mapper
	 */
	@Autowired
	UserMapper userMpper;
	
	/**
	 * ע��һ���ն��û�
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
		//��Ȩ
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		//����У��
		if(mail == null || mail.equals("")) {
			//mailΪ��
			returnBean.fail("mail is null");
			return JSON.toJSONString(returnBean);
		}else if(password == null || password.equals("")) {
			//passwordΪ��
			returnBean.fail("password is null");
			return JSON.toJSONString(returnBean);
		}else {
			//����ע����
			Integer rs = userService.regist(mail, password, neckname);
			if(rs != null && rs > 0) {
				returnBean.success("ע��ɹ���");
				return JSON.toJSONString(returnBean);
			}else {
				returnBean.fail("ע��ʧ��");
				return JSON.toJSONString(returnBean);
			}
		}
	}
	
	/**
	 * ɾ��һ���ն��û�
	 * @param id
	 * @param token ����Ա��������Ϣ
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String deleteUser(@PathVariable Integer id,@RequestParam String token) {
		ReturnBean<String> returnBean = new ReturnBean<>();
		//��Ȩ
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		Integer rs = userService.deleteUserById(id);
		if(rs != null && rs > 0){
			//ɾ���ɹ�
			returnBean.success("ɾ���û��ɹ�");
			return JSON.toJSONString(returnBean);
		}else {
			returnBean.fail("ɾ���û�ʧ�ܣ�");
			return JSON.toJSONString(returnBean);
		}
	}
	
	/**
	 * �޸�һ���ն��û�����Ϣ
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
		//��Ȩ
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		Integer rs = userService.updateUser(id,neckname,role,password);
		if(rs != null && rs > 0){
			//ɾ���ɹ�
			returnBean.success("�����û��ɹ�");
			return JSON.toJSONString(returnBean);
		}else {
			returnBean.fail("�����û�ʧ�ܣ�");
			return JSON.toJSONString(returnBean);
		}
	}
	
	/**
	 * ��������û�����Ϣ�������Լ�����Ϣ
	 * @param token
	 * @param start
	 * @param limit
	 * @return �û��б�
	 */
	@ResponseBody
	@RequestMapping("/all")
	public String getUsers(@RequestParam String token,
			@RequestParam(required=false) Integer start,
			@RequestParam(required=false) Integer limit) {
		ReturnBean<String> returnBean = new ReturnBean<>();
		ReturnBean<List<User>> returnBean2 = new ReturnBean<>();
		//��Ȩ
		if(userService.getRole(token) != RoleConsts.ROLE_ADMIN) {
			returnBean.fail("��Ȩʧ��");
			return JSON.toJSONString(returnBean);
		}
		//����
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
