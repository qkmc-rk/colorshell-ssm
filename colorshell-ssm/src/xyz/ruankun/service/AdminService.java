/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.service;

import xyz.ruankun.model.User;

public interface AdminService {

	/**
	 * ����Ա��¼
	 * @param mail
	 * @param password
	 * @return ����Ա����Ϣ
	 */
	User login(String mail,String password);
}
