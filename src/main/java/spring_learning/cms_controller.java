package spring_learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class cms_controller {
	
	@Resource(name = "template")
	public SqlSessionTemplate st;
	
	@PostMapping("/cms/cmsok.do")
	public String cmsok(@RequestParam String csubject, 
			@RequestParam String cuser,
			@RequestParam(name = "cate", required = false) ArrayList<String> cate) throws Exception {

		/*
		 * ArrayList<String> 클래스 배열로 동일한 checkbox 처리
		 * 
		 *  checkbox가 동일한 name을 가진 것이 여러 개일 경우 => 배열로 값을 받으며 => DB에 저장 시 String으로 변환하여
		 *  => String.join() 클래스를 이용 => DB의 set이라는 자료형으로 저장함
		 */
		String catein = String.join(",", cate);
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("csubject", csubject);
		data.put("cuser", cuser);
		data.put("catein", catein);
		System.out.println(data);
		
		/*
		 * mapper.xml에 다른 table을 사용하더라도 문제가 되지 않음
		 * 단점 : 유지보수 할 경우 해당 쿼리문을 찾아야 함
		 */
		
		int result = this.st.insert("macbook_user.cms_in",data);
		System.out.println(result);
		
		return null;
	}
	
	
	// CMS 상담신청 내역 상세페이지
	@GetMapping("/cms/cmsview.do")
	public String cmsview(Model m) {
		// 데이터 그룹 한개만 가져옴
		/*
		 * mapper에서 resultType String으로 처리했을 경우는 하나의 컬럼값에 대해서만
		 * 처리할 때 사용할 수 있습니다. 단, 여러개의 컬럼으로 처리 시에는 첫 번째 컬럼 외에 모두 loss 처리합니다.
		 * 즉 count, avg, sum, min, max 등 한 개의 데이터만 최종적으로 가져올 때 resultType=String 처리해도 된다
		 */
		// this.st.selectOne("macbook_user.cms_views") 의 자료형이 Map 이므로 결과도 Map으로 받아야 함
//		Map<String, String> result = this.st.selectOne("macbook_user.cms_views");
		List<Map<String, String>> result = this.st.selectList("macbook_user.cms_views");
		System.out.println(result.get(0).get("cate"));
		m.addAttribute("csubject", result.get(0).get("csubject"));
		m.addAttribute("cuser", result.get(0).get("cuser"));
		m.addAttribute("cate", result.get(0).get("cate"));
		
		return null;
	}
}
