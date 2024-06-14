<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>
<c:url var="stuInfo" value="/student/studentInfo"></c:url>
<c:url var="stuUniInfo" value="/student/stuUniversityInfo"></c:url>

<%@page import="com.mkt.ym.entity.type.*"%>

<jsp:include page="${head }"></jsp:include>
<div class="row">
	
	<c:if test="${null ne uniInfoDto }">
		<div class="row justify-content-center p-5 message-target">
			<div class="col col-md-6">
				<div class="row g-0">
					<div class="col-md-4 mt-4">
						<img src="/images/${uniInfoDto.get(0).image() }" class="img-fluid rounded-start" alt="...">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title primary-header-text-color"><span>Name : </span>${uniInfoDto.get(0).name() }</h5>
							<p class="card-text"><span class="primary-text-color">Date Of Birth : </span>${uniInfoDto.get(0).dob() }</p>
							<p class="card-text"><span class="primary-text-color">Religion : </span>${uniInfoDto.get(0).religion() }</p>
							<p class="card-text"><span class="primary-text-color">Nrc Nuumber : </span>${uniInfoDto.get(0).nrc() }</p>					
							<p class="card-text"><span class="primary-text-color">Primary Phone : </span>${uniInfoDto.get(0).primaryPhone() }</p>
							<p class="card-text"><span class="primary-text-color">Secondary Phone : </span>${uniInfoDto.get(0).secondaryPhone() }</p>	
							<p class="card-text"><span class="primary-text-color">Father Name / NRC : </span>${uniInfoDto.get(0).fName() } / ${uniInfoDto.get(0).fNrc() }</p>
							<p class="card-text"><span class="primary-text-color">Mother Name / NRC : </span>${uniInfoDto.get(0).mName() } / ${uniInfoDto.get(0).mNrc() }</p>
							<p class="card-text"><span class="primary-text-color">Nrc Nuumber : </span>${uniInfoDto.get(0).nrc() }</p>				
							<p class="card-text"><span class="primary-text-color">City : </span>${uniInfoDto.get(0).city() }</p>
							<p class="card-text"><span class="primary-text-color">Township : </span>${uniInfoDto.get(0).township() }</p>
							<p class="card-text"><span class="primary-text-color">Street : </span>${uniInfoDto.get(0).street() }</p>						
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</c:if>
</div>


<jsp:include page="${foot }"></jsp:include>
