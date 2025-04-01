package spring_learning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

// Spring Session 사용법
//해당 세션이 생성되었을 경우 모든 메소드에 세션값을 Model로 전송 가능
// @SessionAttributes("mid")
/* @SessionAttributes : Controller에서 셋팅된 값이며, DTO가 있어야 정상적으로 핸들링 가능함
 * DTO 형태는 Session 형태의 DTO를 사용해야 함
 * @SessionAttributes  =>  API Server 에서 사용됨
 */
@Controller
public class session_controller {
	
	// Session을 의존성 주입형태로 interface를 필드에 선언하여 모든 메소드에 세션을 적용할 수 있음
	@Autowired
	HttpSession hs;
	
	@GetMapping("/session1.do")
	public String session1(HttpSession se) {	// HttpSession (=스프링 형태) = HttpServletRequest (=서블렛 형태) 를 이용하여 session 사용
		String userid = "kim";
		se.setAttribute("mid", userid);
		
		return null;
	}
	
	// 핻아 세션을 생성 후 문자열 변수로 변환하여 Model로 전달  =>  jsp에 출력
	@GetMapping("/session2.do")
	public String session2(HttpSession se, Model m) {
		/*
		String id = (String)se.getAttribute("mid");
		System.out.println(id);
		*/
		String id = (String)se.getAttribute("mid");
		m.addAttribute("mid", id);
		return "session";
	}
	
	/* @SessionAttribute = session.getAttribute 와 동일한 성능을 가진 어노테이션
	 * 해당 어노테이션 사용 시 주의사항 : null값일 경우 400 error가 발생할 수 있으므로 
	 * name 속성과 required 속성을 핸들링하는 것이 중요 포인트임
	 */
	@GetMapping("/session3.do")
	public String session3(@SessionAttribute(name="mid", required = false) String mid) {
		System.out.println(mid);
		String test = (String)this.hs.getAttribute("mid");	// 필드에 있는 세션
		System.out.println(test);
		return null;
	}
	
	@GetMapping("/session4.do")
	public String session4() {
		this.hs.invalidate();	// 필드에 올려놓은 Session 을 로드하여 초기화함
		this.hs.removeAttribute("mid");	// 등록된 session의 특정 키만 삭제
		System.out.println(this.hs.getAttribute("mid"));
		return null;
	}
}
