<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.mkt.ym.entity.type.*"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="addUniInfo" value="/admin/addStudentToUni"></c:url>
<c:url var="listStudentUni" value="/admin/studentListfrmUni"></c:url>
<c:url var="updPay" value="/admin/updatePayment"></c:url>
<c:url var="listPayment" value="/admin/paymentList"></c:url>



<c:set var="majors" value="<%=Major.values()%>"></c:set>
<c:set var="uniYears" value="<%=UniYear.values()%>"></c:set>
<c:set var= "types" value="<%=PaymentType.values() %>"></c:set>


<jsp:include page="${head }"></jsp:include>
<nav id="navbar-example"
	class="navbar navbar-light bg-light px-3 sticky-sm-top">
	<ul class="nav">
		<li class="nav-item"><a href="${addUniInfo }"
			class="nav-link primary-text-color"><i class="bi bi-person-fill"></i>&nbsp;Add
				Student To University</a></li>
		<li class="nav-item"><a href="${listStudentUni }"
			class="nav-link primary-text-color"><i class="bi bi-person-lines-fill"></i>&nbsp;Student List From University</a></li>
		<li class="nav-item"><a href="${listPayment }"
			class="nav-link primary-text-color"><i class="bi bi-person-badge-fill"></i>&nbsp;Payments</a></li>
	</ul>
</nav>
<div class="row p-3 m-3">
	<form action="${listPayment }" class="form mt-2 w-75" method="post">
		<fieldset class="border p-2 mb-2">
			<legend class="float-none w-auto primary-text-color">&nbsp;Search
				By &nbsp;</legend>
			<div class="row mb-2">
			<div class="col">
					<label for="payment" class="form-label primary-text-color">Payment Type
						</label><select name="payment" id="payment" class="form-select">
						<option disabled selected value="">---</option>
						<c:forEach var="t" items="${types }">
							<option>${t.name()}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
					<label for="openYear" class="form-label primary-text-color">
						Open Year</label><select name="openYear" id="openYear" class="form-select">
						<option disabled selected value="">---</option>
						<c:forEach var="u" items="${openYears }">
							<option>${u}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
					<label for="uniYear" class="form-label primary-text-color">Unversity
						Year</label><select name="uniYear" id="uniYear" class="form-select">
						<option disabled selected value="">---</option>
						<c:forEach var="u" items="${uniYears }">
							<option>${u.name()}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
					<label for="major" class="form-label primary-text-color">Major</label><select
						name="major" id="major" class="form-select">
						<option disabled selected value="">---</option>
						<c:forEach var="u" items="${majors }">
							<option>${u.name()}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
					<label for="stuName" class="form-label primary-text-color">Student
						Name</label><input type="text" name="stuName" id="stuName"
						class="form-control" />
				</div>
				<div class="col mt-4 pt-2 ">
					<button class="btn primary-color " type="submit">
						<i class="bi bi-search"></i>&nbsp;Search
					</button>
				</div>
			</div>
			<hr />
			<div class="row w-50">
				<div class="col col-sm-4">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status"
							id="status1" value="true"> <label
							class="form-check-label primary-text-color" for="status1">
							Correct Status </label>
					</div>
				</div>
				<div class="col col-sm-4">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status"
							id="status2" value="false"> <label
							class="form-check-label primary-text-color" for="status2">
							Incorrect Status </label>
					</div>
				</div>
				<div class="col col-sm-4">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="status"
							id="status3" value=""> <label
							class="form-check-label primary-text-color" for="status3">
							Need To Check </label>
					</div>
				</div>
			</div>
		</fieldset>
	</form>
	<c:choose>
		<c:when test="${null ne payments }">
			<div class="table-responsive mt-4">
				<table class="table table-hover w-auto">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Student Name</th>
							<th scope="col">Open Year</th>
							<th scope="col">Student Year</th>
							<th scope="col">Major</th>
							<th scope="col">Roll Number</th>
							<th scope="col">Payment Type</th>
							<th scope="col">Transfer From</th>
							<th scope="col">Transition Number</th>
							<th scope="col">Transefer amount</th>
							<th scope="col">Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="p" items="${payments }" varStatus="n">
							<tr>
								<th scope="row">${n.index+1 }</th>
								<td>${p.uniInfo.student.name }</td>
								<td>${p.uniInfo.id.openYear }</td>
								<td>${p.uniInfo.id.uniYear }</td>
								<td>${p.uniInfo.id.major}</td>
								<td>${p.uniInfo.id.rollNumber}</td>
								<td>${p.id.paymentType }</td>
								<td>${p.transferFrom }</td>
								<td>${p.transactionNum }</td>
								<td>${p.amount }</td>
								<c:choose>
								<c:when test="${null != p.status }">
								<td>${p.status?'✓':'✘' }</td>
								</c:when>
								<c:otherwise>
								<td>Need To Check</td>
								</c:otherwise>
								</c:choose>
								
								
								<td><a href="${updPay }?status=${true}&${p.id}"
									class="btn btn-outline-success">✓</a></td>
								<td><a href="${updPay }?status=${false}&${p.id}"
									class="btn btn-outline-danger">✘</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:when>

		<c:otherwise>
			<div class="alert alert-success mt-3" role="alert">
				<h4 class="alert-heading">Well done!</h4>
				<p>There is no student data</p>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="${foot }"></jsp:include>