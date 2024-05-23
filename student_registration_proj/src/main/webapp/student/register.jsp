<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<%@page import="com.mkt.ym.entity.type.*"%>

<jsp:include page="${head }"></jsp:include>
<div class="row" data-bs-spy="scroll" data-bs-target="#navbar-example"
	style="position: relative">

	<nav id="navbar-example"
		class="navbar navbar-light bg-light px-3 sticky-sm-top">
		<ul class="nav nav-pills">
			<li class="nav-item"><a
				class="nav-link primary-text-color fs-sm" href="#registerId">Register</a></li>
			<li class="nav-item "><a class="nav-link primary-text-color"
				href="#paymentId">Payment</a></li>
			<li class="nav-item"><a class="nav-link primary-text-color"
				href="#accountId">Account</a></li>
		</ul>
	</nav>
	<div class="row justify-content-center  p-5 ">
		<div data-bs-spy="scroll" data-bs-target="#navbar-example"
			data-bs-offset="0" class="col col-md-6 scrollspy-example"
			tabindex="0">
			<div id="registerId"
				class="card-header mt-2 primary-header-text-color">
				<i class="bi bi-person-fill"></i> Registration Form
			</div>
			<div class="card-body ">
				<form action="/student/chkStudent" method="post">
					<div class="mb-3">
						<label for="uniEnroll" class="form-label primary-text-color">University
							Enroll Number</label> <input type="text" id="uniEnroll"
							class="form-control" name="uniEnroll">
					</div>
					<div class="mb-3">
						<label for="stuName" class="form-label primary-text-color">Student's
							Name</label> <input type="text" class="form-control" id="stuName"
							name="stuName">
					</div>

					<div class="mb-3">
						<label for="dob" class="form-label primary-text-color">Date
							Of Birth</label> <input type="date" class="form-control" id="dob"
							name="dob">
					</div>
					<div class="mb-3">
						<label for="nrc" class="form-label primary-text-color">National
							Identity Card(NRC)</label> <input type="text" class="form-control"
							id="nrc" name="nrc">
					</div>
					<div class="mb-3">
						<label for="fNrc" class="form-label primary-text-color">Father
							NRC</label> <input type="text" class="form-control" id="fNrc" name="fNrc">
					</div>
					<div class="mb-3">
						<label for="mNrc" class="form-label primary-text-color">Mother
							NRC</label> <input type="text" class="form-control" id="mNrc" name="mNrc">
					</div>
					<div class="mb-3">
						<label for="schEnroll" class="form-label primary-text-color">School
							Grade 12 Enroll Number</label> <input type="text" class="form-control"
							id="schEnroll" name="schEnroll">
					</div>
					<div class="mb-3">
						<label for="schMarks" class="form-label primary-text-color">School
							Grade 12 Marks </label> <input type="text" class="form-control"
							id="schMarks" name="schMarks">
					</div>

					<button type="submit" class="btn primary-color">Confirm
						Student</button>
				</form>
			</div>

			<div id="paymentId"
				class="card-header mt-2 primary-header-text-color">
				<i class="bi bi-wallet "></i> &nbsp;Add Payment Information
			</div>
			<div class="card-body ">
				<form action="/student/addPayment" method="post">

					<div class="mb-3">
						<label for="fName" class="form-label primary-text-color">Payment
							Type</label> <select class="form-select">
							<option selected>---</option>
							<option value="1">KBZ Pay</option>
							<option value="2">Aya Pay</option>
							<option value="3">Wave Pay</option>
						</select>
					</div>
					<div class="mb-3">
						<label for="tName" class="form-label primary-text-color">Transfer
							From</label> <input type="text" class="form-control" id="tName">
					</div>
					<div class="mb-3">
						<label for="tNum" class="form-label primary-text-color">Transaction
							No.</label> <input type="text" class="form-control" id="tNum" name="tNum">
					</div>
					<div class="mb-3">
						<label for="amount" class="form-label primary-text-color">Amount</label>
						<input type="text" class="form-control" id="amount">
					</div>
					<div class="mb-3">
						<label for="note" class="form-label primary-text-color">Note</label>
						<input type="text" class="form-control" id="note">
					</div>

					<button type="submit" class="btn primary-color">Confirm
						Payment</button>
				</form>
			</div>
			<div id="accountId"
				class="card-header mt-2 primary-header-text-color">
				<i class="bi bi-person-fill "></i>&nbsp; Add Account
			</div>
			<div class="card-body ">
				<form action="${addAccount }" method="post">
					<div class="mb-3">
						<label for="role" class="form-label primary-text-color">Account
							Role</label> <select id="role" class="form-select" name="role">
							<c:set var="roles" value="<%=Role.values()%>"></c:set>
							<option selected>---</option>
							<c:forEach var="role" items="${roles }">
								<option>${ role.name()}</option>
							</c:forEach>
						</select>
					</div>
					<div class="row">
						<div class="col">
							<div class="mb-3">
								<label for="fName" class="form-label primary-text-color">University
									Year</label> <select class="form-select" name="year">
									<c:set var="years" value="<%=UniYear.values()%>"></c:set>
									<option selected>---</option>

									<c:forEach var="y" items="${years }">
										<option>${y.name()}</option>

									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col">
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
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="mb-3">
								<label for="uniRollNum" class="form-label primary-text-color">University
									Roll Number </label> <input type="text" class="form-control"
									id="uniRollNum" name="uniRollNum">
							</div>
						</div>
						<div class="col">
							<div class="mb-3">
								<label for="username" class="form-label primary-text-color">Student
									Name</label> <input type="text" class="form-control" id="username"
									name="username">
							</div>
						</div>
					</div>
					<div class="mb-3">
						<label for="username" class="form-label primary-text-color">Account
							User Name</label> <input type="text" class="form-control" id="username"
							name="username">
					</div>
					<div class="mb-3">
						<label for="password" class="form-label primary-text-color">Password</label>
						<input type="password" class="form-control" id="password"
							name="password">
					</div>

					<button type="submit" class="btn primary-color">Save
						Account</button>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>

<script>
	var scrollSpy = new bootstrap.ScrollSpy(document.body, {
		target : '#navbar-example'
	})
</script>