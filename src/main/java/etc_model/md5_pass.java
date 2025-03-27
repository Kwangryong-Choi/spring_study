package etc_model;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;

// 사용자 비밀번호 및 게시판 글 등록 시 사용 또는 비회원에 사용하는 md5
/*	abstract 사용할 경우 => @Repository 필요없음
@Repository("md5_pass")
public class md5_pass {
*/
abstract public class md5_pass{
	public String md5_make(String pw) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(pw.getBytes());
			for(byte b : md.digest()) {
				sb.append(String.format("%x", b));
			}
		} catch (Exception e) {
			sb.append("null");
		}finally {
			
		}
		
		return sb.toString();
	}
}
