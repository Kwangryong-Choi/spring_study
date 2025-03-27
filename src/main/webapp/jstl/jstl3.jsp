<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
//	String data[] = new String[] {"hong","kim","park"};
//	request.setAttribute("data", data);
	ArrayList<String> data = new ArrayList();
	data.add("hong");
	data.add("kim");
	data.add("park");
	request.setAttribute("data", data);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초 3 (반복문 - foreach)</title>
</head>
<body>
<table border="1">
<tr>
<!-- foreach : JSTL 유일하게 배열 및 반복문으로 사용하는 속성
begin : 시작값		=>	0부터 시작해야 첫 인덱스부터 나옴
end : 종료값
 -->
<%-- foreach 기본형태 구조 
<cr:forEach var="z" begin="1" end="5">
<td>${z}</td>
</cr:forEach>
--%>
<%-- items : 배열값을 받는 속성,
setattribute에 값이 있을 경우  =>  items = ${data}
변수에 값이 담겨 있을 경우		 =>	 items = <%=data%>
  --%>
  
<!-- jstl 반복문은 배열의 데이터수보다 반복횟수가 많아도 에러가 나지 않음 -->
<cr:forEach var="z" items="${data}" begin="0" end="100">
<cr:if test="${z != 'kim' }">
<td>${z}</td>
</cr:if>
</cr:forEach>

</tr>
</table>

</body>
</html>