<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<!-- Option 1: Include in HTML -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<title>Student Registration</title>
</head>
<%@ page import="com.mkt.ym.entity.type.*"%>

<c:url var="style" value="/style.css"></c:url>
<c:url var="register" value="/student/register"></c:url>
<c:url var="stuUniInfo" value="/student/stuUniversityInfo"></c:url>
<c:url var="listAccount" value="/admin/accountList"></c:url>
<c:url var="listStudent" value="/admin/studentList"></c:url>
<c:url var="listStudentfrmUni" value="/admin/studentListfrmUni"></c:url>

<c:url var="home" value="/index.jsp"></c:url>
<c:url var="login" value="/student/login"></c:url>
<c:url var="logout" value="/student/logout"></c:url>

<link rel="stylesheet" href="${style }" />

<c:set var="role" value="<%=Role.ADMIN%>"></c:set>

<body>

	<nav class="navbar navbar-expand-md navbar-light primary-color">
		<div class="container-fluid">
			<a class="navbar-brand text-white" href="${home }"> <img
				src="/images/uni_images/UTYCC.png" alt="" width="40" height="40"
				class="rounded-circle"> &nbsp;UTYCC
			</a>
			<button class="navbar-toggler " type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>


			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav w-75 mb-2">

					<c:if test="${null ne account }">
						 <li class="nav-item "><a class="nav-link text-white "
							href="${stuUniInfo }?id=${account.student.id}"><i class="bi bi-person-lines-fill"></i>&nbsp;Student
								Information </a></li>
						<c:if test="${account.role eq role}">
							<li class="nav-item "><a class="nav-link text-white"
								href="${listStudent }"><i class="bi bi-person-fill"></i>&nbsp;Student</a></li>

							<li class="nav-item "><a class="nav-link text-white"
								href="${listStudentfrmUni }"><i class="bi bi-building"></i>&nbsp;University</a></li>
							<li class="nav-item "><a class="nav-link text-white"
								href="${listAccount }"><i class="bi bi-cone-striped"></i>&nbsp;Account</a></li>
						</c:if>
					</c:if>

					<li class="nav-item "><a class="nav-link text-white" href="#"><i
							class="bi bi-question-circle-fill"></i>&nbsp;About</a></li>
				</ul>

				<div class="nav-item ">
					<a class="nav-link text-white" href="${register }"><i
						class="bi bi-pencil-square"></i>&nbsp;Registration</a>
				</div>
				<c:choose>
					<c:when test="${account ne null }">
						<div class="nav-item ">
							<c:set
								value="${null ne account.student ? account.student.image:'uni_images/UTYCC.png'}"
								var="img"></c:set>
							<a class="nav-link text-white" data-bs-toggle="modal"
								data-bs-target="#logoutModal"><img src="/images/${img }"
								alt="" width="30" height="30" class="rounded-circle">&nbsp;Logout
							</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="nav-item ">
							<a class="nav-link text-white" data-bs-toggle="modal"
								data-bs-target="#loginModal"><i class="bi bi-person-circle"></i>&nbsp;Login
							</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>

	<!-- Login Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<form class="form" method="post" action="/student/login">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title primary-header-text-color"
							id="loginModalLabel">
							<i class="bi bi-person-circle"></i>&nbsp;Login Form
						</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="username" class="form-label primary-text-color">User
								Name</label> <input type="text" class="form-control" id="username"
								name="username" value="admin">
						</div>
						<div class="mb-3">
							<label for="password" class="form-label primary-text-color">Password</label>
							<input type="password" class="form-control" id="password"
								name="password" value="admin">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Cancel</button>
						<button type="submit" class="btn primary-color">Login</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- Alert Modal -->
	<div class="modal fade " id="logoutModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title primary-header-text-color">
						<i class="bi bi-info-circle-fill"></i> Warning
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Do you really want to logout !</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<a type="button" class="btn primary-color" href="${logout }">Save
						changes</a>
				</div>
			</div>
		</div>
	</div>