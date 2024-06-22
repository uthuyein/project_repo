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
				<span class="mess"> <label
					class="alert d-flex p-2 ${message.getColor() } }">${message.message}</label>
				</span>
			</c:if>

		<div class="card ">
		<c:choose>
		<c:when test="${null != updAcc }">
		<form action="${addAccount }" method="post" class="needs-validation"
				novalidate>
				<div class="card-body ">
				<input class="form-check-input" type="text" name="id"  value="${updAcc.id }"  hidden/>
					
					<div class="card-header mt-2 mb-3 primary-header-text-color">			
						<i class="bi bi-person-fill "></i>&nbsp; Update Account
					</div>
					<div class="mb-3">
						<label for="username" class="form-label primary-text-color">
							User Name</label> <input type="text" class="form-control" id="username"
							name="username" value="${updAcc.username }" >
						<div class="invalid-feedback">Please re-enter username !</div>
					</div>
					<div class="mb-3">
						<label for="loginId" class="form-label primary-text-color">
							LoginId</label> <input type="text" class="form-control" id="loginId"
							name="loginId" required="required" value="${updAcc.loginId }">
						<div class="invalid-feedback">Please re-enter loginId !</div>
					</div>
					<div class="mb-3">
						<label for="password" class="form-label primary-text-color">Password</label>
						<input type="password" class="form-control" id="password"
							name="password" required="required" value="${updAcc.password }">
						<div class="invalid-feedback">Please re-enter password !</div>
					</div>
					<div class="mb-3">
						<label for="confirm" class="form-label primary-text-color">Confirm</label>
						<input type="password" class="form-control" id="confirm"
							name="confirm" required="required" >
						<div class="invalid-feedback">Please re-enter confirm password !</div>
					</div>
					<div class="nav justify-content-end">
						<button type="submit" class="btn primary-color">Update
							Account</button>
					</div>


				</div>
			</form>
		</c:when>
		<c:otherwise>
		<form action="${addAccount }" method="post" class="needs-validation"
				novalidate>
				<div class="card-body ">
					<div class="card-header mt-2 mb-3 primary-header-text-color">
					<input class="form-check-input" type="checkbox" name="active" checked  />
					
						<i class="bi bi-person-fill "></i>&nbsp; Add Account
					</div>
					<div class="mb-3">
						<label for="username" class="form-label primary-text-color">
							User Name</label> <input type="text" class="form-control" id="username"
							name="username" required="required">
						<div class="invalid-feedback">Please re-enter username !</div>
					</div>
					<div class="mb-3">
						<label for="loginId" class="form-label primary-text-color">
							LoginId</label> <input type="text" class="form-control" id="loginId"
							name="loginId" required="required">
						<div class="invalid-feedback">Please re-enter loginId !</div>
					</div>
					<div class="mb-3">
						<label for="password" class="form-label primary-text-color">Password</label>
						<input type="password" class="form-control" id="password"
							name="password" required="required">
						<div class="invalid-feedback">Please re-enter password !</div>
					</div>
					<div class="mb-3">
						<label for="confirm" class="form-label primary-text-color">Confirm</label>
						<input type="password" class="form-control" id="confirm"
							name="confirm" required="required">
						<div class="invalid-feedback">Please re-enter confirm password !</div>
					</div>
					<div class="nav justify-content-end">
						<button type="submit" class="btn primary-color">Save
							Account</button>
					</div>


				</div>
			</form>
		</c:otherwise>
		</c:choose>
			
		</div>
	</div>
</div>

<jsp:include page="${foot }"></jsp:include>