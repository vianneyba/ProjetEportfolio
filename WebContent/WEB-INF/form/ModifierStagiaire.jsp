<h1>Modification du compte</h1>

<form method="post" action="/ProjetEportfolio/stagiaire/modifier">
	<div class="row">
		<div class="col-md-5 col-sm-12">
		
		</div>
		
		<div class="col-md-7 col-sm-12">
			<div class="form-group">
				<label for="nom">Nom: </label>
				<input name="nom" type="text" class="form-control" id="nom" value="${ sessionScope.user.nom }" require />
			</div>
			
			<div class="form-group">
				<label for="prenom">Prenom: </label>
				<input name="prenom" type="text" class="form-control" id="prenom" value="${ sessionScope.user.prenom }" require />
			</div>

			<div class="form-group">
				<label for="email">Email: </label>
				<input name="email" type="email" class="form-control" id="email" value="${ sessionScope.user.email }" require />
			</div>
			
			<div class="form-group">
				<label for="adresse">Adresse: </label>
				<input name="adresse" type="text" class="form-control" id="adresse" value="${ sessionScope.user.adresse }" />
			</div>
						
			<div class="form-group">
				<label for="ddn">Date de naissance: </label>
				<input name="ddn" type="text" class="form-control" id="ddn" value="${ sessionScope.user.dateNaissance }" />
			</div>
			
			<input type="submit" name="save" value="Modifier" />
		</div>
	</div>
</form>

<form method="post" action="/ProjetEportfolio/stagiaire/modifier">
	<div class="row">

		<div class="col-md-5 col-sm-12">
			<c:if test="${ !empty info }">
				<div class="alert alert-danger" role="alert">
					<ul>
						<c:if test="${ !empty info.msgMdpVs }"><li>${ info.msgMdpVs }</li></c:if>
					</ul>
				</div>
			</c:if>
		</div>

		<div class="col-md-7 col-sm-12">
			<fieldset class="form-group">
				<legend>Mot De Passe</legend>
				
				<div class="form-group">
					<label for="mdp1">Mot de passe: </label>
					<input name="mdp1" type="password" class="form-control" id="mdp1" require />
				</div>
				
				<div class="form-group">
					<label for="mdp2">Re-mot de passe: </label>
					<input name="mdp2" type="password" class="form-control" id="mdp2" require />
				</div>

				<input type="submit" name="save" value="Changer" />
			</fieldset>
		</div>
	</div>
</form>

