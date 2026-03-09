<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, ma.est.models.Etudiant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bienvenue sur notre API Etudiant JSON/XML</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
	<%  
		if(request.getAttribute("liste") != null){
		
			List<Etudiant> liste = (ArrayList<Etudiant>)request.getAttribute("liste");
			
	%>
	<div class="container">
		<br>
		<figure class="d-flex justify-content-between align-items-center">
			<blockquote class="blockquote mb-0">
				<h1 class="h1">API JSON/XML</h1>
				<figcaption class="blockquote-footer mb-0 badge text-bg-info">
					<span>Bienvenue sur notre API ETUDIANT</span>
				</figcaption>
			</blockquote>

		</figure>

		<div class="container-sm my-4 p-3 bg-light rounded shadow-sm">
			<div class="card mb-3">
				<div class="row g-0">
					<div class="col-md-4">
						<img src="<%=request.getContextPath()%>/resources/images/img1.png" class="img-fluid rounded-start">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<div class="d-flex justify-content-between" style="margin-bottom: 0px;">
								<h5 class="card-title">Consulter La liste des étudiants</h5>

								<h2 class="badge text-bg-info">*</h2>

							</div>
							
							
							<div class="d-flex justify-content-between" style="padding-top: 5%">
								<a href="liste?format=xml" class="btn btn-warning">En XML</a>
								<a href="liste?format=json" class="btn btn-info">En JSON</a>
							</div>

							<hr class="border border-top border-dashed border-info rounded">
						
							<div class="d-flex justify-content-between" style="margin-bottom: 0px;">
								<h5 class="card-title">Consulter un étudiant</h5>

								<h2 class="badge text-bg-info">*</h2>

							</div>
							
							<hr class="border border-top border-dashed border-info rounded">
							<form method="get" id="formEtudiant"	>
								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon1"> Etudiant </span>
									 <select class="form-select" aria-label="select format" name="id" id="id">
										<% for(Etudiant e : liste) {%>
											<option value="<%= e.getID() %>"><%= e.getName()+" "+e.getSurname() %></option>
										<%} %>
									</select>
								</div>

								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon1"> Format </span> 
									<select class="form-select" aria-label="select format" name="format" id="format">
										<option selected value="xml">XML</option>
										<option value="json">JSON</option>
									</select>
								</div>



								<hr
									class="border border-top border-dashed border-warning rounded">

								<div class="d-flex justify-content-between" style="padding-top: 1%">
									<input type="submit" value="Consulter" class="btn btn-warning" />
								</div>
							</form>
						</div>
					</div>

				</div>

			</div>
		</div>
	</div>
	<%		
		}
	%>
	<script>
		document.getElementById("formEtudiant").addEventListener("submit", function(e){
		
		    e.preventDefault();
		
		    const id = document.getElementById("id").value;
		    const format = document.getElementById("format").value;
		
		    const url = "" + id + "?format=" + format;
		
		    window.location.href = url;
		});
	</script>
</body>
</html>