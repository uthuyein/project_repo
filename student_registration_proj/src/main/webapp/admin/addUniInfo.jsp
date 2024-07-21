<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.mkt.ym.entity.type.*"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="addUniInfo" value="/admin/addStudentToUni"></c:url>
<c:url var="listStudentUni" value="/admin/studentListfrmUni"></c:url>
<c:url var="listPayment" value="/admin/paymentList"></c:url>


<c:set var="majors" value="<%=Major.values()%>"></c:set>
<c:set var="years" value="<%=UniYear.values()%>"></c:set>
<jsp:include page="${head }"></jsp:include>
<nav id="navbar-example"
	class="navbar navbar-light bg-light px-3 sticky-sm-top">
	<ul class="nav">
		<li class="nav-item"><a href="${addUniInfo }"
			class="nav-link primary-text-color"><i class="bi bi-person-fill"></i>&nbsp;Add
				Student To University</a></li>
		<li class="nav-item"><a href="${listStudentUni }"
			class="nav-link primary-text-color"><i
				class="bi bi-person-lines-fill"></i>&nbsp;Student List From
				University</a></li>
		<li class="nav-item"><a href="${listPayment }"
			class="nav-link primary-text-color"><i
				class="bi bi-person-badge-fill"></i>&nbsp;Payments</a></li>
	</ul>
</nav>
<div class="row justify-content-center p-5 message-target">
	<div class="col col-md-5">
		<c:if test="${null != message }">
			<span class="mess"> <label
				class="alert d-flex p-2 ${message.getColor() } ">${message.message}</label>
			</span>
		</c:if>
		<div class="card">
			<c:choose>
				<c:when test="${null != uniInfo }">
					<c:set var="u" value="${uniInfo }"></c:set>
					<form action="${addUniInfo }?${uniInfo.id}" method="post"
						class="needs-validation" novalidate>
						<div class="card-body ">

							<div class="card-header mt-2 mb-3 primary-header-text-color">
								<i class="bi bi-person-fill"></i> Update Student To University
							</div>
							<div class="mb-3">
								<div class="row">
									<div class="col col-md-5">
										<label for="openYear" class="form-label primary-text-color">University
											Opening Year</label> <input type="text" id="openYear"
											class="form-control" name="openYear" required="required"
											value="${uniInfo.id.openYear }" disabled="disabled">
										<div class="invalid-feedback">Please re-enter university
											open year !</div>
									</div>
								</div>
							</div>
							<div class="mb-3">
								<div class="col col-md-5">
									<label for="stuName" class="form-label primary-text-color">Student
										Name</label> <input name="stuName" type="text" class="form-control"
										value="${uniInfo.student.name }" />
									<div class="invalid-feedback">Please type student's name
										!</div>
								</div>
							</div>
							<div class="mb-3">
								<div class="row">
									<div class="col col-md-5">
										<label for="uniYear" class="form-label primary-text-color">Student
											New Year</label> <select id="uniYear" class="form-select"
											name="uniYear" required="required" disabled="disabled">
											<option selected>${uniInfo.id.uniYear }</option>
											<c:forEach var="y" items="${years }">
												<option>${y.name() }</option>
											</c:forEach>
										</select>
										<div class="invalid-feedback">Please select university
											year !</div>
									</div>
									<div class="col col-md-5">
										<label for="major" class="form-label primary-text-color">Major</label>
										<select class="form-select" id="major" name="major"
											required="required" disabled="disabled">
											<option selected>${uniInfo.id.major }</option>
											<c:forEach var="m" items="${majors }">
												<option>${m.name() }</option>
											</c:forEach>
										</select>
										<div class="invalid-feedback">Please select major !</div>
									</div>
								</div>
							</div>
							
							<div class="mb-3">
							<label for="nrc" class="form-label primary-text-color">National
										Registration Card(NRC)</label>
								<div class="row">
									<div class="col col-sm-2">
										<select id="code" name="code" class="form-select"
											onchange="fetchNrc('code','codeName')">
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
											name="codeNum" required="required" value="${codeNum }">
										<div class="invalid-feedback">Please re-enter student's
											nrc !</div>
									</div>
								</div>

							</div>
							<div class="mb-3">
								<label for="newRollNum" class="form-label primary-text-color">Add
									Roll Number</label> <input type="text" class="form-control"
									id="newRollNum" name="newRollNum" required="required"
									value="${uniInfo.id.rollNumber }">
								<div class="invalid-feedback">Please re-enter student
									university roll number !</div>
							</div>
							<div class="nav justify-content-end">
								<button type="submit" class="btn primary-color w-50">Update
									University Info</button>
							</div>
						</div>
					</form>
				</c:when>


				<c:otherwise>
					<form action="${addUniInfo }" method="post"
						class="needs-validation" novalidate>
						<div class="card-body ">

							<div class="card-header mt-2 mb-3 primary-header-text-color">
								<i class="bi bi-person-fill"></i> Add Student To University
							</div>
							<div class="mb-3">
								<div class="row">
									<div class="col col-md-5">
										<label for="openYear" class="form-label primary-text-color">University
											Opening Year</label> <input type="text" id="openYear"
											class="form-control" name="openYear" required="required">
										<div class="invalid-feedback">Please re-enter university
											open year !</div>
									</div>
								</div>
							</div>
							<div class="mb-3">
								<div class="col col-md-5">
									<label for="stuName" class="form-label primary-text-color">Student
										Name</label> <input name="stuName" type="text" class="form-control"
										value="${uniInfo.student.name }" />
									<div class="invalid-feedback">Please type student's name
										!</div>
								</div>
							</div>
							<div class="mb-3">
								<div class="row">
									<div class="col col-md-5">
										<label for="uniYear" class="form-label primary-text-color">Student
											New Year</label> <select id="uniYear" class="form-select"
											name="uniYear" required="required">
											<option selected disabled="disabled" value="">---</option>
											<c:forEach var="y" items="${years }">
												<option>${y.name() }</option>
											</c:forEach>
										</select>
										<div class="invalid-feedback">Please select university
											year !</div>
									</div>
									<div class="col col-md-5">
										<label for="major" class="form-label primary-text-color">Major</label>
										<select class="form-select" id="major" name="major"
											required="required">
											<option selected disabled="disabled" value="">---</option>
											<c:forEach var="m" items="${majors }">
												<option>${m.name() }</option>
											</c:forEach>
										</select>
										<div class="invalid-feedback">Please select major !</div>
									</div>
								</div>
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
											name="codeNum" required="required" value="${codeNum }">
										<div class="invalid-feedback">Please re-enter student's
											nrc !</div>
									</div>
								</div>

							</div>
							<div class="mb-3">
								<label for="newRollNum" class="form-label primary-text-color">Add
									Roll Number</label> <input type="text" class="form-control"
									id="newRollNum" name="newRollNum" required="required">
								<div class="invalid-feedback">Please re-enter student
									university roll number !</div>
							</div>
							<div class="nav justify-content-end">
								<button type="submit" class="btn primary-color">Save</button>
							</div>
						</div>
					</form>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>
