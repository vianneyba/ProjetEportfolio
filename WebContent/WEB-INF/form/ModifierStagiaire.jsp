<h1>Modification du compte</h1>

<form method="post" action="">
	<div class="row">
		<div class="col-md-5 col-sm-12">
			<c:if test = "${ not empty msg.ok && !msg.ok }">
				<div class="alert alert-danger" role="alert">
					<ul>
						<c:if test = "${not empty msg.msgErrNom }"><li>${ msg.msgErrNom }</li></c:if>
						<c:if test = "${not empty msg.msgErrPrenom }"><li>${ msg.msgErrPrenom }</li></c:if>
						<c:if test = "${not empty msg.msgErrEmail }"><li>${ msg.msgErrEmail }</li></c:if>
						<c:if test = "${not empty msg.msgErrAdresse }"><li>${ msg.msgErrAdresse }</li></c:if>
						<c:if test = "${not empty msg.msgErrDdn }"><li>${ msg.msgErrDdn }</li></c:if>
					</ul>
				</div>
			</c:if>
		</div>
		
		<div class="col-md-7 col-sm-12">
			<fieldset class="form-group">
				<legend>Modification du compte</legend>
				<div class="form-group">
					<label for="nom">Nom: </label>
					<input name="nom" type="text" class="form-control ${ msg.classeNom }" id="nom" value="${ sessionScope.user.nom }" require />
				</div>
				
				<div class="form-group">
					<label for="prenom">Prenom: </label>
					<input name="prenom" type="text" class="form-control ${ msg.classePrenom }" id="prenom" value="${ sessionScope.user.prenom }" require />
				</div>
	
				<div class="form-group">
					<label for="email">Email: </label>
					<input name="email" type="email" class="form-control ${ msg.classeEmail }" id="email" value="${ sessionScope.user.email }" require />
				</div>
				
				<div class="form-group">
					<label for="adresse">Adresse: </label>
					<input name="adresse" type="text" class="form-control ${ msg.classeAdresse }" id="adresse" value="${ sessionScope.user.adresse }" />
				</div>
							
				<div class="form-group">
					<label for="ddn">Date de naissance: </label>
					<input name="ddn" type="text" class="form-control ${ msg.classeDdn }" id="ddn" value="${ sessionScope.user.ddnToString }" />
				</div>
				<button type="submit" name="save" class="btn btn-primary" >Modifier</button>
			</fieldset>
		</div>
	</div>
</form>

<form method="post" action="">
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
					<label for="mdp2" >Re-mot de passe: </label> <label id="mdp2Label"></label>
					<input name="mdp2" type="password" class="form-control" id="mdp2" require />
				</div>
				<button type="submit" id="saveMdp" name="save" class="btn btn-primary" value="changer">Changer</button>
			</fieldset>
		</div>
	</div>
</form>
<script language="javascript">
	var mdp1= document.getElementById("mdp1");
	var mdp2= document.getElementById("mdp2");
	var mdpLabel= document.getElementById("mdp2Label");
	var button = document.getElementById('saveMdp');

	function enlever(e) {
		e.preventDefault();
	}
	
	button.addEventListener('click', enlever, false);
	
	mdp2.addEventListener('input', function() {	
		button.addEventListener('click', enlever, false);
		if (mdp1.value != mdp2.value) {
			mdpLabel.innerHTML= " pas de correspondance";
			mdpLabel.style.background = "red";
		} else if (mdp1.value.length < 8) {
			mdpLabel.innerHTML= " 8 caractéres min.";
			mdpLabel.style.background = "red";
		} else {
			mdpLabel.innerHTML= " OK";
			mdpLabel.style.background = "green";
			button.removeEventListener('click', enlever);
		}
	}); 
</script>