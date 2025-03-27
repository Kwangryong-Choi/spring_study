package spring_learning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class test implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("연습 spring controller");
		
		String search = request.getParameter("search");
		System.out.println(search);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test.jsp");
		
		return mv;
	}
}
