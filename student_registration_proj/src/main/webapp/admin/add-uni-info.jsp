<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>

<div class="card col-md-5">
	<div class="card-header mt-2 text-primary">
		<i class="bi bi-person-fill"></i> Add Student To University
	</div>
	<div class="card-body ">
		<form action="/admin/uniStudent" method="post">
			<div class="mb-3">
				<label for="fName" class="form-label text-primary">Student</label>
				 <select class="form-select">
					<option selected>Select One Student</option>
					<option value="1">Aung Aung</option>
					<option value="2">Maung Maung</option>
					<option value="3">Ma Ma</option>
				</select>
			</div>
			<div class="mb-3">
				<div class="row">
					<div class="col col-md-5">
						<label for="mName" class="form-label text-primary">Year</label>  <select class="form-select">
					<option value="1">First Year</option>
					<option value="2">Second Year</option>
					<option value="3">Third Year</option>
					<option value="3">Fourth Year</option>
					<option value="3">Fifth Year</option>
				</select>
					</div>
					<div class="col">
					<label for="mName" class="form-label text-primary">Major(Only For Second Year)</label>  <select class="form-select">
					<option value="1">FE</option>
					<option value="2">SE</option>
					<option value="3">AE</option>
					<option value="3">EE</option>
					<option value="3">JA</option>
				</select>
					</div>
				</div>
				
			</div>
			<div class="mb-3">
				<label for="fNrc" class="form-label text-primary">Add Roll Number</label> <input
					type="text" class="form-control" id="fNrc" name="fNrc">
			</div>
			
			<button type="submit" class="btn btn-primary w-25">Save</button>
		</form>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>