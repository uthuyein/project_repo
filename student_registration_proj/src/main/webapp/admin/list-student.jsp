<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<div class="p-3 mt-3">
	<c:choose>
		<c:when test="${null ne listUniInfo }">
			<form action="/admin/students" class="form mt-2 w-75" method="post">
				<div class="row">
					<div class="col">
						<label for="openYear" class="form-label text-primary">University Open
							Year</label><select name="openYear" id="openYear" class="form-select">
							<option>---</option>
							<c:forEach var="u" items="${listUniInfo }">
								<option>${u.uniOpenYear()}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col">
						<label for="uniYear" class="form-label text-primary">Unversity Year</label><select
							name="uniYear" id="uniYear" class="form-select">
							<option>---</option>
							<c:forEach var="u" items="${listUniInfo }">
								<option>${u.uniYear().name()}</option>
							</c:forEach>
							</select>
					</div>
					<div class="col">
						<label for="major" class="form-label text-primary">Major</label><select
							name="major" id="major" class="form-select">
							<option>---</option>
							<c:forEach var="u" items="${listUniInfo }">
								<option>${u.major().name()}</option>
							</c:forEach>
							</select>
					</div>
					<div class="col">
						<label for="stuName" class="form-label text-primary">Student Name</label><input
							type="text" name="stuName" id="stuName" class="form-control"/>
					</div>
					<div class="col mt-4 pt-2 ">
						<button class="btn btn-primary " type="submit"><i class="bi bi-search"></i>&nbsp;Search</button>
					</div>
				</div>
			</form>
			<div class="table-responsive mt-4">
				<table class="table table-hover w-auto">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Name</th>
							<th scope="col">Email</th>
							<th scope="col">2nd Contact</th>
							<th scope="col">Unversity Open Year</th>
							<th scope="col">University Year</th>
							<th scope="col">Major</th>
							<th scope="col">Roll Number</th>
							<th scope="col">Date Of Birth</th>
							<th scope="col">Nrc Number</th>
							<th scope="col">City</th>
							<th scope="col">Township</th>
							<th scope="col">Street</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="s" items="${listUniInfo }" varStatus="n">
							<tr>
								<th scope="row">${n.index+1 }</th>
								<td><a href="" class="nav-link">${s.name() }</a></td>
								<td>${s.email()}</td>
								<td>${s.primaryPhone()}</td>
								<td>${s.secondaryPhone()}</td>
								<td>${s.uniOpenYear() }</td>
								<td>${s.uniYear() }</td>
								<td>${s.major() }</td>
								<td>${s.rollNumber()}</td>
								<td>${s.dob() }</td>
								<td>${s.nrc() }</td>
								<td>${s.city() }</td>
								<td>${s.township()}</td>
								<td>${s.street()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:when>

		<c:otherwise>
			<div class="alert alert-success" role="alert">
				<h4 class="alert-heading">Well done!</h4>
				<p>There is no student data</p>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="${foot }"></jsp:include>