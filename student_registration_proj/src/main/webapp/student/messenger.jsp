<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="payment" value="/student/addPayment"></c:url>
<c:url var="acc" value="/student/addAccount"></c:url>
<c:url var="stuDetail" value="/student/detailStudent"></c:url>
<c:url var="messager" value="/student/messenger"></c:url>
<c:url var="deleteMessage" value="/student/deleteMessenges"></c:url>


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

	<div class="row justify-content-center mt-3 ">
		<div class="col col-md-8">

			<c:choose>
				<c:when test="${null ne messengers && messengers.size() > 0}">
	
					<c:forEach var="m" items="${messengers }" varStatus="s">

						<div class="card text-start mb-2">
							<div class="card-header">
								<p class="${m.message.getColor() }">${m.message }</p>
							</div>
							<div class="card-body">
								<p class="card-text">${m.text }</p>
								<a href="${deleteMessage }?messengerId=${m.id}&id=${m.student.id}"
									class="btn btn-primary">Delete message</a>
							</div>
							<div class="card-footer text-muted text-end">
								<p class="fs-6 fw-lighter">${m.times.createTime.year }&nbsp;
									${m.times.createTime.month }&nbsp;
									${m.times.createTime.dayOfMonth }&nbsp;&nbsp;&nbsp;
									${m.times.createTime.hour }hrs &nbsp;
									${m.times.createTime.minute }minutes &nbsp;
									${m.times.createTime.second }seconds &nbsp;</p>
							</div>
						</div>

					</c:forEach>
				</c:when>

				<c:otherwise>
					<div class="alert alert-success mt-3" role="alert">
						<h4 class="alert-heading">Well done!</h4>
						<p>There is no messsage yet !</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<jsp:include page="${foot }"></jsp:include>
