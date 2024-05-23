<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.mkt.ym.entity.type.*"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:set var="majors" value="<%=Major.values()%>"></c:set>
<c:set var="years" value="<%=UniYear.values()%>"></c:set>

<jsp:include page="${head }"></jsp:include>

<div class="row justify-content-center p-5">
	<div class="card col-md-5">
		<div class="card-header mt-2 primary-header-text-color">
			<i class="bi bi-person-fill"></i> Add Student To University
		</div>
		<div class="card-body ">
			<form action="/admin/addUniInfo" method="post">
				<div class="mb-3">
					<div class="row">
						<div class="col col-md-5">
							<label for="openYear" class="form-label primary-text-color">University Opening
								Year</label> <input type="text" id="openYear" class="form-control"
								name="openYear"/>
						</div>
					</div>
				</div>
				<div class="mb-3">
					<div class="row">
						<div class="col col-md-5">
							<label for="newYear" class="form-label primary-text-color">Student
								New Year</label> <select id="newYear" class="form-select" name="newYear">
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
					<label for="student" class="form-label primary-text-color">Student
						Name</label> <select id="student" class="form-select" name="student">
						<option selected>---</option>
						<c:if test="${students ne null}">
							<c:forEach var="s" items="${students }">
								<option value="${s.id }">${s.name }</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
				<div class="mb-3">
					<label for="newRollNum" class="form-label primary-text-color">Add
						Roll Number</label> <input type="text" class="form-control"
						id="newRollNum" name="newRollNum">
				</div>

			<%-- 	<fieldset class="fieldset-color">
					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							name="isOldStudent" id="isOldStudent" checked> <label
							class="form-check-label text-primary" for="isOldStudent" >
							Previous University Info </label>
					</div>
					<div id="emailHelp" class="form-text mb-3"
						style="margin-top: -8px">Must be check and fill up for the old
						students</div>

					<div class="mb-3">
						<div class="row">
							<div class="col col-md-5">
								<label for="prevYear" class="form-label text-primary">Student
									Previous Year</label> <select class="form-select" id="prevYear" name="prevYear">
									<option selected>---</option>
									<c:forEach var="y" items="${years }">
										<option>${y.name() }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="mb-3">
						<label for="prevRollNum" class="form-label text-primary">Previous
							Roll Number</label> <input type="text" class="form-control"
							id="prevRollNum" name="prevRollNum">
					</div>
				</fieldset> --%>
				<button type="submit" class="btn primary-color w-25">Save</button>
			</form>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>
