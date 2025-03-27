<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과정 개설 리스트</title>
</head>
<body>
<p>개설된 과목 개수 : ${ea}</p>
<!-- DTO에 있는 변수명으로 JSTL로 출력하는 형태 -->
<cr:forEach var="cdata" items="${classList}" varStatus="idx">
과정명: ${cdata.class_name} , 강사명 : ${cdata['class_teacher']} , 수강료 :  ${cdata.class_price}
<input type="button" value="수정" onclick="macbook_modify('${cdata.midx}')">
<input type="button" value="삭제" onclick="macbook_del('${cdata.midx}')"><br>
</cr:forEach>
<!-- 해당 게시물의 고유값을 POST 통신으로 Back-end에게 전송 -->
<form id="frm" method="post">
<input type="hidden" name="midx" value="">
</form>
</body>
<script>
function macbook_modify(n){
	/*
//	console.log(n);
	// GET 통신으로 보내는 경우
//	location.href='./macbook_modify.do?midx='+n;
	
	var m = document.getElementById("msg");
	m.innerHTML = '<form id="frm" method="post" action="./macbook_modify.do"><input type="hidden" name="midx" value="+n+"></form>';
	*/
	
	
	// POST 통신으로 보내는 경우  =>  POST통신은 무조건 form태그를 사용해야 함
	frm.midx.value = n;
	frm.action = "./macbook_modify.do";
	frm.submit();
}
function macbook_del(n){
//	console.log(n);
	if(confirm('해당 과정을 삭제하시겠습니까? \n 삭제 시 데이터는 복구되지 않습니다.')){
		frm.midx.value = n;
		frm.action = "./macbook_delete.do";
		frm.submit();
	}
}

</script>
</html>