<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
</head>
<body>
	<form action="Calcul" method="post" class="container-sm">
		<figure class="text-center">
			<blockquote class="blockquote">
				<p>Additionneur</p>
			</blockquote>
			<figcaption class="blockquote-footer">
				Programme pour additionner deux chiffres avec <cite title="JEE">JEE</cite>
			</figcaption>
		</figure>



		<div class="mb-3">
			<% 
				String erreur= null;
				
				if(request.getAttribute("erreur")!=null){
					erreur = (String)request.getAttribute("erreur");	
			%>
			<div class="alert alert-secondary">
				<%=erreur %>
			</div>

			<%} %>
			<div class="input-group mb-4">
				<label for="Nbr1" class="input-group-text">Nbr1</label> <input
					type="number" name="nbr1" class="form-control">
			</div>
			<div class="input-group mb-4">
				<label for="Nbr2" class="input-group-text"> Nbr2</label> <input
					type="number" name="nbr2" class="form-control">
			</div>
			<div class="input-group mb-3">
				<input type="submit" name="valider" value="valider"
					class="btn btn-secondary">
			</div>
		</div>
	</form>
</body>
</html>