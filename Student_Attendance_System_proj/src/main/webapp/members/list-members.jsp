<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<body>

	<c:url var="addMember" value="/admin/add-member"></c:url>
	<c:url var="editMember" value="/admin/edit-member"></c:url>
	<c:url var="deleteMember" value="/admin/delete-member"></c:url>
	<div class="container mt-4">

		<div class="card">
			<div class="card-header txt-body-style">
				<div class="card-title d-flex justify-content-between ">
					<div class="mt-2">
						<h5><i class="bi bi-person-lines-fill"></i>&nbsp;Member List</h5>
					</div>
					<div>
						<a class="txt-body-style nav-link " href="${addMember }"><i
							class="bi bi-pencil-square"></i>&nbsp;Add New Member</a>
					</div>
				</div>



			</div>
		</div>

		<c:choose>
			<c:when test="${members != null }">

				<table class="table table-stripped">
					<thead>
						<tr>
							<th>No.</th>
							<th>Name</th>
							<th>Password</th>
							<th>Role</th>
							<th></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="m" items="${members }" varStatus="l">

							<tr>
								<td>${l.index+1 }</td>
								<td>${m.getName() }</td>
								<td>${m.getPassword() }</td>
								<td>${m.getRole() }</td>
								<td>
									<div class="d-flex justify-content-end mb-2">
										<a class="btn btn-style me-2" href="${editMember }?id=${m.getId()}">edit</a>
										<a class="btn btn-style" href="${deleteMember }?id=${m.getId()}">delete</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p class="txt-body-style">There is no members !</p>
			</c:otherwise>
		</c:choose>




	</div>
</body>
</html>