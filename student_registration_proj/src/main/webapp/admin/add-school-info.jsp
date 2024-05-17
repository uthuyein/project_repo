<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<div class="card col-md-5">
	<div class="card-header mt-2 text-primary">
		<i class="bi bi-person-fill"></i> Add School Information
	</div>
	<div class="card-body ">
		<form action="/admin/schoolInfo" method="post">
			<div class="mb-3">
				<label for="rollNumber" class="form-label text-primary">Grade 12 Roll
					Number</label> <input type="text" class="form-control" id="rollNumber"
					name="rollNumber">
			</div>
			<div class="row">
				<div class="col col-md-3">
					<div class="mb-3">
						<label for="eng" class="form-label text-primary">English</label> <input
							type="text" class="form-control" id="eng" name="eng">
					</div>
				</div>
				<div class="col col-md-3">
					<div class="mb-3">
						<label for="maths" class="form-label text-primary">Mathematics:</label> <input
							type="text" class="form-control" id="maths" name="maths">
					</div>
				</div>
				<div class="col col-md-3">
					<div class="mb-3">
						<label for="phys" class="form-label text-primary">Physics</label> <input
							type="text" class="form-control" id="phys" name="phys">
					</div>
				</div>
				<div class="col col-md-3">
					<div class="mb-3">
						<label for="chem" class="form-label text-primary">Chemistry</label> <input
							type="text" class="form-control" id="chem" name="chem">
					</div>
				</div>

			</div>

			<div class="mb-3">
				<label for="ttl" class="form-label text-primary">Total Marks</label> <input
					type="text" class="form-control" id="ttl" name="ttl">
			</div>


			<button type="submit" class="btn btn-primary">Save</button>
		</form>
	</div>
</div>

<jsp:include page="${foot }"></jsp:include>