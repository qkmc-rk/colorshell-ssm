/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
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
		//�����Ǿ���MD5���ܵģ�
		if(user != null && user.getPassword().equals(MD5Encoder.encode(password)) && user.getRole().equals("admin")) {
			//�ǹ���Ա
			return user;
		}
		return null;
	}

}
