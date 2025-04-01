<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="sform" method="get" action="./bannerlist" onsubmit="return spage()">
<p>배너명 검색 : <input type="text" name="search" value="${search}">
<input type="submit" value="검색">
<input type="button" value="전체목록" onclick="location.href='./bannerlist'">
</p>
</form>
<p>전체 등록된 배너 개수 : ${total}</p>
<table border="1" cellpadding="0" cellspacing="0" >
<thead>
	<tr>
		<th><input type="checkbox" id="allck" onclick="check_all(this.checked)"></th>
		<th width="80">번호</th>
		<th width="300">배너명</th>
		<th width="100">이미지</th>
		<th width="150">파일명</th>
		<th width="150">등록일</th>
	</tr>
</thead>

<!-- 배열값을 조건문으로 jstl에 처리 시 functions 를 이용하여 length로 검토하여 처리합니다. -->
<cr:if test="${fn:length(all) == 0}">
<tbody>
	<tr height="50">
		<td colspan="6" align="center">검색된 데이터가 없습니다.</td>
	</tr>
</tbody>
<tbody>
</cr:if>

<tbody>
<cr:set var="ino" value="${total-userpage}"/>	<!-- 게시물 일련번호 셋팅 -->
<form>
<!-- foreach 즉 반복문 안에는 id를 쓰면 중복되므로 id 속성은 사용하면 안됨 -->
<cr:forEach var="bn" items="${all}" varStatus="idx">
<tr height="50">
	<!-- bidx : DB에서 사용된 auto_increment 값 -->
	<td><input type="checkbox" name="ckbox" value="${bn.bname}"></td>
	<td align="center">${ino-idx.index}</td>
	<td>${bn.bname}</td>
	<td>
	<cr:if test="${bn.file_url == null}">
		No Image
	</cr:if>
	<cr:if test="${bn.file_url != null}">
		<img src="..${bn.file_url}" style="width:100px; height:100px;">
	</cr:if>
	</td>
	<td><a href="../upload/${bn.file_new}" target="_blank" title="${bn.file_new}">${bn.file_ori}</a></td>
	<td>${fn:substring(bn.bdate,0,10)}</td>
</tr>
</cr:forEach>
</form>
</tbody>
</table>
<br><br>

<!-- paging -->
<table border="1" cellpadding="0" cellspacing="0" >
	<tbody>
		<tr height="30">
		<!-- Controller에서 데이터의 전체 개수를 받음 해당 값을 한 페이지당 5개씩 출력하는 구조
		산수식을 입력하여 총 페이징 번호를 생성하여 출력함 -->
		<!-- js에는 몫연산자 같은 게 없어서 %1 시 진짜 소수점 밑 수를 반환  =>      2.8 % 1  =>  0.8
		나누기도 그대로 반환  =>  14/5  =>  2.8    -->
		<cr:set var="pageidx" value="${total / 5 + (1-(total/5)%1)}"/>
		${pageidx}
		<cr:forEach var="no" begin="1" end="${pageidx}" step="1">
			<td width="30" align="center" onclick="pg('${no}')">${no}</td>
		</cr:forEach>
		</tr>
	</tbody>
</table>

<br><br>

<!-- form 전송으로 선택된 값을 삭제하는 프로세서 -->
<form id="dform" action="./bannerdel">
<input type="hidden" name="ckdel" value="1,3,5">
</form>
<input type="button" value="선택삭제" onclick="check_del()">
</body>
<script>
function spage(){
	if(sform.search.value==""){
//		alert("배너명을 입력하세요.");
		return false;
	}else{
		return;
	}
}

function pg(no){
	location.href='./bannerlist?pageno='+no;
}

// 전체 선택 관련 핸들링 함수
function check_all(ck){
	var ea = document.getElementsByName("ckbox");
//	ea[0].checked = true;
	
	if(ck == true){		// 전체선택 했을 경우
		var w = 0;
		while(w < ea.length){
			ea[w].checked = true;
			w++;
		}
	}else{	// 전체선택을 해제할 경우
		var w = 0;
		while(w < ea.length){
			ea[w].checked = false;
			w++;
	}
	
	// 조건문 없이 전체해제, 전체선택  =>  ck가 true or false 이기 때문에
	/*
	var w = 0;
	while(w < ea.length){
		ea[w].checked = ck;
		w++;
	}
	}
	*/
}

// 선택삭제 버튼 클릭 시 리스트에서 체크된 값을 확인 후 배열화하여 hidden으로 값을 적용하여 Back-end로 문자열로 전달 
function check_del(){
	var ar = new Array();	// script 배열
	var ob = document.getElementsByName("ckbox");
	var w = 0;
	while(w < ob.length){
		if(ob[w].checked == true){
			ar.push(ob[w].value);
		}
		w++;
	}
	dform.ckdel.value = ar;
	if(confirm("해당 데이터를 삭제 시 복구되지 않습니다.")){
		dform.submit();
	}
}
</script>
</html>