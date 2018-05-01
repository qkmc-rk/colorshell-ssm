/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/
package xyz.ruankun.service;

import java.util.List;

import xyz.ruankun.model.User;

public interface UserService {

	/**
	 * 注册一个用户
	 * @param mail
	 * @param password
	 * @param neckname
	 * @return
	 */
	Integer regist(String mail, String password, String neckname);

	/**
	 * 删除用户
	 * @param id
	 */
	Integer deleteUserById(Integer id);
	
	/**
	 * 获取用户的基本信息
	 * @return 用户信息
	 */
	User getUserInfoByToken(String token);

	/**
	 * 用户登录
	 * @param mail
	 * @param password
	 * @return
	 */
	User login(String mail, String password);
	
	/**
	 * 假装一个鉴权服务
	 * @param token
	 * @return 0 普通用户 1管理员 -1 非法用户
	 */
	Integer getRole(String token);

	List<User> getAll(Integer start, Integer limit);

	/**
	 * 更新用户的信息
	 * @param id
	 * @param neckname
	 * @param role
	 * @param password
	 * @return
	 */
	Integer updateUser(Integer id, String neckname, String role, String password);
}
