<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>


<jsp:include page="${head }"></jsp:include>
<div id="carouselExampleControls" class="carousel slide"
	data-bs-ride="carousel">
	<div class="carousel-inner">
		<div class="carousel-item active">
			<img src="/images/uni_images/utycc1.jpeg" style="height:50rem;"  class="d-block w-100"alt="...">
		</div>
		<div class="carousel-item">
			<img src="/images/uni_images/utycc2.jpeg" style="height:50rem;" class="d-block w-100 " alt="...">
		</div>
		<div class="carousel-item">
			<img src="/images/uni_images/utycc3.jpeg" style="height:50rem;" class="d-block w-100 " alt="...">
		</div>
		<div class="carousel-item">
			<img src="/images/uni_images/utycc4.jpeg" style="height:50rem;" class="d-block w-100 " alt="...">
		</div>
	</div>
	<button class="carousel-control-prev" type="button"
		data-bs-target="#carouselExampleControls" data-bs-slide="prev">
		<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Previous</span>
	</button>
	<button class="carousel-control-next" type="button"
		data-bs-target="#carouselExampleControls" data-bs-slide="next">
		<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Next</span>
	</button>
</div>
<jsp:include page="${foot }"></jsp:include>