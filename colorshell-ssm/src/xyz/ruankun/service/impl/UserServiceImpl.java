/**
* @author sherivey.Ruan  
* @date 2018年4月23日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.ruankun.consts.RoleConsts;
import xyz.ruankun.dao.UserMapper;
import xyz.ruankun.model.User;
import xyz.ruankun.service.UserService;
import xyz.ruankun.util.MD5Encoder;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public Integer regist(String mail, String password, String neckname) {
		User record = new User();
		record.setMail(mail);
		record.setNeckname(neckname);
		record.setRole("user");
		//对密码进行加密操作 md5加密 128 bit
		String encodedPassword = MD5Encoder.encode(password);
		
		//获取时间戳
		Date now = new Date();
		Long nowTime = now.getTime();
		password += nowTime;
		String token = MD5Encoder.encode(password);
		
		record.setPassword(encodedPassword);
		record.setToken(token);
		
		Integer rs = null;
		try {
			rs = userMapper.insertSelective(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Integer deleteUserById(Integer id) {
		Integer rs = null;
		try {
			rs = userMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public User getUserInfoByToken(String token) {
		User user = null;
		try {
			user = userMapper.selectByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User login(String mail, String password) {
		User user = null;
		String encodePassword = MD5Encoder.encode(password);
		try {
			user = userMapper.selectByMail(mail);
			if(!(user.getPassword()).equals(encodePassword)) {
				user = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Integer getRole(String token) {
		User user = null;
		try {
			user = userMapper.selectByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(user != null && (user.getRole()).equals("admin")) {
			return RoleConsts.ROLE_ADMIN;
		}else if(user != null && (user.getRole()).equals("user")) {
			return RoleConsts.ROLE_USER;
		}else {
			return RoleConsts.ROLE_ERROR;
		}
	}

	@Override
	public List<User> getAll(Integer start, Integer limit) {
		if(start == null) {
			start = 0;
		}
		if(limit == null) {
			limit = Integer.MAX_VALUE;
		}
		List<User> users = null;
		try {
			users = userMapper.selectSome(start,limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
