<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>


<div class="card col-md-5">
	<div class="card-header mt-2 text-primary">
		<i class="bi bi-person-fill"></i> Add Account
	</div>
	<div class="card-body ">
		<form action="/admin/addParent" method="post">

			<div class="mb-3">
				<label for="stuName" class="form-label text-primary">Student Name</label> <input
					type="text" class="form-control" id="stuName" name="stuName" disabled="disabled">
			</div>
			<div class="mb-3">
				<label for="username" class="form-label text-primary">User Name</label> <input
					type="text" class="form-control" id="username" name="username">
			</div>
			<div class="mb-3">
				<label for="password" class="form-label text-primary">Password</label> <input
					type="password" class="form-control" id="password" name="password">
			</div>
			<div class="mb-3">
				<label for="confirm" class="form-label text-primary ">Confirm Password</label> <input
					type="password" class="form-control" id="confirm" name="confirm">
			</div>

			<button type="submit" class="btn btn-primary">Save Account</button>
		</form>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>