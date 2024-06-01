<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<%@page import="com.mkt.ym.entity.type.*"%>

<c:url var="addAccount" value="/admin/addAccount"></c:url>

<div class="row justify-content-center p-5">

	<div class="card col-md-5 ">
		<div class="card-header mt-2 primary-header-text-color">
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
				<!-- <div class="mb-3">
					<label for="confirm" class="form-label text-primary ">Confirm
						Password</label> <input type="password" class="form-control" id="confirm"
						name="confirm">
				</div>
 -->
				<button type="submit" class="btn primary-color">Save
					Account</button>
			</form>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>