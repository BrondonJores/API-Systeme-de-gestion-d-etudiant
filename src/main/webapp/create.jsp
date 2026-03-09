<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Etudiant - Ajouter un étudiant</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>
	<div class="container">
		<br>
		<figure class="d-flex justify-content-between align-items-center">
			<blockquote class="blockquote mb-0">
				<h1 class="h1">Etudiant</h1>
				<figcaption class="blockquote-footer mb-0 badge text-bg-success">
					<span>Ajouter un étudiant à la plateforme</span>
				</figcaption>
			</blockquote>
		</figure>

		<div class="container-sm my-4 p-3 bg-light rounded shadow-sm">
			<div class="card mb-3">
				<div class="row g-0">
					<div class="col-md-4">
						<img src="<%=request.getContextPath()%>/resources/images/img1.png"
							class="img-fluid rounded-start">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<div class="d-flex justify-content-between"
								style="margin-bottom: 0px;">
								<h3 class="card-title">
									Créer un étudiant
									<hr
										class="border border-top border-dashed border-success rounded">
								</h3>

							</div>

							<%if (request.getAttribute("message") != null) {%>
							<div class="alert alert-success d-flex align-items-center"
								role="alert">
								<div>
									<%=request.getAttribute("message")%>
								</div>
							</div>
							<%}%>
							<form method="post" action="etudiant">
								<input type="hidden" name="action" value="insert" />
								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon1"> Nom </span> <input
										type="text" class="form-control" name="name"
										aria-label="Username" required> <span
										class="input-group-text" id="basic-addon1"> Prenom </span> <input
										type="text" class="form-control" name="surname"
										aria-label="Username" required>
								</div>

								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon1">
										Telephone </span> <input type="text" class="form-control"
										name="telephone" aria-label="Username" required>
								</div>

								<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon1">
										Adresse </span> <input type="text" class="form-control"
										name="adresse" aria-label="Username" required>
								</div>
								<hr>
								<div class="d-flex justify-content-between"
									style="padding-top: 1%">
									<input type="submit" value="Valider" class="btn btn-success" />
									<input type="reset" value="Effacer" class="btn btn-secondary" />
								</div>
							</form>
						</div>


					</div>

				</div>
			</div>
			<div class="input-group mb-3" style="padding-left: 90%">
				<a href="etudiant" class="input-group-text btn btn-secondary"
					id="basic-addon1"> Retour </a>
			</div>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>