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
<script>
function spage(){
	if(sform.search.value==""){
//		alert("배너명을 입력하세요.");
		return false;
	}else{
		return;
	}
}
</script>
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
		<th><input type="checkbox"></th>
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
<cr:forEach var="bn" items="${all}" varStatus="idx">
<tr height="50">
	<td><input type="checkbox"></td>
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
<form id="dform" action="">
<input type="hidden">
</form>
<input type="button" value="선택삭제">
<script>
	function pg(no){
		location.href='./bannerlist?pageno='+no;
	}
</script>

</body>
</html>