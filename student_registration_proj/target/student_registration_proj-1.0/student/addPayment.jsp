<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="register" value="/student/register"></c:url>
<c:url var="payment" value="/student/addPayment"></c:url>
<c:url var="acc" value="/student/addAccount"></c:url>
<c:url var="stuDetail" value="/student/detailStudent"></c:url>


<%@page import="com.mkt.ym.entity.type.*"%>
<c:set var="majors" value="<%=Major.values()%>"></c:set>
<c:set var="uniYears" value="<%=UniYear.values()%>"></c:set>
	<c:set var="pays" value="<%=PaymentType.values()%>"></c:set>
							


<jsp:include page="${head }"></jsp:include>
<div class="row">

	<nav id="navbar-example"
		class="navbar navbar-light bg-light px-3 sticky-sm-top">
		<ul class="nav">
			<li class="nav-item"><a
				class="nav-link primary-text-color fs-sm"
				href="${stuDetail }?id=${uniInfoDto.stuId()}">Student</a></li>
			<li class="nav-item "><a class="nav-link primary-text-color"
				href="${payment }">Payment</a></li>
			<li class="nav-item"><a class="nav-link primary-text-color"
				href="${acc }">Account</a></li>
		</ul>
	</nav>
	<div class="row justify-content-center  p-5 message-target">
		<div class="col col-md-5 ">
			<c:if test="${null ne message }">
				<span class="mess"> <label
					class="alert d-flex p-2 ${message.getColor() } }">${message.message}</label>
				</span>
			</c:if>
			<div class="card">

				<form action="${payment }" method="post" class="needs-validation"
					novalidate>
					<div class="card-body ">

						<div class="card-header mt-3 mb-4 primary-header-text-color">
							<i class="bi bi-wallet "></i> &nbsp;Add Payment Information
						</div>
						<div class="row">
							<div class="col col-md-4">
								<label for="payment" class="form-label primary-text-color">Open
									Year </label> <select class="form-select" name="openYear"
									required="required">
									<option selected disabled="disabled" value="">---</option>
									<c:forEach var="year" items="${openYears }">
										<option>${year}</option>
									</c:forEach>
								</select>
								<div class="invalid-feedback">Please select university open year  !</div>

							</div>
							<div class="col col-md-4">
							<label for="payment" class="form-label primary-text-color">University
								Year</label> <select class="form-select" name="uniYear"
								required="required">
								<option selected disabled="disabled" value="">---</option>
								<c:forEach var="u" items="${uniYears }">
									<option>${u.name()}</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback">Please select university year !</div>

						</div>
						<div class="col col-md-4">
							<label for="payment" class="form-label primary-text-color">Major
								</label> <select class="form-select" name="major"
								required="required">
								<option selected disabled="disabled" value="">---</option>
								<c:forEach var="m" items="${majors }">
									<option>${m.name()}</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback">Please select Major type !</div>

						</div>
						
						</div>
						<div class="mb-3">
							<label for="rollNumber" class="form-label primary-text-color">Roll Number
								</label> <input type="text" class="form-control" id="rollNumber"
								name="rollNumber" required="required">
							<div class="invalid-feedback">Please re-enter university roll number
								number !</div>
						</div>
						<div class="mt-2 mb-3">
							<label for="payment" class="form-label primary-text-color">Payment
								Type</label> <select class="form-select" name="payment"
								required="required">
								<option selected disabled="disabled" value="">---</option>
								<c:forEach var="m" items="${pays }">
									<option>${m.name()}</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback">Please select payment type !</div>

						</div>
						<div class="mb-3">
							<label for="tName" class="form-label primary-text-color">Transfer
								From</label> <input type="text" class="form-control" id="tName"
								name="tName" required="required">
							<div class="invalid-feedback">Please re-enter transferring
								from !</div>
						</div>
						<div class="mb-3">
							<label for="tNum" class="form-label primary-text-color">Transaction
								No.</label> <input type="text" class="form-control" id="tNum"
								name="tNum" required="required">
							<div class="invalid-feedback">Please re-enter transaction
								number !</div>
						</div>
						<div class="mb-3">
							<label for="amount" class="form-label primary-text-color">Amount</label>
							<input type="text" class="form-control" id="amount" name="amount"
								required="required">
							<div class="invalid-feedback">Please re-enter transfer
								amount !</div>
						</div>
						<div class="mb-3">
							<label for="note" class="form-label primary-text-color">Note</label>
							<input type="text" class="form-control" id="note" name="note"
								required="required">
							<div class="invalid-feedback">Please re-enter transferring
								note !</div>
						</div>

						<div class="nav justify-content-end">
							<button type="submit" class="btn primary-color">Save
								Payment</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>