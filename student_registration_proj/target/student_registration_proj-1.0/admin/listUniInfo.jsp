<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.mkt.ym.entity.type.*"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="addUniInfo" value="/admin/addStudentToUni"></c:url>
<c:url var="listStudentUni" value="/admin/studentListfrmUni"></c:url>
<c:url var="editStuUni" value="/admin/editUniversityInfo"></c:url>
<c:url var="deleteStuUni" value="/admin/deleteUniversityInfo"></c:url>
<c:url var="stuUniInfo" value="/student/stuUniversityInfo"></c:url>
<c:url var="listPayment" value="/admin/paymentList"></c:url>

<c:set var="majors" value="<%=Major.values()%>"></c:set>
<c:set var="uniYears" value="<%=UniYear.values()%>"></c:set>


<jsp:include page="${head }"></jsp:include>
<nav id="navbar-example"
	class="navbar navbar-light bg-light px-3 sticky-sm-top">
	<ul class="nav">
		<li class="nav-item"><a href="${addUniInfo }"
			class="nav-link primary-text-color"><i class="bi bi-collection"></i>&nbsp;Add
				Student To University</a></li>
		<li class="nav-item"><a href="${listStudentUni }"
			class="nav-link primary-text-color"><i
				class="bi bi-collection-fill"></i>&nbsp;Student List From University</a></li>
				<li class="nav-item"><a href="${listPayment }"
			class="nav-link primary-text-color"><i
				class="bi bi-collection-fill"></i>&nbsp;Payments</a></li>
	</ul>
</nav>
<div class="row p-3 m-3">
	<form action="${listStudentUni }" class="form mt-2 w-75" method="post">
		<div class="row">
			<div class="col">
				<label for="openYear" class="form-label primary-text-color">University
					Open Year</label><select name="openYear" id="openYear" class="form-select">
					<option>---</option>
					<c:forEach var="u" items="${openYears }">
						<option>${u}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col">
				<label for="uniYear" class="form-label primary-text-color">Unversity
					Year</label><select name="uniYear" id="uniYear" class="form-select">
					<option>---</option>
					<c:forEach var="u" items="${uniYears }">
						<option>${u.name()}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col">
				<label for="major" class="form-label primary-text-color">Major</label><select
					name="major" id="major" class="form-select">
					<option>---</option>
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
	</form>
	<c:choose>
		<c:when test="${null ne listUniInfo }">
			<div class="table-responsive mt-4">
				<table class="table table-hover w-auto">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Name</th>
							<th scope="col">Unversity Open Year</th>
							<th scope="col">University Year</th>
							<th scope="col">Roll Number</th>
							<th scope="col">Major</th>
							<th scope="col">Date Of Birth</th>
							<th scope="col">Nrc Number</th>
							<th></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="s" items="${listUniInfo }" varStatus="n">
							<tr>
								<th scope="row">${n.index+1 }</th>
								<td>${s.name() }</td>
								<td>${s.openYear() }</td>
								<td>${s.uniYear() }</td>
								<td>${s.rollNumber()}</td>
								<td>${s.major() }</td>
								<td>${s.dob() }</td>
								<td>${s.nrc() }</td>
								<td><a href="${editStuUni }?index=${n.index}"
									class="btn btn-outline-primary">Edit</a></td>
								<td><a href="${deleteStuUni }?index=${n.index}"
									class="btn btn-outline-danger">Delete</a></td>
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