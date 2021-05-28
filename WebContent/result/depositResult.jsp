<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>depositResult.jsp</title>
</head>
<body>
	<h2>Deposit Result</h2><br>
	<h3>ID : ${id }</h3><br>
	<h3>Deposit Amount : ${money }</h3><br>
	<h3>Balance : ${tMoney }</h3>
	<p>
	<%@ include file="homeMenu.jsp" %>
</body>
</html>