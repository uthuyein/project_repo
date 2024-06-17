<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="register" value="/student/register"></c:url>
<c:url var="payment" value="/student/payment"></c:url>
<c:url var="acc" value="/student/account"></c:url>
<c:url var="stuUniInfo" value="/student/stuUniversityInfo"></c:url>


<%@page import="com.mkt.ym.entity.type.*"%>

<jsp:include page="${head }"></jsp:include>
<div class="row">

	<nav id="navbar-example"
		class="navbar navbar-light bg-light px-3 sticky-sm-top">
		<ul class="nav">
			<c:if test="${null ne account }">

				<li class="nav-item"><a
					class="nav-link primary-text-color fs-sm"
					href="${stuUniInfo }?id=${account.student.id}">Student</a></li>
				<li class="nav-item "><a class="nav-link primary-text-color"
					href="${payment }">Payment</a></li>
			</c:if>
			<li class="nav-item"><a class="nav-link primary-text-color"
				href="${acc }">Account</a></li>
		</ul>
	</nav>
	<div class="row justify-content-center  p-5 message-target">
		<div class="col col-md-5" tabindex="0">
			<c:if test="${null ne message }">
				<span class="mess"> <label
					class="alert d-flex p-2 ${message.getColor() } }">${message.message}</label>
				</span>
			</c:if>

			<div class="card">
				<form action="${account }" method="post">
					<div class="card-body ">

						<div class="card-header mt-3 mb-4 primary-header-text-color">
							<figure>
								<blockquote class="blockquote">
									<input class="form-check-input" type="checkbox" name="active"
										hidden="hidden" /> <i class="bi bi-person-fill "></i>&nbsp;
									Add Account
								</blockquote>
								<figcaption class="blockquote-footer">
									<small>Admin will activate within 48hrs. </small>
								</figcaption>
							</figure>
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
						<div class="mb-3">
							<label for="password" class="form-label primary-text-color">Confirm
								Password</label> <input type="password" class="form-control"
								id="password" name="confirm">
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
</div>
<jsp:include page="${foot }"></jsp:include>
