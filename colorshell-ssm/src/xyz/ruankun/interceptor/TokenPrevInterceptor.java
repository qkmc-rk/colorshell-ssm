/**
* @author sherivey.Ruan  
* @date 2018年4月26日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
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
	 * 完成处理后的动作
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	/**
	 * 处理完成后返回时ModelAndView给spring mvc时
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mdv)
			throws Exception {
	}

	/**
	 * 在调用对应controller处理请求之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		//在此判断token是否合法，是否是管理员身份信息等。
		String token = request.getParameter("token");
		if(token != null) {
			User user = userService.getUserInfoByToken(token);
			if(user != null && user.getRole().equals("admin")) {
				//管理员
				request.setAttribute("code", 1);
			}else if(user != null && user.getRole().equals("user")) {
				//普通用户
				request.setAttribute("code", 0);
			}else {
				//没有查找到用户
				request.setAttribute("code", -1);
			}
		}
		return true;
	}
}
