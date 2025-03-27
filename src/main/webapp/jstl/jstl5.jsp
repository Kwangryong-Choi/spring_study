<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초 - (Database 연결)</title>
</head>
<body>
<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver" 
url="jdbc:mysql://localhost:3306/mrp" 
user="project" 
password="0236"
/>
<%-- ${db} --%>

<!-- ddl1 방식 -->
<%-- <sql:query var="ps" sql="select * from event" dataSource="${db }"></sql:query> --%>

<!-- ddl2 방식 -->
<cr:set var="tables" value="event"/>
<sql:query var="ps" dataSource="${db}">
select * from event
</sql:query>

<%-- ${ps.rows} --%>
<!-- rows : Database의 rows의 전체값을 말함 -->
<cr:forEach var="r" items="${ps.rows }">
<!-- fn 기능은 직접 내부 안에 적어서 사용해야 함  =>  각종 명령어를 사용할 수 있게 함 -->
<!-- fn 은 결과값에 옵션형태로 함수를 적용하여 결과에 대한 사항을 변화시킴 (태그가 없음) -->
고객명: ${r['ename']}, 이메일: ${r['email']}, 등록일: ${fn:substring(r['ejoin'],0,10)}<br>
</cr:forEach>


</body>
</html>