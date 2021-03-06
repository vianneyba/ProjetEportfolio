<h1>Ajouter Entreprise</h1>
 
<form method="post" action="">
	<div class="row">
		<div class="col-md-5 col-sm-12">
			<c:if test="${ not empty info.ok && !info.ok }" >
				<div class="alert alert-danger" role="alert">
					<ul>
						<c:if test="${ not empty info.msgErrNom }" ><li>${ info.msgErrNom }</li></c:if> 
						<c:if test="${ not empty info.msgErrAdresse }" ><li>${ info.msgErrAdresse }</li></c:if>
						<c:if test="${ not empty info.msgErrVille }" ><li>${ info.msgErrVille }</li></c:if>
						<c:if test="${ not empty info.msgErrCp }" ><li>${ info.msgErrCp }</li></c:if>
					</ul>
				</div>
			</c:if>	
		</div>
		<div class="col-md-7 col-sm-12">
			<fieldset class="form-group">
	    		<legend>Entreprise</legend>
	    		
				<div class="form-group">
					<label for="nom">Nom: </label>
					<input name="nom" type="text" class="form-control ${ info.classeNom }" id="nom" placeholder="Nom de l'entreprise"
					<c:if test="${ not empty info.ok && !info.ok }" >value="${ entreprise.nom }"</c:if> required="required" />
				</div>
				 
				<div class="form-group">
					<label for="adresse">Adresse: </label>
					<input name="adresse" type="text" class="form-control ${ info.classeAdresse }" id="adresse" placeholder="Adresse de l'entreprise"
						<c:if test="${ not empty info.ok && !info.ok }" >value="${ entreprise.adresse }"</c:if> required="required" />
				</div>
				
				<div class="form-group">
					<label for="ville">Ville: </label>
					<input name="ville" type="text" class="form-control ${ info.classeVille }" id="ville" placeholder="Ville de l'entreprise"
					<c:if test="${ not empty info.ok && !info.ok }" >value="${ entreprise.ville }"</c:if> require />
				</div>
				
				<div class="form-group">
					<label for="cp">Code postal: </label>
					<input name="cp" type="text" class="form-control ${ info.classeCp }" id="cp" placeholder="Code postal de l'entreprise"
					<c:if test="${ not empty info.ok && !info.ok }" >value="${ entreprise.codePostal }"</c:if> require />
				</div>
				<button type="submit" class="btn btn-primary">Enregistrer</button>
			</fieldset>
		</div>
	</div>			
</form>