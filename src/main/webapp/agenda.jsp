<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.JavaBeans" %>
    <%@ page import="java.util.ArrayList" %>
<%
	ArrayList<JavaBeans> list = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Contacts agenda</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Contacts agenda</h1>
	<a href="novo.html" class="button">New contact</a>
	
	<table id="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Phone</th>
				<th>E-mail</th>
				<th>Options</th>
			</tr>
		</thead>
		<tbody>
		<% for (int i = 0; i < list.size(); i++) { %>
			<tr>
				<td><%=list.get(i).getId()%></td>
				<td><%=list.get(i).getName()%></td>
				<td><%=list.get(i).getPhone()%></td>
				<td><%=list.get(i).getEmail()%></td>
				<td><a href="select?idcon=<%=list.get(i).getId()%>" class="button">Edit</a>
					<a href="javascript: confirmDelete(<%=list.get(i).getId()%>)" class="deleteButton">Delete</a>
				</td>
			</tr>
		<%} %>
		
		</tbody>
	</table>
	
</body>
<script src="scripts/confirm.js"></script>
</html>