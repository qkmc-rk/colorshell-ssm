/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.service;

import xyz.ruankun.model.User;

public interface AdminService {

	/**
	 * 管理员登录
	 * @param mail
	 * @param password
	 * @return 管理员的信息
	 */
	User login(String mail,String password);
}
