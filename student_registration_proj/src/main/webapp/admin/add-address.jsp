<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<div class="row justify-content-center p-5">
	<div class="card col-md-5">
		<div class="card-header mt-2 text-primary">
			<i class="bi bi-person-fill"></i> Add Address Information
		</div>
		<div class="card-body ">
			<form action="/admin/addAddress" method="post">
				<div class="mb-3">
					<label for="fName" class="form-label text-primary">City</label> <input
						type="text" class="form-control" id="city" name="city">
				</div>
				<div class="mb-3">
					<label for="township" class="form-label text-primary">Township</label>
					<input type="text" class="form-control" id="township"
						name="township">
				</div>
				<div class="mb-3">
					<label for="street" class="form-label text-primary">Street</label>
					<input type="text" class="form-control" id="street" name="street">
				</div>

				<button type="submit" class="btn btn-primary">Save Student
					info</button>
			</form>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>