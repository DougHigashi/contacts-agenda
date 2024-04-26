<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="images/favicon.png">
<link rel="stylesheet" href="style.css">
<title>Edit contact</title>
</head>
<body>
	<h1>Edit contact</h1>
	<form name="frmContact" action="insert">
		<table>
			<tr>
				<td><input type="text" name="idcon" placeholder="ID" value="<%out.println(request.getAttribute("idcon"));%>" class="inputBox" readonly></td>
			</tr>
			<tr>
				<td><input type="text" name="cttName" placeholder="Name" value="<%out.println(request.getAttribute("name"));%>" class="inputBox"></td>
			</tr>
			<tr>
				<td><input type="text" name="phone" placeholder="Phone" value="<%out.println(request.getAttribute("phone"));%>" class="phoneBox"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" placeholder="E-mail" value="<%out.println(request.getAttribute("email"));%>" class="inputBox"></td>
			</tr>
		</table>
		<input type="button" value="Edit" class="button"  onclick="validate()">
	</form>
</body>
</html>