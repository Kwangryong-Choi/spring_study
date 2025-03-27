<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초2 (if문)</title>
</head>
<body>
<!-- test : 안의 식이 true일 경우 변수(var)에 true 담음 -->
<cr:if test="${5 < 10}" var="result"/>
${result}

<!-- jstl if문   lt(<),  gt(>),  le(<=),  ge(>=) -->

<!-- 안의 식이 true일 경우 cr태그 안의 값을 출력 -->
<cr:set var="a" value="50" />
<fmt:parseNumber value="${a}" type="number" var="aa" />	<%-- fmt:parseNumber type="number"   =>   a(value로 지정)의 자료형을 숫자형으로 바꾼 후 aa라는 변수로 저장 --%>
<cr:set var="b" value="200" />
<fmt:parseNumber value="${b}" type="number" var="bb" />
${aa}
${bb}
<cr:if test="${aa > bb }">
a값이 큽니다.
a
</cr:if>
<cr:if test="${aa < bb }">
b값이 큽니다.
b
</cr:if>
<!-- else가 존재하지 않음 -->
<cr:if test="${a == b }">
동일한 값
</cr:if>
<br><br><br><br><br>

<!-- eq(==), ne(!=), or(||), and(&&) -->
<cr:set var="product" value="그래픽카드"/>
<cr:set var="product2" value="모니터"/>
<%-- <cr:if test="${product eq '그래픽카드'}"></cr:if> --%>
<%-- <cr:if test="${product ne '그래픽카드'}"></cr:if> --%>
<%-- <cr:if test="${product == '그래픽카드'}"></cr:if> --%>

<cr:if test="${product == '그래픽카드' || product == '모니터'}">
가격미정 
</cr:if>
<br><br><br><br><br>

<!-- choose, when, otherwise -->
<cr:set var="agree" value="Y"/>
<cr:choose>
<cr:when test="${agree == 'Y' }">	<!-- if -->
약관에 동의하였음
</cr:when>
<cr:when test="${agree == 'N' }">	<!-- else if -->
동의 안함
</cr:when>
<!-- Y, N  외에 다른 값이 날라올 경우 otherwise가 작동됨 -->
<cr:otherwise>						<!-- else -->
해당 약관정보를 확인 못함
</cr:otherwise>
</cr:choose>

</body>
</html>