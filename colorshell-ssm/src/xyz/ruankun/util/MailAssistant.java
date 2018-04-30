/**
* @author sherivey.Ruan  
* @date 2018��4��23��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
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
	private SimpleMailMessage templateMailMessage;//���õ�ģ��,�ô�ģ��newһ����Ҫʹ�õľ���message����,���͸��û�����
	
	public void notify(String usermail,String username,String content) {
		//Ϊ���̰߳�ȫ����,��ģ����copyһ��SimpleMailMessage ����
		SimpleMailMessage msg 
			= new SimpleMailMessage(this.templateMailMessage);
		msg.setTo(usermail);
		msg.setText(content);
		//�����ʼ�
		try {
			this.mailSender.send(msg);
		} catch (MailException e) {
			System.out.println("[EXCEPTION]:�����ʼ�ʧ��,���͵�" + usermail);
			e.printStackTrace();
		}
	}
	
	public void notifyWithHtml(String usermail,String username,String content) {
		JavaMailSenderImpl sender = (JavaMailSenderImpl) mailSender;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper  helper;
		try {
			helper = new MimeMessageHelper(message, true);
			//��������
			helper.setSubject("֪ͨ");
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
