<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>transfer.jsp</title>
</head>
<body>
	<h2>Transfer</h2><br>
	<form action="search.do" method="post">
		Receiver ID : <input type="text" name="id" /> <br>
		<input type="submit" value="SEARCH" />
	</form>
	
	<% 
		String result = (String)request.getAttribute("msg");
		if(result != null && result.equals("true")){
	%>
		<form action="transfer.do" method="post">
			Receiver ID : <input type="text" readonly name="rId" value="${rId }"/> <br>
			MONEY : <input type="text" name="money" /> <br>
			<input type="submit" value="TRANSFER" />
		</form>
	
	<%} else if(result != null && result.equals("false")){ 
			out.print("No Receiver ID");
	}
	%>
</body>
</html>