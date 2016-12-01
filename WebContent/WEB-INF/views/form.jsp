<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple Spring</title>
</head>
<body>
	<h1>Add Accounts</h1>

	<form action="addAccount" method="post">
		Description:<br />
		<textarea name="description" rows="5" cols="100"></textarea>
		<form:errors path="account.description" />
		<br /> Value: <input type="text" name="value" /> <br /> 
		<select name="type">
			<option value="ENTRY">Entry</option>
			<option value="EXIT">Exit</option>
		</select> <br />
		<br /> <input type="submit" value="add">

	</form>

</body>
</html>