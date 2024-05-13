<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<body>

	<c:url var="addStudent" value="/admin/add-student"></c:url>
	<c:url var="editStudent" value="/admin/edit-student"></c:url>
	<c:url var="deleteStudent" value="/admin/delete-student"></c:url>
	<c:url var="slClassRoom" value="/select-by-classroom"></c:url>

	<div class="container mt-4">

		<div class="card">
			<div class="card-header txt-body-style">
				<div class="card-title d-flex justify-content-between ">
					<div class="mt-2">
						<h5><i class="bi bi-file-earmark-person-fill"></i>&nbsp;Student List</h5>
					</div>
					<div>
						<a class="txt-body-style nav-link " href="${addStudent }"><i
							class="bi bi-pencil-square"></i>&nbsp;Add New Student</a>
					</div>
				</div>

			</div>


		</div>

		<div class=" mt-2 mb-2">


			<select class="btn btn-style p-2"
				onchange="if (this.value) window.location.href=this.value">
				<option value="" selected>Select One</option>
				<c:forEach var="cr" items="${classrooms }">

					<option value="${ slClassRoom}?room=${cr.getName()}">
						${cr.getName() }</option>
				</c:forEach>
			</select>


		</div>

		<table class="table table-stripped">
			<thead>
				<tr>
					<th>No.</th>
					<th>Name</th>
					<th>Class Room</th>
					<th>Contact</th>
					<th>Email</th>
					<th>City</th>
					<th>Township</th>
					<th>Street</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ students !=null}">

						<c:forEach var="s" items="${students }" varStatus="loop">
							<tr>
								<td>${loop.index+1 }</td>
								<td>${s.getName() }</td>
								<td>${s.getClassRoom().getName() }</td>
								<td>${s.getPhone() }</td>
								<td>${s.getEmail() }</td>
								<td>${s.getAddress().getCity() }</td>
								<td>${s.getAddress().getTownship() }</td>
								<td>${s.getAddress().getStreet() }</td>
								<td>
									<div class="d-flex justify-content-end mb-2">
										<a class="btn btn-style me-2"
											href="${editStudent}?id=${s.getId() }">edit</a> <a
											class="btn btn-style"
											href="${deleteStudent}?id=${s.getId() }">delete</a>
									</div>
								</td>
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