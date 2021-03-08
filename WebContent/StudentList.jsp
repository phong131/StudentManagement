<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Student</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/StudentList.css" />
</head>
<body>


	<div class="survey-page">
		<!-- Thêm phần tiêu đề trang -->
		<jsp:include page="layout/header.jsp"></jsp:include>
		<h1 id="title">Student Management</h1>
		<div id="form-container">
			<h1 id="title">* * * * *</h1>
			<h4>
				<!--  <a href="student?action=new">Add New Student</a> -->
				<button id="submit" type="submit" onclick="window.location.href='student?action=new'">NewStudent</button>

			</h4>
			<table id="customers">
				<tr>
					<th>Id</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Gender</th>
					<th>BirthDay</th>
					<th>Address</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="student" items="${listStudent}">
					<tr>
						<td><c:out value="${student.id}" /></td>
						<td><c:out value="${student.firstname}" /></td>
						<td><c:out value="${student.lastname}" /></td>
						<td><c:out value="${student.gender}" /></td>
						<td><c:out value="${student.birthday}" /></td>
						<td><c:out value="${student.address}" /></td>
						<td><a
							href="student?action=edit&id=<c:out value='${student.id}' />">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="student?action=delete&id=<c:out value='${student.id}' />">Delete</a>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>