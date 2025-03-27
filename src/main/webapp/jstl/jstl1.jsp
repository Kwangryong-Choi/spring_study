<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jstl 엔진 -->    
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jstl 각종 함수들 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- jstl Database 관련사항 -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!-- jstl 부가옵션 (금액, 날짜, 시간, 통화기호 등)을 사용할 수 있게 함 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String user = "홍길동";	// jsp 변수
	// jsp session 생성
	HttpSession hs = request.getSession();
	hs.setAttribute("ssdata", "1588-1004");
	// jsp session 출력
	String se = (String)hs.getAttribute("ssdata");
	out.print(se);
	
	String tels = (String)hs.getAttribute("tel");
	out.print(tels);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초문법1 ( set + session )</title>
</head>
<body>
<!-- jstl 태그 형태 -->
<!-- core 태그 속성
set : setAttribute 와 동일한 기능,   var로 변수 생성,  value로 값 생성
 -->
<cr:set var="a" value="감강찬"/>
<input type="text" name="mname" value="${a}">
<cr:set var="nm" value="<%=user %>" />
고객명 : ${nm }
<cr:out value="값출력 합니다."/>	<!--   cr:out = out.print()   -->
<br><br><br><br><br><br>
<p>JSP Session 값</p>
<!-- JSTL 로 세션을 생성하는 방식     scope : session, request, page -->
<cr:set var="tel" value="02-1004-1004" scope="session"/>
JSP에 session 데이터 : ${ssdata}<br>
세션 데이터 : ${tel}
<br><br><br><br><br>
<%
String money = "50000";
%>
<!--
<cr:set var="kk" scope="request"><%=money%></cr:set>
scope의 기본값이 request 이므로 안 써도 같음
밑의 코드와 같은 의미임
-->
<cr:set var="kk" value="<%=money%>"/>
${kk}

</body>
</html>