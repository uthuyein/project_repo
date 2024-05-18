<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<div class="row justify-content-center p-5">
	<div class="card col-md-6">
		<div class="card-header mt-2 text-primary">
			<i class="bi bi-person-fill "></i> Add Student Information
		</div>
		<div class="card-body ">
			<form action="/admin/addStudent" method="post"
				enctype="multipart/form-data">
				<div class="row">
					<div class="col mt-4">
						<div>
							<div class="d-flex justify-content-center mb-4">
								<img id="selectedImage" src="/images/No_image.JPG"
									class="rounded-circle"
									style="width: 200px; height: 200px; object-fit: cover;" />
							</div>
						</div>
					</div>
					<div class="col ">
						<div class="mb-3">
							<label for="stuName" class="form-label text-primary">Student's
								Name</label> <input type="text" class="form-control" id="stuName"
								name="stuName">
						</div>

						<div class="mb-3">
							<label for="dob" class="form-label text-primary">Date Of
								Birth</label> <input type="date" class="form-control" id="dob"
								name="dob">
						</div>
						<div class="mb-3">
							<label for="imageFile" class="form-label text-primary">Image
							</label> <input type="file" accept="image/*" class="form-control"
								id="imageFile" name="imageFile"
								onchange="displaySelectedImage(event, 'selectedImage')">
						</div>
					</div>
				</div>
				<div class="mb-3">
					<label for="religion" class="form-label text-primary">Nation
						/ Religion </label> <input type="text" class="form-control" id="religion"
						name="religion">
				</div>
				<div class="mb-3">
					<label for="nrc" class="form-label text-primary">National
						Identity Card(NRC)</label> <input type="text" class="form-control"
						id="nrc" name="nrc">
				</div>
				<div class="mb-3">
					<label for="email" class="form-label text-primary ">Email
						address</label> <input type="email" class="form-control" id="email"
						name="email">
				</div>
				<div class="mb-3">
					<label for="pContact" class="form-label text-primary">Primary
						Contact</label> <input type="text" class="form-control" id="pContact"
						name="pContact">
				</div>
				<div class="mb-3">
					<label for="sContact" class="form-label text-primary">Secondary
						Contact</label> <input type="text" class="form-control" id="sContact"
						name="sContact">
				</div>


				<button type="submit" class="btn btn-primary">Continue >>></button>
			</form>
		</div>

	</div>
</div>
<jsp:include page="${foot }"></jsp:include>

<script>
	function displaySelectedImage(event, elementId) {
		const selectedImage = document.getElementById(elementId);
		const fileInput = event.target;

		if (fileInput.files && fileInput.files[0]) {
			const reader = new FileReader();

			reader.onload = function(e) {
				selectedImage.src = e.target.result;
			};

			reader.readAsDataURL(fileInput.files[0]);
		}
	}
</script>