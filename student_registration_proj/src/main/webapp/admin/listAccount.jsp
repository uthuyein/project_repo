<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.mkt.ym.entity.type.*"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="addAcc" value="/admin/addAccount"></c:url>
<c:url var="listAcc" value="/admin/accountList"></c:url>
<c:url var="editAcc" value="/admin/editAccount"></c:url>
<c:url var="deleteAcc" value="/admin/deleteAccount"></c:url>

<c:set var="roles" value="<%=Role.values()%>"></c:set>


<jsp:include page="${head }"></jsp:include>
<nav id="navbar-example"
	class="navbar navbar-light bg-light px-3 sticky-sm-top">
	<ul class="nav">
		<li class="nav-item"><a href="${addAcc }"
			class="nav-link primary-text-color"><i
				class="bi bi-credit-card-2-front"></i>&nbsp;Add Account</a></li>
		<li class="nav-item"><a href="${listAcc }"
			class="nav-link primary-text-color"><i
				class="bi bi-credit-card-2-front-fill"></i>&nbsp;Account List</a></li>
	</ul>
</nav>
<div class="row p-3 m-3">
	<form action="${listAcc }" class="form mt-2 w-75" method="post">
		<div class="row">
			<div class="col">
				<label for="userName" class="form-label primary-text-color">User
					Name </label><input id="userName" name="userName" type="text"
					class="form-control" />
			</div>
			<div class="col">
				<label for="loginId" class="form-label primary-text-color">LoginId
					</label><input name="loginId" id="loginId" type="text"
					class="form-control" />
			</div>
			<div class="col">
				<label for="role" class="form-label primary-text-color">Role</label><select
					name="role" id="role" class="form-select">
					<option selected disabled value="">---</option>
					<c:forEach var="r" items="${roles }">
						<option>${r.name()}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col mt-4 pt-2 ">
				<button class="btn primary-color " type="submit">
					<i class="bi bi-search"></i>&nbsp;Search
				</button>
			</div>
		</div>
	</form>
	<c:choose>
		<c:when test="${null ne listAccount }">
			<div class="table-responsive mt-4">
				<table class="table table-hover w-auto">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Role</th>
							<th scope="col">User Name</th>
							<th scope="col">Login Id</th>
							<th scope="col">Password</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="a" items="${listAccount }" varStatus="n">
							<tr>
								<td scope="row">${n.index+1 }</td>
								<td>${a.role}</td>
								<td>${a.username}</td>
								<td>${a.loginId }</td>
								<td>${a.password }</td>
								<td><a href="${editAcc }?id=${a.id}" class="btn btn-outline-primary">Edit</a></td>
								<td><a href="${editAcc }?id=${a.id}" class="btn btn-outline-danger">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:when>

		<c:otherwise>
			<div class="alert alert-success mt-3" role="alert">
				<h4 class="alert-heading">Well done!</h4>
				<p>There is no account yet !</p>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="${foot }"></jsp:include>