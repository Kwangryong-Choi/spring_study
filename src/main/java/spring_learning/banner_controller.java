package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class banner_controller {
	List<String> listdata = null;
	Map<String, String> mapdata = null;
	PrintWriter pw = null;
	String result = null;
	int callback = 0;
	ModelAndView mv = null;
	
	@Resource(name = "banner_DTO")
	banner_DTO dto;
	
	@Resource(name = "banner_DAO")
	banner_DAO dao;
	
	@Resource(name = "file_rename")	// 파일명을 개발자가 원하는 형태로 변경
	file_rename fname;
	
	// Field 에 있는 dto와 매개변수에 있는 dto는 다른 형태를 가지고 있음
	// this.dto는 필드에 선언한 dto를 가져오는 것,   그냥 dto는 매개변수에서 선언한 dto를 가져오는 것
	
	// @ModelAttribute : 1대1 매칭  =>  name의 value값과 DTO 자료형 변수가 같은 것이 있으면 Setter적용
	@PostMapping("/banner/bannerok")
	public String bannerok(@ModelAttribute(name = "dto") banner_DTO dto, MultipartFile bfile,
			HttpServletRequest req) throws Exception {
		String file_new = null;
		
		if(bfile.getSize() > 0) {
			
			file_new = this.fname.rename(bfile.getOriginalFilename());
			
			// 웹 디렉토리 개발자가 생성한 파일명으로 저장하는 코드
			String url = req.getServletContext().getRealPath("/upload/");
//			System.out.println(url);
			FileCopyUtils.copy(bfile.getBytes(), new File(url + file_new));
			
			// 웹디렉토리 경로 및 파일명 설정
			dto.setFile_url("/upload/" + file_new);
			
			dto.setFile_new(file_new);	// 개발자가 원하는 방식으로 파일명을 변경한 값  을  setter로 넣어 dto, mapper에서 사용할 수 있게 함
			dto.setFile_ori(bfile.getOriginalFilename());	// 사용자가 적용한 파일명
		}
		
		this.callback = this.dao.new_banner(dto);
//		System.out.println(this.callback);

		return null;
	}
	
	// 검색에 관련사항은 필수조건은 아니며, 또한 null 처리가 되었을 경우 defaultValue 값이 공백 처리
	@GetMapping("/banner/bannerlist")
	public String bannerlist(Model m, 
			@RequestParam(name="search", defaultValue = "", required = false)String search, 
			@RequestParam(defaultValue = "1", required = false)Integer pageno) {
//		System.out.println(search);
//		System.out.println(pageno);
		
		//데이터 총 갯수 확인 코드
		int total = this.dao.banner_total();
//		System.out.println(total);
		
		// 끝
		int userpage = 0;
		if(pageno == 1) {
			userpage = 0;
		}else {	// 1외의 페이지 번호 클릭 시
			userpage = (pageno - 1) * 5;
		}
		// 해당 일련번호를 계산하여 jsp에 전달
		m.addAttribute("userpage", userpage);
		
		List<banner_DTO> all = null;
		if(search.intern() == "") {		// 검색어가 없을 경우
			all = this.dao.all_banner(pageno);	// 인자값 : 사용자가 페이지 번호를 클릭한 값
		}else {	// 검색어가 있을 경우
			all = this.dao.search_banner(search);
		}
		m.addAttribute("search", search);	// 검색어를 jsp로 전달
		m.addAttribute("all",all);
		m.addAttribute("total", total);		// 데이터 전체 개수
		
		return null;
	}
	
	@PostMapping("/banner/bannerdel")
	public String bannerdel(@RequestParam(defaultValue = "", required = false) String ckdel, Model m) {
		String msg = "";
		this.callback = 0;	// 초기화
		if(ckdel.equals("")) {
			msg = "alert('올바른 접근이 아닙니다.');"
					+ "location.href='./bannerlist'";
		}else {
			String no[] = ckdel.split(",");
			int w = 0;
			while(w < no.length) {	// Front-end에서 체크된 값만큼 반복
				int result = this.dao.banner_del(no[w]);
				if(result > 0) {
					this.callback++;
				}
				w++;
			}
			// -1을 사용한 이유는 반복문에 조건이 없으므로 +1이 작동될 수 있음
			if(no.length == this.callback - 1) {
				msg = "alert('정상적으로 삭제되었습니다.');"
						+ "location.href='./bannerlist'";
			}else {
				msg = "alert('비정상적인 데이터가 확인되었습니다.');"
						+ "location.href='./bannerlist'";
			}
			
		}
		m.addAttribute("msg", msg);
		return "load";
	}
}
