package spring_learning;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

// Spring Controller + View 기초

// @Controller : 해당 일반 class를 web에서 작동할 수 있도록 변경하도록 함
@Controller
public class mainpage {
	PrintWriter pw = null;
	
	// @RequestMapping : doService
	// @PostMapping : doPost
	// @GetMapping : doGet
	
	// throws + HttpServletRequest + HttpServletResponse   =>   View 사용하지 않음   =>   PrintWriter 바로 사용
	@GetMapping("/abc.do")
	public void abc(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		
		this.pw = res.getWriter();
		this.pw.write("<script>"
				+ "alert('테스트 페이지 입니다.')"
				+ "</script>");
		this.pw.close();
		System.out.println("abc 페이지");
	}
	
	// View 무조건 사용하는 메소드를 알림
	@PostMapping("/bbb.do")
	public void bbb(HttpServletRequest req) {
		try {
			// Front-end에서 값을 이관 받음
			String pdnm = req.getParameter("pdnm");
			// View(bbb.jsp)로 값을 이관
			req.setAttribute("pdnm", pdnm);
		} catch (Exception e) {
			
		}
		System.out.println("bbb 페이지");
	}
	
	// return 형태의 메소드는 view 파일명을 다르게 사용할 수 있습니다.
	// 기본은 return null (do와 이름이 같은 jsp를 찾게됩니다.)
	// return 값을 사용할 경우  =>  do와 다른 이름의 jsp를 찾게됩니다.
	@PostMapping("/ccc.do")
	public String ccc(HttpServletRequest req) {
		String pdnm = req.getParameter("pdnm");
		req.setAttribute("pdnm", pdnm);
		return "/product_list";
	}
	
	// ModelAndView : 모델에 있는 값을 가져와서 사용
	// request로 view(jsp)에 전달하는 방식이 아님
	@PostMapping("/ddd.do")
	public ModelAndView ddd(HttpServletRequest req) {
		
		String pdnm = req.getParameter("pdnm");
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		// ModelAndView (기본 자료형 : object) : 배열
		ModelAndView mv = new ModelAndView();
		mv.addObject("pdnm",pdnm);		// addObject : 키 배열 형태로 값을 저장
		mv.addObject("pcode",pcode);
		mv.addObject("pmoney",pmoney);
		
		// setView() : 값이 null일 경우 mapping 이름과 같은 jsp를 참조, 사용하지 않아도 기본값으로 설정
//		mv.setView(null);
		
		// setViewName() : mapping 이름과 다른 이름을 사용하고 싶을 경우 어떤 View 파일(jsp)을 참조할 것인지 설정
		mv.setViewName("bbb");
		
		return mv;	// return 값은 무조건 ModelAndView 객체를 사용해야 함
	}
	
	@PostMapping("/eee.do")
	public String eee(HttpServletRequest req, Model m) {
		String pdnm = req.getParameter("pdnm");
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		// Model(interface)를 이용하여 JSP로 값을 전달 ( JSTL형태로 값을 출력 )
		m.addAttribute("pdnm",pdnm);
		m.addAttribute("pcode",pcode);
		m.addAttribute("pmoney",pmoney);
		
		return "ddd";
	}
	
	
	
	
	
	
	
	
	
}