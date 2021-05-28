<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>withdrawalResult.jsp</title>
</head>
<body>
	<h2>Withdrawal Result</h2>

	<%
		String result = (String)request.getAttribute("result");
		if(result == null)
		{
	%>
			<h3>ID : ${id }</h3><br>
			<h3>Withdrawal Money : ${money }</h3><br>
			<h3>Balance : ${tMoney }</h3>
	<%	}else{
		out.print(result);
		}
	%>
	<p></p>
	<%@ include file="homeMenu.jsp"%>
</body>
</html>