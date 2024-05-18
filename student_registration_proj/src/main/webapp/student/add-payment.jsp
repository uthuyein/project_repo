<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<div class="row justify-content-center p-5">
	<div class="card col-md-5">
		<div class="card-header mt-2 text-primary">
			<i class="bi bi-wallet "></i> &nbsp;Add Payment Information
		</div>
		<div class="card-body ">
			<form action="/student/addPayment" method="post">

				<div class="mb-3">
					<label for="fName" class="form-label text-primary">Payment
						Type</label> <select class="form-select">
						<option selected>---</option>
						<option value="1">KBZ Pay</option>
						<option value="2">Aya Pay</option>
						<option value="3">Wave Pay</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="tName" class="form-label text-primary">Transfer
						From</label> <input type="text" class="form-control" id="tName">
				</div>
				<div class="mb-3">
					<label for="tNum" class="form-label text-primary">Transaction
						No.</label> <input type="text" class="form-control" id="tNum" name="tNum">
				</div>
				<div class="mb-3">
					<label for="amount" class="form-label text-primary">Amount</label>
					<input type="text" class="form-control" id="amount">
				</div>
				<div class="mb-3">
					<label for="note" class="form-label text-primary">Note</label> <input
						type="text" class="form-control" id="note">
				</div>

				<button type="submit" class="btn btn-primary">Confirm</button>
			</form>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>