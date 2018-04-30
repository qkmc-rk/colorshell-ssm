/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.ruankun.dao.UserMapper;
import xyz.ruankun.model.User;
import xyz.ruankun.service.AdminService;
import xyz.ruankun.util.MD5Encoder;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public User login(String mail, String password) {
		User user = null;
		
		try {
			user = userMapper.selectByMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//密码是经过MD5加密的！
		if(user != null && user.getPassword().equals(MD5Encoder.encode(password)) && user.getRole().equals("admin")) {
			//是管理员
			return user;
		}
		return null;
	}

}
