<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<div class="row justify-content-center p-5">
	<div class="card col-md-5">
		<div class="card-header mt-2 text-primary">
			<i class="bi bi-person-fill"></i> Add Student Information
		</div>
		<div class="card-body ">
			<form action="/student/chkStudent" method="post">
				<div class="mb-3">
					<label for="uniEnroll" class="form-label text-primary">University
						Enroll Number</label> <input type="text" id="uniEnroll"
						class="form-control" name="uniEnroll">
				</div>
				<div class="mb-3">
					<label for="stuName" class="form-label text-primary">Student's
						Name</label> <input type="text" class="form-control" id="stuName"
						name="stuName">
				</div>
				
				<div class="mb-3">
					<label for="dob" class="form-label text-primary">Date Of
						Birth</label> <input type="date" class="form-control" id="dob" name="dob">
				</div>
				<div class="mb-3">
					<label for="nrc" class="form-label text-primary">National
						Identity Card(NRC)</label> <input type="text" class="form-control"
						id="nrc" name="nrc">
				</div>
				<div class="mb-3">
					<label for="fNrc" class="form-label text-primary">Father
						NRC</label> <input type="text" class="form-control" id="fNrc" name="fNrc">
				</div>
				<div class="mb-3">
					<label for="mNrc" class="form-label text-primary">Mother
						NRC</label> <input type="text" class="form-control" id="mNrc" name="mNrc">
				</div>
				<div class="mb-3">
					<label for="schEnroll" class="form-label text-primary">School
						Grade 12 Enroll Number</label> <input type="text" class="form-control"
						id="schEnroll" name="schEnroll">
				</div>
				<div class="mb-3">
					<label for="schMarks" class="form-label text-primary">School
						Grade 12 Marks </label> <input type="text" class="form-control"
						id="schMarks" name="schMarks">
				</div>

				<button type="submit" class="btn btn-primary">Continue >>></button>
			</form>
		</div>

	</div>
</div>
<jsp:include page="${foot }"></jsp:include>