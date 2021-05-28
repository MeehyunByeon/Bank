<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>transferResult.jsp</title>
</head>
<body>
	<%
		String result = (String)request.getAttribute("result");
		if(result == null)
		{
	%>
			${id } -> ${rId } :  ${money } | ${tMoney }
	<%	}else{
		out.print(result);
		}
	%>
	<p></p>
	<%@ include file="homeMenu.jsp"%>
</body>
</html>