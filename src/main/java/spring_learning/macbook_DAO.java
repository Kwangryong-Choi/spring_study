package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/* DAO, DTO, VO
 * DAO : 데이터를 Access 하는 역할
 */

// @Repository : Model을 Controller에 호출
@Repository("macbook_DAO")
public class macbook_DAO implements macbook_mapper {	// @Mapper를 interface 로드하여 DAO 작성
	
	// Mybatis => DB 연결
	@Resource(name="template")
	public SqlSessionTemplate st;
	
	// 데이터 수정 메소드
	@Override
	public int macbook_update(macbook_DTO dto) {
		int result = this.st.update("macbook_update",dto);
		return result;
	}
	
	// 하나의 데이터만 가져오는 메소드
	@Override
	public macbook_DTO macbook_one(String midx){
//		System.out.println(midx);
		// selectOne("mapper.xml에서 사용하는 id명",매개변수)
		macbook_DTO onedata = this.st.selectOne("macbook_one",midx);
		return onedata;
	}
	
	/*
	@Override
	public List<macbook_DTO> macbook_one2(String midx){
//		System.out.println(midx);
		// selectOne("mapper.xml에서 사용하는 id명",매개변수)
		List<macbook_DTO> onedata = this.st.selectOne("macbook_one","홍길동");
		return onedata;
	}
	*/
	
	@Override
	public List<macbook_DTO> macbook_select(){
		// selectOne : 데이터를 한 개만 가져올 때 사용 (List배열, ArrayList배열, DTO 처리 모두 가능)
		// selectList : 데이터를 여러 개 가져올 때 사용  (List배열로 가져옴)
		List<macbook_DTO> classList = this.st.selectList("macbook_select");
		return classList;
	}
	
	@Override
	public int macbook_insert(macbook_DTO dto) {
		int result = this.st.insert("macbook_insert",dto);
		return result;
	}
	
	@Override
	public int macbook_delete(int midx) {
		int result = this.st.delete("macbook_delete",midx);
		return result;
	}

}
