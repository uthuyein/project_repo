<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.mkt.ym.entity.type.*"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="addUniInfo" value="/admin/addStudentToUni"></c:url>
<c:url var="listStudentUni" value="/admin/studentListfrmUni"></c:url>


<c:set var="majors" value="<%=Major.values()%>"></c:set>
<c:set var="years" value="<%=UniYear.values()%>"></c:set>

<jsp:include page="${head }"></jsp:include>
<nav id="navbar-example"
	class="navbar navbar-light bg-light px-3 sticky-sm-top">
	<ul class="nav">
		<li class="nav-item"><a href="${addUniInfo }"
			class="nav-link primary-text-color"><i class="bi bi-collection"></i>&nbsp;Add Student To
				University</a></li>
		<li class="nav-item"><a href="${listStudentUni }"
			class="nav-link primary-text-color"><i class="bi bi-collection-fill"></i>&nbsp;Student List
				From University</a></li>
	</ul>
</nav>
<div class="row justify-content-center p-5">
	<div class="card col-md-5">
		<div class="card-header mt-2 primary-header-text-color">
			<i class="bi bi-person-fill"></i> Add Student To University
		</div>
		<div class="card-body ">
			<form action="${addUniInfo }" method="post">
				<div class="mb-3">
					<div class="row">
						<div class="col col-md-5">
							<label for="frmYear" class="form-label primary-text-color">University Opening
								Year</label> <input type="text" id="frmYear" class="form-control"
								name="openYear"/>
						</div>
					</div>
				</div>
				<div class="mb-3">
					<div class="row">
						<div class="col col-md-5">
							<label for="newYear" class="form-label primary-text-color">Student
								New Year</label> <select id="newYear" class="form-select" name="newyear">
								<option selected>---</option>
								<c:forEach var="y" items="${years }">
									<option>${y.name() }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col">
							<label for="major" class="form-label primary-text-color">Major</label>
							<select class="form-select" id="major" name="major">
								<option selected>---</option>
								<c:forEach var="m" items="${majors }">
									<option>${m.name() }</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="mb-3">
				<div class="row">
					<div class="col">
					<label for="student" class="form-label primary-text-color">Student
						Name</label> <select id="student" class="form-select" name="stuName">
						<option selected>---</option>
						<c:if test="${listStudent ne null}">
							<c:forEach var="s" items="${listStudent }">
								<option >${s.name }</option>
							</c:forEach>
						</c:if>
					</select>
					</div>
					<div class="col">
					<label for="student" class="form-label primary-text-color">Student
						Nrc</label> <select id="student" class="form-select" name="nrc">
						<option selected>---</option>
						<c:if test="${listStudent ne null}">
							<c:forEach var="s" items="${listStudent }">
								<option >${s.nrc }</option>
							</c:forEach>
						</c:if>
					</select>
					</div>
				</div>
					
				</div>
				<div class="mb-3">
					<label for="newRollNum" class="form-label primary-text-color">Add
						Roll Number</label> <input type="text" class="form-control"
						id="newRollNum" name="newRollNum">
				</div>
				<button type="submit" class="btn primary-color w-25">Save</button>
			</form>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>
