<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="register" value="/student/register"></c:url>
<c:url var="payment" value="/student/payment"></c:url>
<c:url var="account" value="/student/account"></c:url>

<%@page import="com.mkt.ym.entity.type.*"%>

<jsp:include page="${head }"></jsp:include>
<div class="row">
	<nav id="navbar-example"
		class="navbar navbar-light bg-light px-3 sticky-sm-top">
		<ul class="nav">
			<li class="nav-item"><a
				class="nav-link primary-text-color fs-sm" href="${register }">Register</a></li>
			<li class="nav-item "hidden="true"><a class="nav-link primary-text-color"
				href="${payment }" >Payment</a></li>
			<li class="nav-item" hidden="true"><a class="nav-link primary-text-color"
				href="${account }">Account</a></li>
		</ul>
	</nav>
	<div class="row justify-content-center p-5 ">
		<div class="col col-md-6 ">
			<form action="${register }" method="post">
				<div class="card-body ">

					<div id="registerId"
						class="card-header  mb-4 primary-header-text-color">
						<i class="bi bi-person-fill"></i> Registration Form
					</div>

					<div class="row">
						<div class="col">
							<div class="mb-3">
								<label for="openYear" class="form-label primary-text-color">University
									Open Year</label> <input type="text" id="openYear" class="form-control"
									name="openYear" value="2022">
							</div>
						</div>
						<div class="col">
							<div class="mb-3">
								<label for="uniYear" class="form-label primary-text-color">University
									Year</label> <select class="form-select" name="uniYear" id="uniYear">
									<c:set var="years" value="<%=UniYear.values()%>"></c:set>
									<option selected>FIRST</option>
									<c:forEach var="y" items="${years }">
										<option>${y.name()}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<%-- <div class="col">
							<div class="mb-3">
								<label for="major" class="form-label primary-text-color">Major
									Name</label> <select id="major" class="form-select" name="major">
									<c:set var="majors" value="<%=Major.values()%>"></c:set>
									<option selected>---</option>
									<c:forEach var="m" items="${majors }">
										<option>${m.name()}</option>
									</c:forEach>
								</select>
							</div>
						</div> --%>
					</div>
					<div class="mb-3">
						<label for="stuName" class="form-label primary-text-color">Student's
							Name</label> <input type="text" class="form-control" id="stuName"
							name="stuName" value="andrew">
					</div>
					<div class="mb-3">
						<label for="dob" class="form-label primary-text-color">Date
							Of Birth</label> <input type="date" class="form-control" id="dob"
							name="dob" value="1991-05-22">
					</div>
					<div class="mb-3">
						<label for="nrc" class="form-label primary-text-color">National
							Identity Card(NRC)</label> <input type="text" class="form-control"
							id="nrc" name="nrc" value="9/pamana(n)765642">
					</div>
					<div class="mb-3">
						<label for="fNrc" class="form-label primary-text-color">Father
							NRC</label> <input type="text" class="form-control" id="fNrc" name="fNrc" value="5/pakaka(n)982342">
					</div>
					<div class="mb-3">
						<label for="mNrc" class="form-label primary-text-color">Mother
							NRC</label> <input type="text" class="form-control" id="mNrc" name="mNrc" value="6/yatata(F)0942342">
					</div>
					<div class="mb-3">
						<label for="schEnroll" class="form-label primary-text-color">School
							Grade 12 Enroll Number</label> <input type="text" class="form-control"
							id="schEnroll" name="schEnroll" value="10">
					</div>
					<div class="mb-3">
						<label for="schMarks" class="form-label primary-text-color">School
							Grade 12 Marks </label> <input type="text" class="form-control"
							id="schMarks" name="schMarks" value="405">
					</div>
					<div class="nav justify-content-end">
						<button type="submit" class="btn primary-color">
							Register</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>

<!-- <script>
	var scrollSpy = new bootstrap.ScrollSpy(document.body, {
		target : '#navbar-example'
	})
</script> -->