package spring_learning;

import org.springframework.stereotype.Repository;

import lombok.Data;

// DTO를 생성 시 무조건 config.xml에 추가해야 함

// @Data : @Setter 와 @Getter 를 함께 적용하는 어노테이션
@Data
@Repository("banner_DTO")
public class banner_DTO {
	int bidx;
	String bname, file_ori, file_new, file_url, bdate;
}
