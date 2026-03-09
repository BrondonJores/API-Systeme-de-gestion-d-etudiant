
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calcul</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">

</head>
<body>
	<% 
		if(request.getAttribute("Somme")!=null){
			int somme = (int) request.getAttribute("Somme");
	%>
	<figure class="text-center">
		<blockquote class="blockquote">
			<p>Additionneur</p>
		</blockquote>
		<figcaption class="blockquote-footer">
			Programme pour additionner deux chiffres avec <cite title="JEE">JEE</cite>
		</figcaption>
	</figure>
	<div class="alert alert-secondary">
		<h4>
			La somme est :
			<%=somme %></h4>
	</div>
	<% }else{
		response.sendRedirect("index.jsp");
	}%>



</body>
</html>