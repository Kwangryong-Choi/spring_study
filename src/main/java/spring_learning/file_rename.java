package spring_learning;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository("file_rename")
public class file_rename {
	
	// 홍길동.jpg  =>  2025032755.jpg  로 바꾸는 메소드
	public String rename(String filenm) {
		
		// 속성
		int com = filenm.lastIndexOf(".");
		String fnm = filenm.substring(com);
		
		// 날짜
		Date day = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String today = sf.format(day);	// 년.월.일
		
		// 랜덤
		int no = (int)Math.ceil(Math.random()*10000);
		String makefile = today + no + fnm;	// makefile 예시) 2025032715
		
		return makefile;
	}
}
