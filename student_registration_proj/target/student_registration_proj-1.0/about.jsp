<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="head" value="/commons/header.jsp"></c:url>
<c:url var="foot" value="/commons/footer.jsp"></c:url>


<jsp:include page="${head }"></jsp:include>

<div class="row justify-content-center p-5 message-target">
	<div class="col text-dark bg-light mb-3">
		<div class="card-header mt-2">What is University of Technology
			(Yatanarpon Cyber City)?</div>
		<div class="card-body">
			<p class="card-text">It was opened in 2010. It is a member of
				ASEAN Cyber University (ACU) Network. It is opened to produce
				talented engineers and outstanding researchers.</p>
			<h5 class="card-title py-2">Location and Shipping Communications</h5>
			<p class="card-text">It is located on the Mandalay-Lashio road,
				near Pyin Oo Lwin, about 67 km east of Mandalay. Ferry cars operate
				at a price set according to the distance traveled.</p>
				<ul>
					<li>
						<p class="card-text">Number of recruits</p>
					</li>
					<li>
						<p class="card-text">250-300</p>
					</li>
					<li>
						<p class="card-text">Annual changes</p>
					</li>
				</ul>
		
			<h5 class="card-title py-2">Specialized subjects</h5>
			<p class="card-text">Faculty of Information & Communication
				Technology</p>
				<ul>
					<li>
						<p class="card-text">Faculty of Computer Engineering</p>
					</li>
					<li>
						<p class="card-text">Faculty of Electronics Engineering</p>
					</li>
					<li>
						<p class="card-text">Faculty of Mechanical Precision & Automation</p>
					</li>
					<li>
						<p class="card-text">Faculty of Advanced Material Engineering</p>
					</li>
				</ul>
			

			<h5 class="cart-title py-2">Degrees Awarded</h5>
			<p class="cart-text">Attended for 6 years and awarded the
				following degrees.</p>
			<ul>
				<li>
					<div class="card-text">BE (Information Science & Technology)</div>
				</li>
				<li>
					<div class="card-text">BE (Computer Engineering)</div>
				</li>
				<li>
					<div class="card-text">BE (Electronics)</div>
				</li>
				<li>
					<div class="card-text">BE (Mechanical Precision & Automation)</div>
				</li>
				<li>
					<div class="card-text">BE (Material & Metallurgy)</div>
				</li>
			</ul>
			<div class="cart-text">Through that, you can attend from Master
				to Phd.</div>


			<h5 class="card-title py-2 mt-2">Employment opportunities</h5>
			<p class="card-text">You can work as technicians and engineers in
				domestic and foreign organizations.</p>
			<h5 class="card-titel">Student Facilities</h5>
			<ul>
				<li>
					<p class="card-text">Library</p>
				</li>
				<li>
					<p class="card-text">E-Library</p>
				</li>
				<li>
					<p class="card-text">Workshop</p>
				</li>
				<li>
					<p class="card-text">Football playground</p>
				</li>
				<li>
					<p class="card-text">Basketball playground</p>
				</li>
				<li>
					<p class="card-text">ATM machine</p>
				</li>
				<li>
					<p class="card-text">Hostels</p>
				</li>
			</ul>
			<p class="card-text">There are in-school dormitories and out-of-school dormitories.</p>
		</div>
	</div>
</div>

<jsp:include page="${foot }"></jsp:include>