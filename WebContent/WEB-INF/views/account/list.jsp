<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple Spring</title>

<script src="resources/js/jquery.js"></script>
<script type="text/javascript">
	
	function itsWorking(data){
		alert("you paid your account");
	}
	
	function payNow(id) {
		//$.post("payAccount?id=" + id, itsWorking);
		$.post("payAccount", {'id' : id}, function() {
			  $("#account_"+id).html("Paid");
			});
	}
	
</script>
</head>
<body>
	<table>
		<tr>
			<th>Code</th>
			<th>Description</th>
			<th>Value</th>
			<th>Type</th>
			<th>Pay</th>
			<th>Due date</th>
			<th>Actions</th>
		</tr>

		<c:forEach items="${allAccounts }" var="account">
			<tr>
				<td>${account.id }</td>
				<td>${account.description }</td>
				<td>${account.value }</td>
				<td>${account.type }</td>
				<td  id="account_${account.id }">
				 <c:if test="${account.paid eq false }">
			       Do not paid
			     </c:if>
			     <c:if test="${account.paid eq true }">
			       Paid
			     </c:if>
			    </td>
				<td><fmt:formatDate value="${account.payDay.time }"
						pattern="dd/MM/yyyy" /></td>
				<td><a href="removeAccount?id=${account.id }">Remove</a> 
				| 
				<c:if
						test="${account.paid eq false}">
						<a href="#" onClick="payNow(${account.id})">Pay</a>
				</c:if></td>
			</tr>
		</c:forEach>
		
		<a href="logout"> Logout </a>


	</table>
</body>
</html>