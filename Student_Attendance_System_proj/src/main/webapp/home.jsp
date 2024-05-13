<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<body>

	
	<div class="container mt-2">
		<div class="row">
			<div class="col-md-9">
				<c:choose>
					<c:when test="${courses != null }">
						<c:forEach var="co" items="${courses }">

							<div class="card p-3 mt-3 mb-2">
								<div class="card-title"><h4 class="txt-body-style">${co.getName() }</h4></div>
								<div class="card-body">${co.getDescription() }</div>
							</div>

						</c:forEach>
					</c:when>
					<c:otherwise>
						<p class="txt-body-style">There is no course yet !</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-md-3">
				<ul class="list-group mt-3">

					<c:forEach var="co" items="${courses }">
						<li class="list-group-item "><a href="" class="txt-body-style"><i class="bi bi-bookmark-star-fill"></i>&nbsp; ${co.getName()}</a></li>

					</c:forEach>


				</ul>
			</div>
		</div>
	</div>

</body>
</html>