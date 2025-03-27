package spring_learning;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.xdevapi.JsonArray;

// API 서버 구축,  API 전용 Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")	// 모든 메소드의 CORS 해결,   Spring, Spring-boot 에서 사용
@RestController
public class api_controller {
	PrintWriter pw = null;	// Front-end가 값을 가져갈 수 있도록 함
	
	@Resource(name = "macbook_member_DTO")
	macbook_member_DTO dto;
	
	@Resource(name = "user_DAO")
	user_DAO dao;
	
	@GetMapping("/api_data.do")
	public String api_data(HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=utf-8");
		this.pw = res.getWriter();
		
		/*
		 * JSONArray : []  =>  배열  =>  put 사용해서 값 넣음
		 * JSONObject : {}  =>  키 배열  =>  put 사용해서 값 넣음
		 * 
		 * 
		 * org.json.simple.JSONArray  :  add 사용해서 값 넣음
		 */
		/* ["a","b","c","d"]
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");

		this.pw.print(ja);
		*/
		/*
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		JSONObject jo = new JSONObject();
		jo.put("data", ja);
		this.pw.print(jo);
		*/
		
		org.json.simple.JSONArray ja = new org.json.simple.JSONArray();
		ja.add("a");
		
		this.pw.close();
		
		return null;
	}
	
	@GetMapping("/api_data2.do")
		public String api_data2(HttpServletResponse res) throws Exception {
			res.setContentType("text/html; charset=utf-8");
			this.pw = res.getWriter();
			
			/*
			// 내부 배열 (2차 배열) 생성
			JSONArray ja = new JSONArray();
			ja.put("홍길동");
			ja.put("강감찬");
			
			// 외부 배열 (1차 배열) 생성
			JSONArray ja2 = new JSONArray();
			ja2.put(ja);
			
			this.pw.print(ja2);
			*/
			
			/*
			// 내부 데이터 배열 생성
			JSONArray ja = new JSONArray();
			ja.put("홍길동");
			ja.put("강감찬");
			
			// 내부 키배열 생성
			JSONObject jo = new JSONObject();
			jo.put("member", ja);
			
			// 외부 1차 배열 생성
			JSONArray ja2 = new JSONArray();
			ja2.put(jo);
			
			this.pw.print(ja2);
			*/
			
			this.pw.close();
			
			return null;
	}
	
	@GetMapping("/api_data3.do")
	public String api_data3(HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=utf-8");
		this.pw = res.getWriter();

		String db[] = {"hong","홍길동","hong@nate.com","서울","01012345678"};
		JSONObject jo = new JSONObject();
		jo.put("id", db[0]);
		jo.put("name", db[1]);
		jo.put("email", db[2]);
		jo.put("area", db[3]);
		jo.put("phone", db[4]);
		
		JSONArray ja = new JSONArray();
		ja.put(jo);
		
		JSONObject jo2 = new JSONObject();
		jo2.put("myinfo", ja);
		
		this.pw.print(jo2);
		
		this.pw.close();
		
		return null;
	}
	
	@GetMapping("/api_data4.do")
	public String api_data4(HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=utf-8");
		this.pw = res.getWriter();
		
		String data[][] = {
				{"모니터", "키보드", "마우스"},
				{"NEW", "BEST", "NEW"}
		};
		
		int w=0;
		String keyname = "";
		/*
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Credentials", "true");
		 * 
		 */
		JSONObject alldata = new JSONObject();	// 대표키
		JSONObject jb = new JSONObject();		// 보조키
		while(w<data.length) {
			
			JSONArray jo = new JSONArray();		// 데이터 배열
			for(int f=0; f<data[w].length; f++) {
				jo.put(data[w][f]);	// 데이터 배열을 생성
			}
			// 데이터 배열에 맞는 보조키를 적용하기 위함
			if(w==0) {
				keyname = "product_name";
			}else if(w==1) {
				keyname = "product_ico";
			}
			jb.put(keyname, jo);
			
			w++;
		}
		alldata.put("product", jb);	// 대표키 생성은 최종 반복문 밖에 코드를 작성
		this.pw.print(alldata);
		/*
		JSONArray ja1 = new JSONArray();
		ja1.put(data[0][0]);
		ja1.put(data[0][1]);
		ja1.put(data[0][2]);
		JSONArray ja2 = new JSONArray();
		ja2.put(data[1][0]);
		ja2.put(data[1][1]);
		ja2.put(data[1][2]);
		
		JSONObject jo1 = new JSONObject();
		jo1.put("product_name", ja1);
		jo1.put("product_ico", ja2);
		
		JSONObject jo2 = new JSONObject();
		jo2.put("products", jo);
		
		
		this.pw.print(jo2);
		*/
		
		this.pw.close();
		return null;
	}
	
	// Mysql DB 가져와서 API로 생성
	@GetMapping("/api_data5.do")
	public String api_data5(HttpServletResponse res) throws Exception {
		List<macbook_member_DTO> result = this.dao.all_list();
		JSONObject alldata = new JSONObject();
		res.setContentType("text/html; charset=utf-8");
		this.pw = res.getWriter();
		JSONArray ja = new JSONArray();
		int w=0;
		while(w<result.size()) {
			JSONObject jo = new JSONObject();
			jo.put("midx", result.get(w).midx);
			jo.put("mid", result.get(w).mid);
			jo.put("mname", result.get(w).mname);
			jo.put("memail", result.get(w).memail);
			ja.put(jo);
			this.pw.print(jo);
			w++;
		}
		alldata.put("member", ja);
		this.pw.close();
		return null;
		
	}
}
