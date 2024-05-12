<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<div class="card col-md-5">
	<div class="card-header mt-2">
		<i class="bi bi-person-fill"></i> Add Student Information
	</div>
	<div class="card-body ">
		<form action="/student/newStudent" method="post">
			<div class="mb-3">
				<label for="enroll" class="form-label">University Enroll
					Number</label> <input type="text" class="form-control" id="enroll">
			</div>
			<div class="mb-3">
				<label for="stuName" class="form-label">Student's Name</label> <input
					type="text" class="form-control" id="stuName">
			</div>
			<div class="mb-3">
				<label for="religion" class="form-label">Nation Religion </label> <input
					type="text" class="form-control" id="religion">
			</div>
			<div class="mb-3">
				<label for="dob" class="form-label">Date Of Birth</label> <input
					type="date" class="form-control" id="dob">
			</div>
			<div class="mb-3">
				<label for="nrc" class="form-label">National Identity
					Card(NRC)</label> <input type="text" class="form-control" id="nrc">
			</div>
			<div class="mb-3">
				<label for="fNrc" class="form-label">Father NRC</label> <input
					type="text" class="form-control" id="fNrc">
			</div>
			<div class="mb-3">
				<label for="mNrc" class="form-label">Mother NRC</label> <input
					type="text" class="form-control" id="mNrc">
			</div>
			<div class="mb-3">
				<label for="enroll" class="form-label">School Grade 12 Enroll
					Number</label> <input type="text" class="form-control" id="enroll">
			</div>
			<div class="mb-3">
				<label for="enroll" class="form-label">School Grade 12 Marks
					</label> <input type="text" class="form-control" id="enroll">
			</div>

			<button type="submit" class="btn btn-primary">Confirm</button>
		</form>
	</div>

</div>

<jsp:include page="${foot }"></jsp:include>