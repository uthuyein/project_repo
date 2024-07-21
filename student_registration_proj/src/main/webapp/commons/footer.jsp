

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="home" value="/index.jsp"></c:url>

<nav class=" relative-bottom navbar-expand-lg bg-light ">
	<div class="card text-center ">
		<div class="card-body">
			<h5 class="card-title">University of Technology Yatanarpon Cyber
				City</h5>
			<p class="card-text">This project is used for student
				registration</p>
			<a href="${home }" class="btn primary-color">Go Home Page</a>
		</div>
	</div>
</nav>

<script>
/* for nrc data binding */
function fetchNrc(code,codeName) {
    const selectedcode = document.getElementById(code).value;
    fetch("/nrc.json")
    .then(response => response.json())
    .then(({data}) => {
        const nrcs = data.filter(region => region.nrc_code === selectedcode)
        const resultsDropdown = document.getElementById(codeName);
              resultsDropdown.innerHTML = ''; 
	
            nrcs.forEach(item => {
                const option = document.createElement('option');
                option.value = item.name_en;
                option.text = item.name_en;
                resultsDropdown.add(option);
            });
      });
}
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
		const message = hoverTarget.querySelector('.mess');

		hoverTarget.addEventListener('input', function() {
			message.style.display = 'none';
		});
	});
	
	
</script>

</body>
</html>

