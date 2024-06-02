<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.mkt.ym.entity.type.*"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="addStudent" value="/admin/addStudent"></c:url>
<c:url var="listStudent" value="/admin/studentList"></c:url>
<c:set var="majors" value="<%=Major.values()%>"></c:set>
<c:set var="uniYears" value="<%=UniYear.values()%>"></c:set>


<jsp:include page="${head }"></jsp:include>
<div class="row" data-bs-spy="scroll" data-bs-target="#navbar-example"
	style="position: relative">
	<nav id="navbar-example"
		class="navbar navbar-light bg-light px-3 sticky-sm-top">
		<ul class="nav">
			<li class="nav-item"><a href="${addStudent }"
				class="nav-link primary-text-color"><i class="bi bi-person-fill"></i>&nbsp;Add
					Student</a></li>
			<li class="nav-item"><a href="${listStudent }"
				class="nav-link primary-text-color"><i
					class="bi bi-person-lines-fill"></i>&nbsp;Student List</a></li>
		</ul>
	</nav>
	<div class="row p-3 m-3">
		<form action="${listStudent }" class="form  mt-2 w-75" method="post">
			<div class="row">
				<div class="col">
					<label for="city" class="form-label primary-text-color">City</label><select
						name="city" id="city" class="form-select">
						<option>---</option>
						<c:forEach var="u" items="${cities }">
							<option>${u}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
					<label for="township" class="form-label primary-text-color">Township
					</label><select name="township" id="township" class="form-select">
						<option>---</option>
						<c:forEach var="u" items="${townships }">
							<option>${u}</option>
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
			<c:when test="${null ne studentList }">
				<div class="table-responsive mt-4">
					<table class="table table-hover w-auto">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Name</th>
								<th scope="col">Email</th>
								<th scope="col">1st Contact</th>
								<th scope="col">2nd Contact</th>
								<th scope="col">Grade 12 RollNumber</th>
								<th scope="col">Grade 12 Marks</th>
								<th scope="col">Date Of Birth</th>
								<th scope="col">Nrc Number</th>
								<th scope="col">City</th>
								<th scope="col">Township</th>
								<th scope="col">Street</th>
								<th scope="col">Father Name</th>
								<th scope="col">Father Nrc</th>
								<th scope="col">Mother Name</th>
								<th scope="col">Mother Nrc</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="s" items="${studentList }" varStatus="n">
								<tr>
									<th scope="row">${n.index+1 }</th>
									<td><a href="" class="nav-link">${s.name() }</a></td>
									<td>${s.email()}</td>
									<td>${s.primaryContact()}</td>
									<td>${s.secondaryContact()}</td>
									<td>${s.rollNum()}</td>
									<td>${s.totalMarks() }</td>
									<td>${s.dob() }</td>
									<td>${s.nrc() }</td>
									<td>${s.city() }</td>
									<td>${s.township()}</td>
									<td>${s.street()}</td>
									<td>${s.fName() }</td>
									<td>${s.fNrc() }</td>
									<td>${s.mName() }</td>
									<td>${s.mNrc() }</td>
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
</div>
<jsp:include page="${foot }"></jsp:include>