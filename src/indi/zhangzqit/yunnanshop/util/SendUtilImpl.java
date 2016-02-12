package indi.zhangzqit.yunnanshop.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Component;

import indi.zhangzqit.yunnanshop.service.SendUtil;

@Component("sendlUtil")
public class SendUtilImpl implements SendUtil {

	@Override
	public void sendEmail(String id, String total, String email) {
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		Message message = null;
		Transport transport = null;
		Session session = null;
		try {
			session = Session.getDefaultInstance(prop);
			session.setDebug(true);
			// ����һ�������ʼ�
			message = (Message) new MimeMessage(session);
			// ���⡢�������ݡ������˵�ַ
			message.setSubject("����֧���ɹ��ʼ�(ϵͳ�ʼ�)");
			message.setContent("�������Ϊ:" + id + ",��� Ϊ: " + total + ",�Ѿ�֧���ɹ�!",
					"text/html;charset=utf-8");
			message.setFrom(new InternetAddress("soft03_test@sina.com"));
			// �����û�������,�ռ��˵�ַ,�����ʼ�
			transport = session.getTransport();
			// ͨ���û���������, �����ʼ�������
			transport.connect("smtp.sina.com", "soft03_test", "soft03_test");
			transport.sendMessage(message, new Address[] { new InternetAddress(
					email) });
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				// �رտͻ���(�ͷ���Դ)
				transport.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void sendMessage(String id, String total, String phone) {
		// �������������
		HttpClient client = new HttpClient();
		// ����post����
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		post.setParameter("Uid", "gz_shop_ms");
		post.setParameter("Key", "0d0dd0eeca547bcb896d");
		post.setParameter("smsMob", phone);
		post.setParameter("smsText", "�������Ϊ:" + id + ",��� Ϊ: " + total
				+ ",�Ѿ�֧���ɹ�!");
		// ����post����
		try {
			int code = client.executeMethod(post);
			System.out.println("http״̬��:200����OK: " + code);
			// �鿴���ͽ��
			System.out.println(post.getResponseBodyAsString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) throws Exception {

		SendUtilImpl sendUtilImpl = new SendUtilImpl();
		sendUtilImpl.sendMessage("123456789", "45.67", "18027364651");
	}
}
