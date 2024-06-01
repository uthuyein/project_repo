<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<%@page import="com.mkt.ym.entity.type.*"%>

<c:set var="majors" value="<%=Major.values()%>"></c:set>
<c:set var="years" value="<%=UniYear.values()%>"></c:set>

<jsp:include page="${head }"></jsp:include>

<c:url var="addStudent" value="/admin/addStudent"></c:url>

<div id="test" class=" ">
	<div class="row" data-bs-spy="scroll" data-bs-target="#navbar-example"
		style="position: relative">

		<nav id="navbar-example"
			class="navbar navbar-light bg-light px-3 sticky-sm-top">
			<ul class="nav nav-pills">
				<li class="nav-item"><a
					class="nav-link primary-text-color fs-sm" href="#studentId">Student</a></li>
				<li class="nav-item"><a class="nav-link primary-text-color"
					href="#schoolId">School</a></li>
				<li class="nav-item"><a class="nav-link primary-text-color"
					href="#parentId">Parent</a></li>
				<li class="nav-item"><a class="nav-link primary-text-color"
					href="#addressId">Address</a></li>

			</ul>
		</nav>
		<div class="row justify-content-center  p-5 ">
			<div data-bs-spy="scroll" data-bs-target="#navbar-example"
				data-bs-offset="0" class="col col-md-6 scrollspy-example"
				tabindex="0">
				<form action="${addStudent}" method="post"
					enctype="multipart/form-data">
					<div class="card-body ">

						<div id="studentId"
							class="card-header mb-3 primary-header-text-color">
							<i class="bi bi-person-fill "></i> Add Student Information
						</div>
						<div class="row">
							<div class="col mt-4">
								<div>
									<div class="d-flex justify-content-center mb-4">
										<img id="selectedImage" src="/images/uni_images/No_image.JPG"
											class="rounded-circle"
											style="width: 200px; height: 200px; object-fit: cover;" />
									</div>
								</div>
							</div>
							<div class="col ">
								<div class="mb-3">
									<label for="stuName" class="form-label primary-text-color">Student's
										Name</label> <input type="text" class="form-control" id="stuName"
										name="stuName">
								</div>
								<div class="mb-3">
									<label for="dob" class="form-label primary-text-color">Date
										Of Birth</label> <input type="date" class="form-control" id="dob"
										name="dob">
								</div>
								<div class="mb-3">
									<label for="imageFile" class="form-label primary-text-color">Image
									</label> <input type="file" accept="image/*" class="form-control"
										id="imageFile" name="imageFile"
										onchange="displaySelectedImage(event, 'selectedImage')">
								</div>
							</div>
						</div>
						<div class="mb-3">
							<label for="religion" class="form-label primary-text-color">Nation
								/ Religion </label> <input type="text" class="form-control"
								id="religion" name="religion">
						</div>
						<div class="mb-3">
							<label for="nrc" class="form-label primary-text-color">National
								Registration Card(NRC)</label> <input type="text" class="form-control"
								id="nrc" name="nrc">
						</div>
						<div class="mb-3">
							<label for="email" class="form-label primary-text-color ">Email
								address</label> <input type="email" class="form-control" id="email"
								name="email">
						</div>
						<div class="mb-3">
							<label for="pContact" class="form-label primary-text-color">Primary
								Contact</label> <input type="text" class="form-control" id="pContact"
								name="pContact">
						</div>
						<div class="mb-3">
							<label for="sContact" class="form-label primary-text-color">Secondary
								Contact</label> <input type="text" class="form-control" id="sContact"
								name="sContact">
						</div>
						<%-- <div id="universityId"
							class="card-header mt-3 mb-3 primary-header-text-color">
							<i class="bi bi-person-fill"></i> Add Student To University
						</div>
						<div class="mb-3">
							<div class="row">
								<div class="col col-md-5">
									<label for="openYear" class="form-label primary-text-color">University
										Opening Year</label> <input type="text" id="openYear"
										class="form-control" name="openYear" />
								</div>
							</div>
						</div>
						<div class="mb-3">
							<div class="row">
								<div class="col col-md-5">
									<label for="newYear" class="form-label primary-text-color">Student
										New Year</label> <select id="newYear" class="form-select"
										name="newYear">
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
							<label for="newRollNum" class="form-label primary-text-color">Add
								Roll Number</label> <input type="text" class="form-control"
								id="newRollNum" name="newRollNum">
						</div> --%>
						<div id="schoolId"
							class="card-header mt-3 mb-3 primary-header-text-color">
							<i class="bi bi-person-fill"></i> Add School Information
						</div>
						<div class="mb-3">
							<label for="rollNumber" class="form-label primary-text-color">Grade
								12 Roll Number</label> <input type="text" class="form-control"
								id="rollNumber" name="rollNumber">
						</div>

						<div class="mb-3">
							<label for="ttl" class="form-label primary-text-color">Total
								Marks</label> <input type="text" class="form-control" id="ttl"
								name="ttl">
						</div>

						<div id="parentId"
							class="card-header mt-3  mb-3 primary-header-text-color">
							<i class="bi bi-person-fill"></i> Add Parent Information
						</div>
						<div class="mb-3">
							<label for="fName" class="form-label primary-text-color">Father's
								Name</label> <input type="text" class="form-control" id="fName"
								name="fName">
						</div>
						<div class="mb-3">
							<label for="mName" class="form-label primary-text-color">Mother's
								Name</label> <input type="text" class="form-control" id="mName"
								name="mName">
						</div>
						<div class="mb-3">
							<label for="fNrc" class="form-label primary-text-color">Father's
								NRC</label> <input type="text" class="form-control" id="fNrc"
								name="fNrc">
						</div>
						<div class="mb-3">
							<label for="mNrc" class="form-label primary-text-color">Mother's
								NRC</label> <input type="text" class="form-control" id="mNrc"
								name="mNrc">
						</div>
						<div id="addressId"
							class="card-header mt-3 mb-3 primary-header-text-color">
							<i class="bi bi-person-fill"></i> Add Address Information
						</div>
						<div class="mb-3">
							<label for="fName" class="form-label primary-text-color">City</label>
							<input type="text" class="form-control" id="city" name="city">
						</div>
						<div class="mb-3">
							<label for="township" class="form-label primary-text-color">Township</label>
							<input type="text" class="form-control" id="township"
								name="township">
						</div>
						<div class="mb-3">
							<label for="street" class="form-label primary-text-color">Street</label>
							<input type="text" class="form-control" id="street" name="street">
						</div>

						<button type="submit" class="btn primary-color">Save
							Student</button>
					</div>

				</form>
			</div>
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
	var scrollSpy = new bootstrap.ScrollSpy(document.body, {
		target : '#navbar-example'
	})
</script>