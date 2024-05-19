<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.mkt.ym.entity.type.*"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>

<jsp:include page="${head }"></jsp:include>
<div class="row justify-content-center p-5">
	<div class="card col-md-5">
		<div class="card-header mt-2 text-primary">
			<i class="bi bi-person-fill"></i> Add Old Student To University
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
							<label for="mName" class="form-label text-primary">Year</label> <select
								class="form-select">
								<c:set var="years" value="<%=Year.values()%>"></c:set>
								<c:forEach var="y" items="${years }">
									<option value="${y}">${y.name() }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col">
							<label for="mName" class="form-label text-primary">Major</label>
							<select class="form-select">
								<c:set var="majors" value="<%=Major.values()%>"></c:set>
								<c:forEach var="m" items="${majors }">
									<option value="${m }">${m.name() }</option>
								</c:forEach>

							</select>
						</div>
					</div>

				</div>
				<div class="mb-3">
					<label for="fNrc" class="form-label text-primary">Add Roll
						Number</label> <input type="text" class="form-control" id="fNrc"
						name="fNrc">
				</div>
				<button type="submit" class="btn btn-primary w-25">Save</button>
			</form>
		</div>
	</div>
</div>
<jsp:include page="${foot }"></jsp:include>