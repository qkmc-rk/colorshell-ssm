/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/
package xyz.ruankun.service;

import java.util.List;

import xyz.ruankun.model.User;

public interface UserService {

	/**
	 * ע��һ���û�
	 * @param mail
	 * @param password
	 * @param neckname
	 * @return
	 */
	Integer regist(String mail, String password, String neckname);

	/**
	 * ɾ���û�
	 * @param id
	 */
	Integer deleteUserById(Integer id);
	
	/**
	 * ��ȡ�û��Ļ�����Ϣ
	 * @return �û���Ϣ
	 */
	User getUserInfoByToken(String token);

	/**
	 * �û���¼
	 * @param mail
	 * @param password
	 * @return
	 */
	User login(String mail, String password);
	
	/**
	 * ��װһ����Ȩ����
	 * @param token
	 * @return 0 ��ͨ�û� 1����Ա -1 �Ƿ��û�
	 */
	Integer getRole(String token);

	List<User> getAll(Integer start, Integer limit);

	/**
	 * �����û�����Ϣ
	 * @param id
	 * @param neckname
	 * @param role
	 * @param password
	 * @return
	 */
	Integer updateUser(Integer id, String neckname, String role, String password);
}
