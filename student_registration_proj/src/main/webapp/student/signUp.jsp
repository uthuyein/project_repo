<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="signUp" value="/student/signUp"></c:url>
<c:url var="payment" value="/student/addPayment"></c:url>
<c:url var="account" value="/student/addAccount"></c:url>

<%@page import="com.mkt.ym.entity.type.*"%>

<jsp:include page="${head }"></jsp:include>
<div class="row">
	<nav id="navbar-example"
		class="navbar navbar-light bg-light px-3 sticky-sm-top">
		<ul class="nav vh-2">
			<li class="nav-item"><a
				class="nav-link primary-text-color fs-sm" href="${register }"></a></li>
		</ul>
	</nav>
	<div class="row justify-content-center p-5 message-target">
		<div class="col col-md-5">
			<c:if test="${null ne message }">
				<span class="mess"> <label
					class="alert d-flex p-2 ${message.getColor() } }">${message.message}</label>
				</span>
			</c:if>
			<div class="card">
				<form action="${signUp }" method="post" class="needs-validation"
					novalidate>
					<div class="card-body ">
						<div class="card-header  mb-4 primary-header-text-color">
							<i class="bi bi-person-fill"></i> SignUp Form
						</div>

						<div class="mb-3">
							<label for="stuName" class="form-label primary-text-color">Student's
								Name</label> <input type="text" class="form-control" id="stuName"
								name="stuName" value="andrew" aria-describedby="stuName"
								required="required">
							<div class="invalid-feedback">Please re-enter student name
								!</div>
						</div>
						<div class="mb-3">
							<label for="dob" class="form-label primary-text-color">Date
								Of Birth</label> <input type="date" class="form-control" id="dob"
								name="dob" aria-describedby="stuName " value="1991-05-22"
								required>
							<div class="invalid-feedback">Please re-enter student's
								date of birth !</div>
						</div>
						<div class="mb-3">
							<label for="nrc" class="form-label primary-text-color">National
								Registration Card(NRC)</label>
							<div class="row">
								<div class="col col-sm-2">
									<select id="code" name="code" class="form-select"
										onchange="fetchNrc('code','codeName')">
									<option selected="selected" disabled="disabled" value="">---</option>
										<c:forEach var="n" items="${nrcCodes }">
											<option>${n}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col col-sm-4">
									<select id="codeName" name="codeName" class="form-select">
									</select>
								</div>
								<div class="col ">
									<input type="text" class="form-control" id="codeNum"
										name="codeNum" required="required">
									<div class="invalid-feedback">Please re-enter student's
										nrc !</div>
								</div>
							</div>
						</div>
						<div class="mb-3">
							<label for="fNrc" class="form-label primary-text-color">Father's
								NRC</label>
							<div class="row">
								<div class="col col-sm-2">
									<select id="fcode" name="fcode" class="form-select"
										onchange="fetchNrc('fcode','fcodeName')">
										<option selected="selected" disabled="disabled" value="">---</option>
										<c:forEach var="n" items="${nrcCodes }">
											<option>${n}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col col-sm-4">
									<select id="fcodeName" name="fcodeName" class="form-select">
									</select>
								</div>
								<div class="col ">
									<input type="text" class="form-control" name="fcodeNum"
										required="required">
									<div class="invalid-feedback">Please re-enter father's
										nrc !</div>
								</div>
							</div>
						</div>
						<div class="mb-3">
							<label for="mNrc" class="form-label primary-text-color">Mother's
								NRC</label>
							<div class="row">
								<div class="col col-sm-2">
									<select id="mcode" name="mcode" class="form-select"
										onchange="fetchNrc('mcode','mcodeName')">
										<option selected="selected" disabled="disabled" value="">---</option>
										<c:forEach var="n" items="${nrcCodes }">
											<option>${n}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col col-sm-4">
									<select id="mcodeName" name="mcodeName" class="form-select">
									</select>
								</div>
								<div class="col ">
									<input type="text" class="form-control" name="mcodeNum"
										required="required">
									<div class="invalid-feedback">Please re-enter mother's
										nrc !</div>
								</div>
							</div>
						</div>
						<div class="mb-3">
							<label for="schEnroll" class="form-label primary-text-color">School
								Grade 12 Enroll Number</label> <input type="text" class="form-control"
								id="schEnroll" name="schEnroll" value="10"
								aria-describedby="schEnroll rollfeedback" required>
							<div id="rollfeedback" class="invalid-feedback">Please
								re-enter school roll number !</div>
						</div>
						<div class="mb-3">
							<label for="schMarks" class="form-label primary-text-color">School
								Grade 12 Marks </label> <input type="text" class="form-control"
								id="schMarks" name="schMarks" value="405"
								aria-describedby="schMarks markfeedback" required>
							<div id="markfeedback" class="invalid-feedback">Please
								re-enter school marks birth !</div>
						</div>
						<div class="nav justify-content-end">
							<button type="submit" class="btn primary-color">Confirm</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<jsp:include page="${foot }"></jsp:include>
