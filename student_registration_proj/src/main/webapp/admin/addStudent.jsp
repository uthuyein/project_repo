<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mkt.ym.entity.type.*"%>


<c:set var="majors" value="<%=Major.values()%>"></c:set>
<c:set var="years" value="<%=UniYear.values()%>"></c:set>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="addStudent" value="/admin/addStudent"></c:url>
<c:url var="listStudent" value="/admin/studentList"></c:url>
<c:url var="nrc" value="/student/nrc"></c:url>
<jsp:include page="${head }"></jsp:include>

<div class="row">

	<nav id="navbar-example"
		class="navbar navbar-light bg-light px-3 sticky-sm-top">
		<ul class="nav">
			<li class="nav-item"><a href="${addStudent }"
				class="nav-link primary-text-color"><i class="bi bi-person-fill"></i>&nbsp;Add
					Student</a></li>
			<li class="nav-item"><a href="${listStudent }"
				class="nav-link primary-text-color"><i
					class="bi bi-person-lines-fill"></i>&nbsp;Student List</a></li>
		</ul>
	</nav>
	<div class="row justify-content-center p-5 message-target">
		<div class="col col-md-5">
			<c:if test="${null ne message }">
				<span class="mess"> <label
					class="alert d-flex p-2 ${message.getColor() } ">${message.message}
				</label>
				</span>
			</c:if>
			<div class="card">
				<c:choose>
					<c:when test="${null != studentDto }">
						<c:set var="s" value="${studentDto }"></c:set>
						<form action="${addStudent}" method="post"
							enctype="multipart/form-data" class="needs-validation" novalidate>
							<input type="text" class="form-control" id="stuId" name="stuId"
								value="${s.id() }" hidden="true">
							<div class="card-body ">

								<div id="studentId"
									class="card-header mb-3 primary-header-text-color">
									<i class="bi bi-person-fill "></i>&nbsp;Update Student
									Information
								</div>
								<div class="row">
									<div class="col mt-4">
										<div>
											<div class="d-flex justify-content-center mb-4">
												<img id="selectedImage"
													src="/images/uni_images/no-image.JPG"
													class="rounded-circle"
													style="width: 200px; height: 200px; object-fit: cover;" />
											</div>
										</div>
									</div>

									<div class="col ">
										<div class="mb-3">
											<label for="stuName" class="form-label primary-text-color">Student's
												Name</label> <input type="text" class="form-control" id="stuName"
												name="stuName" required="required" value="${s.name() }">
											<div class="invalid-feedback">Please re-enter student's
												name !</div>
										</div>
										<div class="mb-3">
											<label for="dob" class="form-label primary-text-color">Date
												Of Birth</label> <input type="date" class="form-control" id="dob"
												name="dob" required="required" value="${s.dob() }">
											<div class="invalid-feedback">Please re-enter student's
												date of birth !</div>
										</div>
										<div class="mb-3">
											<label for="imageFile" class="form-label primary-text-color">Add
												Photo </label> <input type="file" accept="image/*"
												class="form-control" id="imageFile" name="imageFile"
												onchange="displaySelectedImage(event, 'selectedImage')"
												required="required" value="${s.image() }">
											<div class="invalid-feedback">Please re-enter student's
												image !</div>
										</div>
									</div>
								</div>
								<div class="mb-3">
									<label for="religion" class="form-label primary-text-color">Nation
										/ Religion </label> <input type="text" class="form-control"
										id="religion" name="religion" required="required"
										value="${s.religion() }">
									<div class="invalid-feedback">Please re-enter
										nation/region !</div>
								</div>
								<div class="mb-3">
									<label for="nrc" class="form-label primary-text-color">National
										Registration Card(NRC)</label>
									<div class="row">
										<input type="text" name="type" value="" hidden="true" /> <input
											type="text" name="form" value="editStudent" hidden="true" />
										<div class="col col-sm-2">
											<select id="code" name="code" class="form-select"
												onchange="fetchNrc('code','codeName')">
												<option value="${code }">${code }</option>
												<c:forEach var="n" items="${nrcCodes }">
													<option>${n}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col col-sm-4">
											<select id="codeName" name="codeName" class="form-select">
												<option value="${codeName }">${codeName }</option>

											</select>
										</div>

										<div class="col ">
											<input type="text" class="form-control" id="codeNum"
												name="codeNum" required="required" value="${codeNum }">
											<div class="invalid-feedback">Please re-enter student's
												nrc !</div>
										</div>
									</div>
								</div>

								<div class="mb-3">
									<label for="pContact" class="form-label primary-text-color">Primary
										Contact</label> <input type="text" class="form-control" id="pContact"
										name="pContact" required="required"
										value="${s.primaryContact() }">
									<div class="invalid-feedback">Please re-enter primary
										phone !</div>
								</div>
								<div class="mb-3">
									<label for="sContact" class="form-label primary-text-color">Secondary
										Contact</label> <input type="text" class="form-control" id="sContact"
										name="sContact" required="required"
										value="${s.secondaryContact() }">
									<div class="invalid-feedback">Please re-enter secondary
										phone !</div>
								</div>
								<div id="schoolId"
									class="card-header mt-3 mb-3 primary-header-text-color">
									School Information</div>
								<div class="mb-3">
									<label for="rollNumber" class="form-label primary-text-color">Grade
										12 Roll Number</label> <input type="text" class="form-control"
										id="rollNumber" name="rollNumber" required="required"
										value="${s.rollNum() }">
									<div class="invalid-feedback">Please re-enter grade 12
										school roll number !</div>
								</div>

								<div class="mb-3">
									<label for="ttl" class="form-label primary-text-color">Total
										Marks</label> <input type="text" class="form-control" id="ttl"
										name="ttl" required="required" value="${s.totalMarks() }">
									<div class="invalid-feedback">Please re-enter grade 12
										total marks !</div>
								</div>

								<div id="parentId"
									class="card-header mt-3  mb-3 primary-header-text-color">
									Parent Information</div>
								<div class="mb-3">
									<label for="fName" class="form-label primary-text-color">Father's
										Name</label> <input type="text" class="form-control" id="fName"
										name="fName" required="required" value="${s.fName() }">
									<div class="invalid-feedback">Please re-enter father name
										!</div>
								</div>
								<div class="mb-3">
									<label for="mName" class="form-label primary-text-color">Mother's
										Name</label> <input type="text" class="form-control" id="mName"
										name="mName" required="required" value="${s.mName() }">
									<div class="invalid-feedback">Please re-enter mother name
										!</div>
								</div>
								<div class="mb-3">
									<label for="fNrc" class="form-label primary-text-color">Father's
										NRC</label>
									<div class="row">
										<input type="text" name="type" value="f" hidden="true" /> <input
											type="text" name="form" value="editStudent" hidden="true" />
										<div class="col col-sm-2">
											<select id="fcode" name="fcode" class="form-select"
												onchange="fetchNrc('fcode','fcodeName')">
												<option value="${fcode }">${fcode }</option>
												<c:forEach var="n" items="${nrcCodes }">
													<option>${n}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col col-sm-4">
											<select id="fcodeName" name="fcodeName" class="form-select">
												<option value="${fcodeName }">${fcodeName }</option>

											</select>
										</div>
										<div class="col ">
											<input type="text" class="form-control" id="fcodeNum"
												name="fcodeNum" value="${fcodeNum}" required="required">
											<div class="invalid-feedback">Please re-enter father's
												nrc !</div>
										</div>
									</div>
								</div>
								<div class="mb-3">
									<label for="mNrc" class="form-label primary-text-color">Mother's
										NRC</label>
									<div class="row">
										<input type="text" name="type" value="m" hidden="true" /> <input
											type="text" name="form" value="editStudent" hidden="true" />
										<div class="col col-sm-2">
											<select id="mcode" name="mcode" class="form-select"
												onchange="fetchNrc('mcode','mcodeName')">
												<option value="${mcode }">${mcode }</option>

												<c:forEach var="n" items="${nrcCodes }">
													<option>${n}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col col-sm-4">
											<select id="mcodeName" name="mcodeName" class="form-select">
												<option value="${mcodeName }">${mcodeName }</option>

											</select>
										</div>
										<div class="col ">
											<input type="text" class="form-control" id="mcodeNum"
												name="mcodeNum" required="required" value="${mcodeNum }">
											<div class="invalid-feedback">Please re-enter mother's
												nrc !</div>
										</div>

									</div>
								</div>
								<div id="addressId"
									class="card-header mt-3 mb-3 primary-header-text-color">
									Address Information</div>
								<div class="mb-3">
									<label for="fName" class="form-label primary-text-color">City</label>
									<input type="text" class="form-control" id="city" name="city"
										required="required" value="${s.city() }">
									<div class="invalid-feedback">Please re-enter city !</div>
								</div>
								<div class="mb-3">
									<label for="township" class="form-label primary-text-color">Township</label>
									<input type="text" class="form-control" id="township"
										name="township" required="required" value="${s.township() }">
									<div class="invalid-feedback">Please re-enter township !</div>
								</div>
								<div class="mb-3">
									<label for="street" class="form-label primary-text-color">Street</label>
									<input type="text" class="form-control" id="street"
										name="street" required="required" value="${s.street() }">
									<div class="invalid-feedback">Please re-enter street!</div>
								</div>
								<div class="nav justify-content-end">
									<button type="submit" class="btn primary-color">Update
										Student</button>
								</div>
							</div>
						</form>
					</c:when>


					<c:otherwise>
						<form action="${addStudent}" method="post"
							enctype="multipart/form-data" class="needs-validation" novalidate>
							<div class="card-body ">

								<div id="studentId"
									class="card-header mb-3 primary-header-text-color">
									<i class="bi bi-person-fill "></i> &nbsp;Add Student
									Information
								</div>
								<div class="row">
									<div class="col mt-4">
										<div>
											<div class="d-flex justify-content-center mb-4">
												<img id="selectedImage"
													src="/images/uni_images/no-image.JPG"
													class="rounded-circle"
													style="width: 200px; height: 200px; object-fit: cover;" />
											</div>
										</div>
									</div>

									<div class="col ">
										<div class="mb-3">
											<label for="stuName" class="form-label primary-text-color">Student's
												Name</label> <input type="text" class="form-control" id="stuName"
												name="stuName" required="required">
											<div class="invalid-feedback">Please re-enter student's
												name !</div>
										</div>
										<div class="mb-3">
											<label for="dob" class="form-label primary-text-color">Date
												Of Birth</label> <input type="date" class="form-control" id="dob"
												name="dob" required="required">
											<div class="invalid-feedback">Please re-enter student's
												date of birth !</div>
										</div>
										<div class="mb-3">
											<label for="imageFile" class="form-label primary-text-color">Add
												Photo </label> <input type="file" accept="image/*"
												class="form-control" id="imageFile" name="imageFile"
												onchange="displaySelectedImage(event, 'selectedImage')"
												required="required">
											<div class="invalid-feedback">Please re-enter student's
												image !</div>
										</div>
									</div>
								</div>
								<div class="mb-3">
									<label for="religion" class="form-label primary-text-color">Nation
										/ Religion </label> <input type="text" class="form-control"
										id="religion" name="religion" required="required">
									<div class="invalid-feedback">Please re-enter
										nation/region !</div>
								</div>
								<div class="mb-3">
									<label for="nrc" class="form-label primary-text-color">National
										Registration Card(NRC)</label>
									<div class="row">
										<input type="text" name="type" value="" hidden="true" /> <input
											type="text" name="form" value="addStudent" hidden="true" />
										<div class="col col-sm-2">
											<select id="code" name="code" class="form-select"
												onchange="fetchNrc('code','codeName')">
												<c:forEach var="n" items="${nrcCodes }">
													<option>${n}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col col-sm-4">
											<select id="codeName" name="codeName" class="form-select">
											</select>
										</div>

										<div class="col ">
											<input type="text" class="form-control" id="codeNum"
												name="codeNum" required="required">
											<div class="invalid-feedback">Please re-enter student's
												nrc !</div>
										</div>

									</div>
								</div>

								<div class="mb-3">
									<label for="pContact" class="form-label primary-text-color">Primary
										Contact</label> <input type="text" class="form-control" id="pContact"
										name="pContact" required="required">
									<div class="invalid-feedback">Please re-enter primary
										phone !</div>
								</div>
								<div class="mb-3">
									<label for="sContact" class="form-label primary-text-color">Secondary
										Contact</label> <input type="text" class="form-control" id="sContact"
										name="sContact" required="required">
									<div class="invalid-feedback">Please re-enter secondary
										phone !</div>
								</div>
								<div id="schoolId"
									class="card-header mt-3 mb-3 primary-header-text-color">
									School Information</div>
								<div class="mb-3">
									<label for="rollNumber" class="form-label primary-text-color">Grade
										12 Roll Number</label> <input type="text" class="form-control"
										id="rollNumber" name="rollNumber" required="required">
									<div class="invalid-feedback">Please re-enter grade 12
										school roll number !</div>
								</div>

								<div class="mb-3">
									<label for="ttl" class="form-label primary-text-color">Total
										Marks</label> <input type="text" class="form-control" id="ttl"
										name="ttl" required="required">
									<div class="invalid-feedback">Please re-enter grade 12
										total marks !</div>
								</div>

								<div id="parentId"
									class="card-header mt-3  mb-3 primary-header-text-color">
									Parent Information</div>
								<div class="mb-3">
									<label for="fName" class="form-label primary-text-color">Father's
										Name</label> <input type="text" class="form-control" id="fName"
										name="fName" required="required">
									<div class="invalid-feedback">Please re-enter father name
										!</div>
								</div>
								<div class="mb-3">
									<label for="mName" class="form-label primary-text-color">Mother's
										Name</label> <input type="text" class="form-control" id="mName"
										name="mName" required="required">
									<div class="invalid-feedback">Please re-enter mother name
										!</div>
								</div>
								<div class="mb-3">
									<label for="fNrc" class="form-label primary-text-color">Father's
										NRC</label>
									<form id="selectFatherNrcAdd" action="${nrc }">
										<div class="row">
											<input type="text" name="type" value="f" hidden="true" /> <input
												type="text" name="form" value="addStudent" hidden="true" />
											<div class="col col-sm-2">
												<select id="fcode" name="fcode" class="form-select"
													onchange="fetchNrc('fcode','fcodeName')">
													<c:forEach var="n" items="${nrcCodes }">
														<option>${n}</option>
													</c:forEach>
												</select>
											</div>
											<div class="col col-sm-4">
												<select id="fcodeName" name="fcodeName" class="form-select">
												</select>
											</div>

											<div class="col ">
												<input type="text" class="form-control" name="fcodeNum"
													required="required">
												<div class="invalid-feedback">Please re-enter father's
													nrc !</div>
											</div>
										</div>
									</form>
								</div>
								<div class="mb-3">

									<label for="mNrc" class="form-label primary-text-color">Mother's
										NRC</label>

									<div class="row">

										<input type="text" name="type" value="m" hidden="true" /> <input
											type="text" name="form" value="addStudent" hidden="true" />

										<div class="col col-sm-2">
											<select id="mcode" name="mcode" class="form-select"
												onchange="fetchNrc('mcode','mcodeName')">
												<c:forEach var="n" items="${nrcCodes }">
													<option>${n}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col col-sm-4">
											<select id="mcodeName" name="mcodeName" class="form-select">
											</select>
										</div>

										<div class="col ">
											<input type="text" class="form-control" name="mcodeNum"
												required="required">
											<div class="invalid-feedback">Please re-enter mother's
												nrc !</div>
										</div>

									</div>
								</div>
								<div id="addressId"
									class="card-header mt-3 mb-3 primary-header-text-color">
									Address Information</div>
								<div class="mb-3">
									<label for="fName" class="form-label primary-text-color">City</label>
									<input type="text" class="form-control" id="city" name="city"
										required="required">
									<div class="invalid-feedback">Please re-enter city !</div>
								</div>
								<div class="mb-3">
									<label for="township" class="form-label primary-text-color">Township</label>
									<input type="text" class="form-control" id="township"
										name="township" required="required">
									<div class="invalid-feedback">Please re-enter township !</div>
								</div>
								<div class="mb-3">
									<label for="street" class="form-label primary-text-color">Street</label>
									<input type="text" class="form-control" id="street"
										name="street" required="required">
									<div class="invalid-feedback">Please re-enter street!</div>
								</div>
								<div class="nav justify-content-end">
									<button type="submit" class="btn primary-color">Save
										Student</button>
								</div>
							</div>
						</form>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>
</div>

<jsp:include page="${foot }"></jsp:include>
