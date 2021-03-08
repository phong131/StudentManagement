<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/newStudent.css" />
<title>Login</title>
</head>
<body>
	<div class="survey-page">
		<!-- Thêm phần tiêu đề trang -->
		<jsp:include page="header.jsp"></jsp:include>
		<h1 id="title">Login Page</h1>
		<div id="form-container">

			<p style="color: red;">${errorString}</p>


			<form method="POST">
				<table border="0">
					<tr>
						<td>User Name</td>
						<td><input type="text" name="userName"
							value="${user.userName}" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="text" name="password"
							value="${user.password}" /></td>
					</tr>
					<tr>
						<td>Remember me</td>
						<td><input type="checkbox" name="rememberMe" value="Y" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Submit" /> <a
							href="${pageContext.request.contextPath}/">Cancel</a></td>
					</tr>
				</table>
			</form>

			<p style="color: blue;">User Name: tom, password: tom001 or
				jerry/jerry001</p>
		</div>
	</div>
</body>
</html>