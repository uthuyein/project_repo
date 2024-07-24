<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="stuDetail" value="/student/detailStudent"></c:url>
<c:url var="payment" value="/student/addPayment"></c:url>
<c:url var="acc" value="/student/addAccount"></c:url>
<c:url var="messager" value="/student/messenger"></c:url>



<%@page import="com.mkt.ym.entity.type.*"%>

<jsp:include page="${head }"></jsp:include>
<div class="row">
	<nav id="navbar-example"
		class="navbar navbar-light bg-light px-3 sticky-sm-top">
		<ul class="nav">
			<li class="nav-item"><a
				class="nav-link primary-text-color fs-sm"
				href="${stuDetail }?id=${uniInfoDto.stuId()}">Student</a></li>
			<li class="nav-item "><a class="nav-link primary-text-color"
				href="${payment }?id=${uniInfoDto.stuId()}">Payment</a></li>
			<c:if test="${null == account }">
				<li class="nav-item"><a class="nav-link primary-text-color"
					href="${acc }?id=${uniInfoDto.stuId()}">Account</a></li>
			</c:if>
			<li class="nav-item"><a
				class="nav-link primary-text-color position-relative"
				href="${messager }?id=${uniInfoDto.stuId()}">Messager <c:if
						test="${null ne messengers && messengers.size() > 0 }">
						<span
							class="position-absolute top-25 start-75 translate-middle badge rounded-pill bg-danger">
							${messengers.size()} </span>
					</c:if>
			</a></li>

		</ul>
	</nav>
	<c:if test="${null ne uniInfoDto }">
		<c:set var="u" value="${uniInfoDto }"></c:set>
		<div class="row justify-content-center p-5 message-target">
			<div class="col col-md-8">
				<div class="row">
					<div class="col-md-4 mt-5">
						<img src="/images/${u.image() }" class="img-thumbnail "
							style="width: 300px; height: 300px; object-fit: cover;" alt="...">
					</div>
					<div class="col-md-8 ">
						<table class="table">
							<tbody>
								<tr>
									<td class="primary-header-text-color">Name :</td>
									<td class="primary-header-text-color" >${u.name() }</td>
								</tr>
								<tr>
									<td class="primary-text-color">Date Of Birth :</td>
									<td class="text-secondary">${u.dob() }</td>
								</tr>
								<tr>
									<td class="primary-text-color">Religion :</td>
									<td class="text-secondary">${u.religion() }</td>
								</tr>
								<tr>
									<td class="primary-text-color">NRC Number :</td>
									<td class="text-secondary">${u.nrc() }</td>
								</tr>
								<tr>
									<td class="primary-text-color">Primary Phone :</td>
									<td class="text-secondary">${u.primaryPhone() }</td>
								</tr>
								<tr>
									<td class="primary-text-color">Secondary Phone :</td>
									<td class="text-secondary">${u.secondaryPhone() }</td>
								</tr>
								<tr>
									<td class="primary-text-color">Father Name / NRC :</td>
									<td class="text-secondary">${u.fName() }&nbsp;( ${u.fNrc() } )</td>

								</tr>
								<tr>
									<td class="primary-text-color">Mother Name / NRC :</td>
									<td class="text-secondary">${u.mName() } &nbsp;( ${u.mNrc() } )</td>

								</tr>
								<tr>
									<td class="primary-text-color">City :</td>
									<td class="text-secondary">${u.city() }</td>
								</tr>
								<tr>
									<td class="primary-text-color">Township :</td>
									<td class="text-secondary">${u.township() }</td>
								</tr>
								<tr>
									<td class="primary-text-color">Street :</td>
									<td class="text-secondary">${u.street() }</td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>

	</c:if>
</div>


<jsp:include page="${foot }"></jsp:include>
