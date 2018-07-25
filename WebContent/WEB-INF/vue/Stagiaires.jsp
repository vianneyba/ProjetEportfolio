<table class="table table-dark">
	<thead class="thead-dark">
		<tr>
			<td>Nom</td>
			<td>Prenom</td>
			<td>email</td>
			<td>Adresse</td>
		</tr>
	</thead>

	<c:forEach items="${ stagiaires }" var="stagiaire">
	<tr>
		<td><a href="<c:url value = "/stagiaire/id/${ stagiaire.id }"/>"><c:out value="${ stagiaire.nom }" /></a></td>
		<td><c:out value="${ stagiaire.prenom }" /></td>
		<td><c:out value="${ stagiaire.email }" /></td>
		<td><c:out value="${ stagiaire.adresse }" /></td>
	</tr>
	</c:forEach>
</table>