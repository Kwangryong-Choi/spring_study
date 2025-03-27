<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
Servlet  =>  jsp
Spring  =>  jstl (엔진 필요)
Spring-boot  -> thymeleaf (엔진 필요)
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- Controller (ModelAndView)  =>  jstl 출력  =>  ${} 이렇게 사용, jsp주석 사용해야 함 --%>
상품명 : ${pdnm}<br>
상품코드 : ${pcode}<br>
상품가격 : ${pmoney}<br>
</body>
</html>