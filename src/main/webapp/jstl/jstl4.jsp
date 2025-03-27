<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String member[][] = {
			{"홍길동","감강찬","이상혁","문현준"},
			{"A형","B형","O형","AB형"},
			{"50","23","45","35"}
	};

	ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
	ArrayList<String> data = new ArrayList<String>();
	data.add("에어프라이어");
	data.add("냉장고");
	all.add(data);
	data.add("에어컨");
	ArrayList<String> data2 = new ArrayList<String>();
	data.add("250000");
	data.add("350000");
	data.add("450000");
	all.add(data2);
	
	out.print(all);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초 문법 - (반복문 2차)</title>
</head>
<body>
<!-- 배열의 데이터 수 만큼 반복문 돌리는 법 -->
<!-- varStatus : 배열번호 0부터 시작   =>   값.index -->
<cr:set var="aa" value="<%=member[1]%>"></cr:set>
<cr:set var="bb" value="<%=member[2]%>"></cr:set>
<cr:forEach var="cc" items="<%=member[0]%>" varStatus="no">
고객명 : ${cc },      혈액형 : ${aa[no.index]},   나이 : ${bb[no.index] }<br>
</cr:forEach>
<br><br><br><br>

<!-- Class 배열에 JSTL 출력 -->
<cr:set var="dd" value="<%=all.get(1) %>"/>	<!-- 가격 데이터 전체를 가져옴 -->
<cr:forEach var="ee" items="<%=all.get(0)%>" varStatus="no">
번호:${no.index} , 제품명:${ee} , 가격:${dd.get(no.index)} , <br>
</cr:forEach>


</body>
</html>