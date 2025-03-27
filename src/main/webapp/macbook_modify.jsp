<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과정 수정 페이지</title>
</head>
<body>
<script src="./macbook.js"></script>
<form id="frm" method="post" action="./macbook_modifyok.do">
<!-- 수정 또는 삭제 시에는 무조건 고유값이 필요함  =>  여기서는 midx  =>  그래서 hidden 속성으로 값을 받아야 함 -->
<input type="hidden" name="midx" value="${onedata.midx}">
과정구분 : 
<select name="class_part">
<option value="온라인 상시과정">온라인 상시과정</option>
<option value="온라인 정규과정">온라인 정규과정</option>
<option value="집합과정">집합과정</option>
</select name="class_cate"><br>
과정 카테고리 : <select name="class_cate">
<option value="교육" <cr:if test="${onedata.class_cate == '교육'}">selected</cr:if>>교육</option>
<option value="보강" <cr:if test="${onedata.class_cate == '보강'}">selected</cr:if>>보강</option>
<option value="자격증" <cr:if test="${onedata.class_cate == '자격증'}">selected</cr:if>>자격증</option>
</select><br>
과정 개설명 : <input type="text" name="class_name" value="${onedata.class_name}"><br>
학습일수 : <input type="text" name="class_day" value="${onedata.class_day }"><br>
정가 : <input type="test" name="class_price" value="${onedata.class_price }"><br>
수강료 : <input type="text" name="class_sales" value="${onedata.class_sales }"><br>
과정소개 : <textarea rows="5" cols="100" name="class_info">${onedata.class_info }</textarea><br>
강사명 : <input type="text" name="class_teacher" value="${onedata.class_teacher }"><br>
학습목표 : <textarea rows="5" cols="100" name="class_object">${onedata.class_object }</textarea><br>
강의 진행여부 : <input type="radio" value="Y" name="class_use" value="Y" <cr:if test="${onedata.class_use == 'Y'}">checked</cr:if> >진행
<input type="radio" value="N" name="class_use" value="N" <cr:if test="${onedata.class_use == 'N'}">checked</cr:if> > 정지<br>
<input type="button" value="개설된 강의 수정" onclick="save_class()">
</form>
</body>
<script>
// .js라는 파일에서는 Back-end 코드(jstl)를 사용할 수 없음  =>  이렇게 jsp나 html 안에 script태그를 사용해서 jstl을 사용해야 함
window.onload = function(){
	// javascript 변수는 back-end의 모든 변수값을 받을 수 있음
	var subject = "${onedata.class_part}";
//	console.log(subject);
	var ea = frm.class_part.length;
//	console.log(ea);
//	console.log(frm.class_part[2].value);
	for(var f=0; f<ea; f++){
		if(frm.class_part[f].value == subject){
			frm.class_part[f].selected = "selected";
		}
	}
}
</script>
</html>