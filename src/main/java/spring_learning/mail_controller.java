package spring_learning;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mail_controller {
	
	@PostMapping("/contactusok.do")
	public String contactusok(@RequestParam String subject,
			@RequestParam String mname,
			@RequestParam String memail,
			@RequestParam String mtext) throws Exception {
		
		// Properties : Map 보다 좀 더 발전된 기능  =>  환경설정 파일 형태의 배열 (.ini)
		// smtp, imap, pop
		Properties props = new Properties();
		props.put("mail.smtp.hos", "smtp.naver.com");		// 메일 발송 서버
		props.put("mail.smtp.port", "587");					// 메일 발송 서버의 포트
		props.put("mail.smtp.auth", "true");				// 메일 발송 서버의 인증 (아이디, 패스워드)
		props.put("mail.smtp.starttls.enable", "true");		// 메일 서버의 TLS를 연결
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");	// 메일 서버의 SSL 기능 사용
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		// 메일 발송에 대한 로그인 (메일 서버 로그인 정보) 사항을 Session으로 등록 시킴
		Session session = Session.getInstance(props,new Authenticator() {
			
			// 로그인 할 ID와 패스워드 정보를 입력
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(memail, "ckrjjang");
			}
		});
		
		// Mime : 이메일 전송을 위한 인터넷 표준 포맷
		try {	// 메일 내용을 발송하는 상황
			Message msg = new MimeMessage(session);
			// 보내는 사람 메일 주소 + 보낸이
			msg.setFrom(new InternetAddress(memail,mname,"utf-8"));
			// 받는 사람
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("ckrjjang2@naver.com"));
			// 메일 제목
			msg.setSubject(subject);
			// 메일 내용
			msg.setContent(mtext, "text/html; charset=utf-8");
			// 메일 발송에 대한 메소드
			Transport.send(msg);
		} catch (Exception e) {	// 메일 발송에 대한 서버 접근오류 발생시 출력 코드
			e.printStackTrace();
		}finally {
			
		}
		
		return null;
	}
}
