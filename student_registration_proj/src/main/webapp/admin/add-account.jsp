<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="addAccount" value="/admin/addAccount"></c:url>
<c:url var="listAccount" value="/admin/accountList"></c:url>

<%@page import="com.mkt.ym.entity.type.*"%>


<jsp:include page="${head }"></jsp:include>
<nav id="navbar-example"
	class="navbar navbar-light bg-light px-3 sticky-sm-top">
	<ul class="nav">
		<li class="nav-item"><a href="${addAccount }"
			class="nav-link primary-text-color"><i
				class="bi bi-credit-card-2-front"></i>&nbsp;Add Account </a></li>
		<li class="nav-item"><a href="${listAccount }"
			class="nav-link primary-text-color"><i
				class="bi bi-credit-card-2-front-fill"></i>&nbsp;Account List </a></li>
	</ul>
</nav>

<div class="row justify-content-center p-5 message-target">
	<div class="col col-md-5">
		<c:if test="${null ne message }">
				<span class="message"> <label
					class="alert d-flex p-2 ${message.getColor() } }">${message.message}</label>
				</span>
			</c:if>

		<div class="card ">
			<form action="${addAccount }" method="post" class="needs-validation"
				novalidate>
				<div class="card-body ">
					<div class="card-header mt-2 mb-3 primary-header-text-color">
						<i class="bi bi-person-fill "></i>&nbsp; Add Account
					</div>
					<div class="mb-3">
						<label for="role" class="form-label primary-text-color">Account
							Role</label> <select id="role" class="form-select" name="role"
							required="required">
							<c:set var="roles" value="<%=Role.values()%>"></c:set>
							<option selected disabled="disabled" value="">---</option>
							<c:forEach var="role" items="${roles }">
								<option>${ role.name()}</option>
							</c:forEach>
						</select>
						<div class="invalid-feedback">Please select account role !</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="mb-3">
								<label for="fName" class="form-label primary-text-color">University
									Year</label> <select class="form-select" name="year"
									required="required">
									<c:set var="years" value="<%=UniYear.values()%>"></c:set>
									<option selected disabled="disabled" value="">---</option>

									<c:forEach var="y" items="${years }">
										<option>${y.name()}</option>

									</c:forEach>
								</select>
								<div class="invalid-feedback">Please select university
									year</div>
							</div>
						</div>
						<div class="col">
							<div class="mb-3">
								<label for="major" class="form-label primary-text-color">Major
									Name</label> <select id="major" class="form-select" name="major"
									required="required">
									<c:set var="majors" value="<%=Major.values()%>"></c:set>
									<option selected disabled="disabled" value="">---</option>
									<c:forEach var="m" items="${majors }">
										<option>${m.name()}</option>
									</c:forEach>
								</select>
								<div class="invalid-feedback">Please select major !</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="mb-3">
								<label for="uniRollNum" class="form-label primary-text-color">University
									Roll Number </label> <input type="text" class="form-control"
									id="uniRollNum" name="uniRollNum" required="required">
								<div class="invalid-feedback">Please re-enter university
									roll number !</div>
							</div>
						</div>
						<div class="col">
							<div class="mb-3">
								<label for="stuName" class="form-label primary-text-color">Student
									Name</label> <input type="text" class="form-control" id="stuName"
									name="username" required="required">
								<div class="invalid-feedback">Please re-enter student name
									!</div>
							</div>
						</div>
					</div>
					<div class="mb-3">
						<label for="username" class="form-label primary-text-color">Account
							User Name</label> <input type="text" class="form-control" id="username"
							name="username" required="required">
						<div class="invalid-feedback">Please re-enter username !</div>
					</div>
					<div class="mb-3">
						<label for="password" class="form-label primary-text-color">Password</label>
						<input type="text" class="form-control" id="password"
							name="password" required="required">
						<div class="invalid-feedback">Please re-enter password !</div>
					</div>
					<div class="nav justify-content-end">
						<button type="submit" class="btn primary-color">Save
							Account</button>
					</div>


				</div>
			</form>
		</div>
	</div>
</div>

<jsp:include page="${foot }"></jsp:include>