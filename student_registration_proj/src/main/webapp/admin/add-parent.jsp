<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<div class="row justify-content-center p-5">
<div class="card col-md-5">
	<div class="card-header mt-2  primary-header-text-color">
		<i class="bi bi-person-fill"></i> Add Parent Information
	</div>
	<div class="card-body ">
			<form action="/admin/addParent" method="post">
				<div class="mb-3">
					<label for="fName" class="form-label primary-text-color">Father's Name</label> <input
						type="text" class="form-control" id="fName" name="fName" >
				</div>
				<div class="mb-3">
					<label for="mName" class="form-label primary-text-color">Mother's Name</label> <input
						type="text" class="form-control" id="mName" name="mName">
				</div>
				<div class="mb-3">
					<label for="fNrc" class="form-label primary-text-color">Father's NRC</label> <input
						type="text" class="form-control" id="fNrc" name="fNrc">
				</div>
				<div class="mb-3">
					<label for="mNrc" class="form-label primary-text-color">Mother's NRC</label> <input
						type="text" class="form-control" id="mNrc" name="mNrc">
				</div>

				<button type="submit" class="btn primary-color">Continue >>></button>
			</form>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>