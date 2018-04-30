/**
* @author sherivey.Ruan  
* @date 2018年4月23日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailAssistant {
	
	private MailSender mailSender;
	private SimpleMailMessage templateMailMessage;//配置的模板,用此模板new一个需要使用的具体message对象,发送给用户邮箱
	
	public void notify(String usermail,String username,String content) {
		//为了线程安全着想,从模板中copy一个SimpleMailMessage 对象
		SimpleMailMessage msg 
			= new SimpleMailMessage(this.templateMailMessage);
		msg.setTo(usermail);
		msg.setText(content);
		//发送邮件
		try {
			this.mailSender.send(msg);
		} catch (MailException e) {
			System.out.println("[EXCEPTION]:发送邮件失败,发送到" + usermail);
			e.printStackTrace();
		}
	}
	
	public void notifyWithHtml(String usermail,String username,String content) {
		JavaMailSenderImpl sender = (JavaMailSenderImpl) mailSender;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper  helper;
		try {
			helper = new MimeMessageHelper(message, true);
			//设置内容
			helper.setSubject("通知");
			helper.setTo(usermail);
			helper.setFrom("1537854187@qq.com");
			helper.setText(content, true);
			sender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public MailSender getMailSender() {
		return mailSender;
	}
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	public SimpleMailMessage getTemplateMailMessage() {
		return templateMailMessage;
	}
	public void setTemplateMailMessage(SimpleMailMessage templateMailMessage) {
		this.templateMailMessage = templateMailMessage;
	}
	
	
	
}
