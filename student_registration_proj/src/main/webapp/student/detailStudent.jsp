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
				class="nav-link primary-text-color fs-sm"  href="${stuDetail }?id=${uniInfoDto.stuId()}" >Student</a></li>
			<li class="nav-item "><a
				class="nav-link primary-text-color" href="${payment }?id=${uniInfoDto.stuId()}">Payment</a></li>
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
							${messengers.size()}
						</span>
					</c:if>
			</a></li>

		</ul>
	</nav>
	<c:if test="${null ne uniInfoDto }">
	<c:set var="u" value="${uniInfoDto }"></c:set>
		<div class="row justify-content-center p-5 message-target">
			<div class="col col-md-6">
				<div class="row g-0">
					<div class="col-md-4 mt-4">
						<img src="/images/${u.image() }" class="img-fluid rounded-start" alt="...">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title primary-header-text-color"><span>Name : </span>${u.name() }</h5>
							<p class="card-text"><span class="primary-text-color">Date Of Birth : </span>${u.dob() }</p>
							<p class="card-text"><span class="primary-text-color">Religion : </span>${u.religion() }</p>
							<p class="card-text"><span class="primary-text-color">Nrc Nuumber : </span>${u.nrc() }</p>					
							<p class="card-text"><span class="primary-text-color">Primary Phone : </span>${u.primaryPhone() }</p>
							<p class="card-text"><span class="primary-text-color">Secondary Phone : </span>${u.secondaryPhone() }</p>	
							<p class="card-text"><span class="primary-text-color">Father Name / NRC : </span>${u.fName() } / ${u.fNrc() }</p>
							<p class="card-text"><span class="primary-text-color">Mother Name / NRC : </span>${u.mName() } / ${u.mNrc() }</p>
							<p class="card-text"><span class="primary-text-color">Nrc Nuumber : </span>${u.nrc() }</p>				
							<p class="card-text"><span class="primary-text-color">City : </span>${u.city() }</p>
							<p class="card-text"><span class="primary-text-color">Township : </span>${u.township() }</p>
							<p class="card-text"><span class="primary-text-color">Street : </span>${u.street() }</p>						
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</c:if>
</div>


<jsp:include page="${foot }"></jsp:include>
