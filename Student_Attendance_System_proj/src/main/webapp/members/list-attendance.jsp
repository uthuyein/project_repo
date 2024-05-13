<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<c:url var="addAttendance" value="/admin/add-attendance"></c:url>
<c:url var="slClassRoom" value="/list-select-classroom"></c:url>
<body>
	<div class="container mt-4 p-2">

		<div class="card">
			<div class="card-header txt-body-style">
				<div class="card-title d-flex justify-content-between ">
					<div class="mt-2">
						<h5> <i class="bi bi-file-person"></i>&nbsp;Attendance List</h5>
					</div>
					<div>
						<a class="txt-body-style nav-link " href="${addAttendance }"><i
							class="bi bi-pencil-square"></i>&nbsp;Add Attendance</a>
					</div>
				</div>

			</div>
		</div>

		<form action="${slClassRoom }" class="d-flex justify-content-start mt-2" method="post">
			<div class="me-2">
				<label class="txt-body-style" for="de">Date :&nbsp;</label> <input
					class="date-picker p-1" id="datepicker" type="date" name="date" required />
			</div>
			<div>

				<select class="btn btn-style p-2 me-2">
					<option value="" selected>Select One</option>
					<c:forEach var="cr" items="${classrooms }">

						<option>${cr.getName() }</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<button type="submit" class="btn btn-style">
					<i class="bi bi-search"></i>&nbsp;Search
				</button>
			</div>
		</form>

		<table class="table table-stripped">
			<thead>
				<tr>
					<th>No.</th>
					<th>Name</th>
					<th>Class Room</th>
					<th>Date</th>
					<th>IsPresent</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${attendances != null }">
						<c:forEach var="a" items="${attendances }" varStatus="loop">
							<tr>
								<td>${loop.index+1 }</td>
								<td>${a.getStudent().getName() }</td>
								<td>${a.getStudent().getClassRoom().getName() }</td>
								<td>${a.getAttDate() }</td>
								<td><c:choose>
										<c:when test="${a.isPresent() }">
											<p class="text-success">
												<i class="bi bi-check-lg"></i>
											</p>
										</c:when>
										<c:otherwise>
											<p class="text-danger">
												<i class="bi bi-x-lg"></i>
											</p>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p class="txt-body-style">There is no students</p>
					</c:otherwise>
				</c:choose>

			</tbody>
		</table>


	</div>
</body>
</html>