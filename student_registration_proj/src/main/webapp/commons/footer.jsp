

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<nav class=" relative-bottom navbar-expand-lg bg-light ">
	<div class="card text-center ">
		<div class="card-body">
			<h5 class="card-title">University of Technology Yatanarpon Cyber
				City</h5>
			<p class="card-text">This project is used for student
				registration</p>
			<a href="#" class="btn primary-color">Go somewhere</a>
		</div>
	</div>
</nav>

<script>
	/* for image upload */
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
	/* for validation message */
	(function() {
		'use strict'
		var forms = document.querySelectorAll('.needs-validation')
		Array.prototype.slice.call(forms).forEach(function(form) {
			form.addEventListener('submit', function(event) {
				if (!form.checkValidity()) {
					event.preventDefault()
					event.stopPropagation()
				}

				form.classList.add('was-validated')
			}, false)
		})
	})()

	/* for message disapper */
	document.addEventListener('DOMContentLoaded', function() {
		const hoverTarget = document.querySelector('.message-target');
		const message = hoverTarget.querySelector('.message');

		hoverTarget.addEventListener('input', function() {
			message.style.display = 'none';
		});
	});
</script>

</body>
</html>

