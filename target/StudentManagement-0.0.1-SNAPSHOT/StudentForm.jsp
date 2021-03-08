<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/newStudent.css" />
<title>Add new Student</title>
</head>
<body>

	<div class="survey-page">
		<!-- Thêm phần tiêu đề trang -->
		<jsp:include page="layout/header.jsp"></jsp:include>
		<h1 id="title">Student Management</h1>
		<div id="form-container">
			<p id="description">Input student information to add or edit a
				student</p>
			<h2>
				<!-- <a href="student">List All Student</a> &nbsp;&nbsp;&nbsp; -->
				<!-- <a href="studentt?action=new">Add New Student</a> -->
			</h2>
			<c:if test="${student != null}">
				<form action="student?action=update" method="post">
			</c:if>
			<c:if test="${student == null}">
				<form action="student?action=insert" method="post">
			</c:if>
			<c:if test="${student != null}">
				<input type="hidden" name="id" class="input-field"
					value="<c:out value='${student.id}'/>" />
			</c:if>
			<div class="formRow">
				<label id="name-label" class="label-cls" for="name">*
					FirstName: </label>
				<div class="input-col">
					<input autofocus type="text" name="firstname" id="name"
						class="input-field" value="<c:out value='${student.firstname}'/>"
						placeholder="Enter Firstname" required>
				</div>
			</div>
			<div class="formRow">
				<label id="email-label" class="label-cls" for="name">*
					LastName: </label>
				<div class="input-col">
					<input type="text" name="lastname" id="name" class="input-field"
						value="<c:out value='${student.lastname}'/>" required
						placeholder="Enter Lastname">
				</div>
				</div>
			<div class="formRow">
				<label id="email-label" class="label-cls" for="name">*
					Gender: </label>
				<div class="input-col">
					<input type="radio" id="male" name="gender" value="male"> <label for="male">Male</label>
					<input type="radio" id="female" name="gender" value="female"> <label for="female">Female</label>
					<input type="radio" id="other" name="gender" value="other"> <label for="other">Other</label>
			</div>
			</div>
			<div class="formRow">
				<label id="number-label" class="label-cls" for="age">*
					BirthDay: </label>
				<div class="input-col">
					<input type="date" name="birthday" id="email" class="input-field"
						value="<c:out value='${student.birthday}'/>"
						placeholder="Enter Birthday">
				</div>
			</div>
			<div class="formRow">
				<label id="email-label" class="label-cls" for="name">*
					Address: </label>
				<div class="input-col">
					<input type="text" name="address" id="name" class="input-field"
						value="<c:out value='${student.address}'/>" required
						placeholder="Enter Address">
				</div>
			</div>
			<button id="submit" type="submit">Submit</button>
			<button id="submit" type="submit"
				onclick="window.location.href='student'">Cancel</button>
			</form>
		</div>
	</div>
	</div>

</body>
</html>