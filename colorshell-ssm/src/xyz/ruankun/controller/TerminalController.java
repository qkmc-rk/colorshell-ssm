/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import xyz.ruankun.custombean.NowDataBean;
import xyz.ruankun.custombean.PrevBean;
import xyz.ruankun.custombean.ReturnBean;
import xyz.ruankun.model.User;
import xyz.ruankun.service.DataService;
import xyz.ruankun.service.UserService;

@Controller
@RequestMapping(value="/terminal")
public class TerminalController {

	@Autowired
	DataService dataService;
	
	@Autowired
	UserService userService;
	
	/**
	 * ���ص�ǰʱ��ģ�������ڣ��Ĺ���ˮ��������
	 * @param token ������Ϣ
	 * @param request ȡ�����������������ʱʹ��
	 * @return
	 */
	@RequestMapping(value="/now",method=RequestMethod.GET)
	@ResponseBody
	public String now(@RequestParam String token,HttpServletRequest request) {
		Integer code = (Integer) request.getAttribute("code");//1 Ϊ����Ա  0 Ϊ��ͨ�û� -1Ϊ�����ڵ��û�
		if(code != null && code.intValue() == 1) {
			//admin
			return JSON.toJSONString(PrevBean.getPrevBeanRoleWrong("��ɫ����"));
		}else if(code != null && code.intValue() == 0) {
			//user
			NowDataBean nowData = dataService.getCurrentData();
			if(nowData != null) {
				//��������
				ReturnBean<NowDataBean> returnBean = new ReturnBean<>();
				returnBean.success(nowData);
				return JSON.toJSONString(returnBean);
			}else {
				//���ش�����Ϣ
				ReturnBean<String> returnBean = new ReturnBean<>();
				returnBean.success("û�в��ҵ���ǰʱ������");
				return JSON.toJSONString(returnBean);
			}
		}else if(code != null && code.intValue() == -1){
			//no user
			return JSON.toJSONString(PrevBean.getPrevBeanAuthWrong("��֤ʧ��"));
		}else {
			//wrong
			return JSON.toJSONString(PrevBean.getPrevBeanAuthWrong("δ֪����"));
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam String mail,
			@RequestParam String password) {
		User user = userService.login(mail,password);
		if(user == null) {
			//��¼ʧ��
			ReturnBean<String> returnBean = new ReturnBean<>();
			returnBean.success("������Σ���¼ʧ����");
			return JSON.toJSONString(returnBean);
		}else {
			//��¼�ɹ�
			ReturnBean<String> returnBean = new ReturnBean<>();
			returnBean.success(user.getToken());
			return JSON.toJSONString(returnBean);
		}
	}
}
