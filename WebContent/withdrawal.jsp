<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>withdrawal.jsp</title>
</head>
<body>
	Total Money : ${money }
	<p>
	<form action="withdrawal.do" method="put">
		Money : <input type="text" name="money" /> <br>
		<input type="submit" value="WITHDRAWAL" />
	</form>
</body>
</html>