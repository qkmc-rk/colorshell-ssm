/**
* @author sherivey.Ruan  
* @date 2018��4��26��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import xyz.ruankun.model.User;
import xyz.ruankun.service.UserService;

public class TokenPrevInterceptor implements HandlerInterceptor{

	@Autowired
	UserService userService;
	/**
	 * ��ɴ����Ķ���
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	/**
	 * ������ɺ󷵻�ʱModelAndView��spring mvcʱ
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mdv)
			throws Exception {
	}

	/**
	 * �ڵ��ö�Ӧcontroller��������֮ǰ
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		//�ڴ��ж�token�Ƿ�Ϸ����Ƿ��ǹ���Ա�����Ϣ�ȡ�
		String token = request.getParameter("token");
		if(token != null) {
			User user = userService.getUserInfoByToken(token);
			if(user != null && user.getRole().equals("admin")) {
				//����Ա
				request.setAttribute("code", 1);
			}else if(user != null && user.getRole().equals("user")) {
				//��ͨ�û�
				request.setAttribute("code", 0);
			}else {
				//û�в��ҵ��û�
				request.setAttribute("code", -1);
			}
		}
		return true;
	}
}
