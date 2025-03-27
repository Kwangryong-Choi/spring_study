<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cms 상담신청 내역 확인</title>
</head>
<body>
제목 : ${csubject}<br>
상담자명 : ${cuser}<br>
상담 목적 : ${cate}
<!-- 
disabled : 해당 내용을 더 이상 수정 및 변경을 못하게 하는 속성  =>  checkbox, radio, input 에서 사용
disabled 의 단점은 name 속성이 있어서 Back-end에게 값을 전송해야 할 때 disabled 처리를 하면 name의 value값을 전송하지 못함

contains : 해당 단어가 포함되어 있는지를 확인하는 함수
 -->
<input type="checkbox" value="cms1" disabled <cr:if test="${fn:contains(cate,'cms1')}">checked</cr:if> > 심리상담
<input type="checkbox" value="cms2" disabled <cr:if test="${fn:contains(cate,'cms2')}">checked</cr:if> > 학교생활
<input type="checkbox" value="cms3" disabled <cr:if test="${fn:contains(cate,'cms3')}">checked</cr:if> > 가정
<input type="checkbox" value="cms4" disabled <cr:if test="${fn:contains(cate,'cms4')}">checked</cr:if> > 학업 및 진로
<input type="checkbox" value="cms5" disabled <cr:if test="${fn:contains(cate,'cms5')}">checked</cr:if> > 대인관계
</body>
</html>