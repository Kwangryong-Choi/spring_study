package spring_learning;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class macbook {
	
	// @Autowired, @Inject  :  의존성 주입  =>  xml을 java에서 사용할 수 있게 하는 것, 또는 java를 xml에서 사용할 수 있게하는 것
	
//	@Inject
//	SqlSessionFactoryBean sqlfact;
	
	// Ibatis로 연결
//	@Inject
//	SqlSessionFactory sqlfact;
	
//	@Autowired(required = true)
//	macbook_ServiceImp si;
	
	
	// @Resource : new 클래스명 호출과 동일한 기능,  @Repository 를 호출하는 역할을 함
	@Resource(name="macbook_DAO")
	private macbook_DAO dao;
	
	@Resource(name="macbook_DTO")
	private macbook_DTO macbook_DTO;
	
	// 과정 수정 페이지(출력)
	@PostMapping("/macbook_modify.do")
	public String macbook_modify(@RequestParam("midx") String midx, Model m) {
//		System.out.println(midx);
		macbook_DTO onedata = this.dao.macbook_one(midx);
//		System.out.println(onedata.class_name);	// DTO의 getter 메소드를 호출
		m.addAttribute("onedata", onedata);
		return null;
	}
	
	// 과정 수정 처리
	@PostMapping("/macbook_modifyok.do")
	public String macbook_modifyok(macbook_DTO dto, Model m) {
		
		// insert, update, delete는 무조건 결과를 int로 받음
		int result = this.dao.macbook_update(dto);	// DAO로 값을 전송
		System.out.println(result);
		
		String msg = "";
		
		if(result > 0) {
			msg = "alert('정상적으로 데이터가 수정되었습니다.');"
					+ "location.href='./macbook_list.do';";
		}
		m.addAttribute("msg", msg);
		
		return "load";
	}
	
	// 과정 리스트 출력
	@GetMapping("/macbook_list.do")
	public String macbook_list(Model m) {
		// List<macbook_DTO>  :  DTO 형태의 배열로 생성하며, JSP로 전달
		List<macbook_DTO> classList = this.dao.macbook_select();
//		System.out.println(classList.size());
//		System.out.println(classList.get(0).class_name);
		m.addAttribute("ea",classList.size());
		m.addAttribute("classList",classList);
		return null;
	}
	
	// 개설된 과정을 삭제하는 메소드
	
	// 과정 개설 메소드
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto, Model m){
		try {
			int result = this.dao.macbook_insert(dto);
			String msg = "";
			if(result > 0) {
				msg = "alert('과정 개설이 올바르게 생성되었습니다.');"
						+ "location.href='./macbook_list.do';";
			}
			m.addAttribute("msg",msg);
		} catch (Exception e) {
			
		}finally {
			
		}
		
		return "load";
	}
	
	
	// HttpServletResponse res + PrintWriter   와   Model m + m.addAttribute(res)  는 같이 쓸 수 없다
	// 두 개의 interface 역할이 같으므로 하나만 사용이 가능함
	PrintWriter pw = null;
	
	// 과정 삭제 처리하는 메소드
	@PostMapping("/macbook_delete.do")
	public String macbook_delete(@RequestParam("midx") String midx, HttpServletResponse res) throws Exception {
		res.setContentType("test/html; charset=utf-8");
		
		this.pw= res.getWriter();
		int result = this.dao.macbook_delete(Integer.parseInt(midx));
		if(result > 0) {
			this.pw.print("<script>"
					+ "alert('올바르게 해당 과정을 삭제하였습니다.');"
					+ "location.href='./macbook_list.do';"
					+ "</script>");
		}
		this.pw.close();
		
		return null;
	}
}