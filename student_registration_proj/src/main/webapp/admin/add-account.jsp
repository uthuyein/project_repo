<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<%@page import="com.mkt.ym.entity.University.Year"%>
<%@page import="com.mkt.ym.entity.University.Major"%>
<%@page import="com.mkt.ym.entity.Account.Role"%>

<div class="row justify-content-center p-5">

	<div class="card col-md-5">
		<div class="card-header mt-2 text-primary">
			<i class="bi bi-person-fill"></i> Add Account
		</div>
		<div class="card-body ">
			<form action="/admin/addParent" method="post">

				<div class="row">
					<div class="col">
						<div class="mb-3">
							<label for="fName" class="form-label text-primary">University
								Year</label> <select class="form-select">
								<c:set var="years" value="<%=Year.values()%>"></c:set>
								<option selected>---</option>

								<c:forEach var="y" items="${years }">
									<option value="${y}">${y.name()}</option>

								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col">
						<div class="mb-3">
							<label for="fName" class="form-label text-primary">Major
								Name</label> <select class="form-select">
								<c:set var="majors" value="<%=Major.values()%>"></c:set>
								<option selected>---</option>
								<c:forEach var="m" items="${majors }">
									<option value="${m }">${m.name()}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="col">
						<div class="mb-3">
							<label for="fName" class="form-label text-primary">Roll</label> <select
								class="form-select">
								<option selected>---</option>
								<option value="1">Aung Aung</option>
								<option value="2">Maung</option>
								<option value="3">Maung Myo</option>
							</select>
						</div>
					</div>
				</div>

				<div class="mb-3">
					<label for="fName" class="form-label text-primary">Account
						Role</label> <select class="form-select">
						<c:set var="roles" value="<%=Role.values()%>"></c:set>
						<option selected>---</option>
						<c:forEach var="role" items="${roles }">
							<option value="${role }">${ role.name()}</option>
						</c:forEach>

					</select>
				</div>
				<div class="mb-3">
					<label for="username" class="form-label text-primary">User
						Name</label> <input type="text" class="form-control" id="username"
						name="username">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label text-primary">Password</label>
					<input type="password" class="form-control" id="password"
						name="password">
				</div>
				<div class="mb-3">
					<label for="confirm" class="form-label text-primary ">Confirm
						Password</label> <input type="password" class="form-control" id="confirm"
						name="confirm">
				</div>

				<button type="submit" class="btn btn-primary">Save Account</button>
			</form>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>