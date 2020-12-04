// fonction qui écrit dans le document la date actuelle
function dateActuelle(){
	document.write(new Date());
}

// function qui change la couleur 
function changeCouleur(e,couleur){
	e.style.color = couleur;
}

// pour centrer un paragraphe au mileu de la page avec un mot par ligne
function centreParagraphe(e){
	e.style.position = "relative";
	e.style.left = "50%";
	e.style.marginLeft = "-15px";
	e.style.width = "30px";

}

// pour remettre le paragraphe comem avant
function positionInitiale(e){
	e.style.position = "relative";
	e.style.left = "";
	e.style.marginLeft = "";
	e.style.width = "5000px";

}

// ajouter un input à un form
function addInput(){
	// on demande à la document HTML de créer une nouvelle balise <input>
	var input = document.createElement("input");
	// cet input est de type texte et pré rempli avec "vous avez reussi"
	input.type = "text";
	input.value = "vous avez reussi";
	// on récupère le formulaire de name = "formulaire" dans la page et l:ui ajouter la balise input créée
	document.forms['formulaire'].appendChild(input);
}

// ajouter un bouton à la page
function afficherBouton(){
	document.write('<input type="button" value="Appuyer ici" onclick="afficherHello()"/>');
}

// ajouter un bouton à la page
function afficherChampsEtBoutton(){
	document.write('<input type="button" value="Appuyer ici" onclick="afficherHello()"/>');
}

/*function afficherHello(){
	console.log("toto");
	alert("Hello world!");
}*/

function afficherChampsEtBoutton(){
	document.write('<input type="text" id="champ"/>');
	document.write('<input type="button" id="bouton" value="Envoyer" onclick="verifierChamps()"/>');
}

function verifierChamps(){
	var champ = document.getElementById("champ");
	if(champ.value){
		champ.focus();
	}else{
		alert("Le champ n'est pas saisi");
	}
}

// ajoute un label attention
function afficheAttention(){
	// on crée un nouveau label
	var labelAttention = document.createElement("label");
	labelAttention.id = "attention";
	labelAttention.style.color = "red";
	// mettre le texte qui s'affiche sur la page au label
	labelAttention.innerHTML = "Attention !!!";
	// on ajoute le label au body de la page HTML
	document.body.appendChild(labelAttention);
}

// enlève le label attention
function enleveAttention(){
	var labelAttention = document.getElementById("attention");
	document.body.removeChild(labelAttention);
}

// change la source d'une balise image passée en paramètres
function changeImg(e){
	e.src = "contact.png";
}

function creeAlert(){
		alert("vous pouvez nous contacter.");
}

// affiche ou supprime l id des checkbox dans un label donne
function afficheOuNon(e){
	var label = document.getElementById("label");
	if(e.checked == true){	
		label.innerHTML = label.innerHTML + e.id; 
	}else{
		var text = label.innerHTML;
		var newtext;
		// il n y avait qu une suele checkbox de validéee
		if(e.id.length == text.length){
			newtext = "";
		}else{
			newtext = text.substring(0,text.indexOf(e.id));
			newtext += text.substring(text.indexOf(e.id)+e.id.length,text.length);
		}
		label.innerHTML = newtext; 
	}
}

// dessine un triangle rectangle d'étoile dans un div "rectangle"
function dessinerTriangleRectangle(){
	// on récupère la valeur du nombre de ligne
	var nbLignes = document.getElementById("nbLignes").value;
	// on récupère la div où afficher
	var div = document.getElementById("rectangle");
	// est ce que l utilisateur a bien rentré un nombre???
	var reg = /^\d+$/i;
	if(reg.test(nbLignes)){
		nbLignes = parseInt(nbLignes);
		var str = "";
			var j;
			for(i = 1; i < nbLignes + 1; i++){
				var ligne = document.createElement("p");
				for(j = 0; j < i ; j ++){
					str +="* ";
				}
				ligne.innerHTML = str;
				div.appendChild(ligne);
				str = "";
			}
		}
}

//calcul du nombre du jour restant
function calculNbJours(){
	var jour = document.getElementById("jour").value;
	var mois = document.getElementById("mois").value;
	var anniversaire = new Date();
	var date = new Date(anniversaire.getFullYear(), parseInt(mois) - 1,jour);
	if((date.getMonth() == anniversaire.getMonth() && date.getDate() > anniversaire.getDate()) 
					|| date.getMonth() > anniversaire.getMonth()){
				anniversaire.setFullYear(date.getFullYear()+1);
			}

			//on fait la différence des deux dates en ms
			nbJour = Date.UTC(anniversaire.getFullYear(),anniversaire.getMonth(),anniversaire.getDate()) - Date.UTC(date.getFullYear(),date.getMonth(),date.getDate());

			//ou plus simple
			// getTime trnasforme le date en une valeur en ms
			nbJour = anniversaire.getTime() - date.getTime();

			// on transforme les ms en jours
			nbJour /= 3600000;
			nbJour /= 24;
			document.getElementById("joursrestant").innerHTML =Math.round(nbJour);
}

function valideNom(e){
	var reg = /^\w{2,}$/;
	if(!reg.test(e.value) && !document.getElementById("msgNom")){
		var msg = document.createElement("label");
		msg.id = "msgNom";
		msg.style.color = "red";
		msg.innerHTML = "* Minimum deux caracteres."
		// nextSibling : élément après e qui correcpond <br/>
		document.forms["formulaire"].insertBefore(msg, e.nextSibling);
		// pour supprimer la valeur fausse de l input
		e.value = "";
		return false;
	}else if(!e.issetfocus){
		// on enlève le message uniquement si l'input n'as pas le focus
		document.forms["formulaire"].removeChild(document.getElementById("msgNom"));
	}
	return true;	
}

function valideMail(e){
	var reg = /^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$/;
	if(!reg.test(e.value) && !document.getElementById("msgMail")){
		var msg = document.createElement("label");
		msg.id = "msgMail";
		msg.style.color = "red";
		msg.innerHTML = "* Adresse mail invalide."
		document.forms["formulaire"].insertBefore(msg, e.nextSibling);
		return false;
	}else if(!e.issetfocus){
		document.forms["formulaire"].removeChild(document.getElementById("msgMail"));
	}
	return true;	
}

function valideCodePostal(e){
	var reg = /^\d{5}$/;
	if(!reg.test(e.value) && !document.getElementById("msgCP")){
		var msg = document.createElement("label");
		msg.id = "msgCP";
		msg.style.color = "red";
		msg.innerHTML = "* Cinq chiffres."
		document.forms["formulaire"].insertBefore(msg, e.nextSibling);
		return false;
	}else if(!e.issetfocus){
		document.forms["formulaire"].removeChild(document.getElementById("msgCP"));
	}
	return true;	
}

function valideTel(e){
	var reg = /^(\d{2}(\s|\-|\.)?){4}\d{2}$/;
	if(!reg.test(e.value) && !document.getElementById("msgTel")){
		var msg = document.createElement("label");
		msg.id = "msgTel";
		msg.style.color = "red";
		msg.innerHTML = "* 12.34.56.78.90."
		document.forms["formulaire"].insertBefore(msg, e.nextSibling);
		return false;
	}else if(!e.issetfocus){
		document.forms["formulaire"].removeChild(document.getElementById("msgTel"));
	}
	return true;	
}

function valideUrl(e){
	var reg = /^((http:\/\/|https:\/\/)?(www.)?(([a-zA-Z0-9-]){2,}\.){1,4}([a-zA-Z]){2,6}(\/([a-zA-Z-_\/\.0-9#:?=&;,]*)?)?)$/;
	if(!reg.test(e.value) && !document.getElementById("msgURL")){
		var msg = document.createElement("label");
		msg.id = "msgURL";
		msg.style.color = "red";
		msg.innerHTML = "* Adresse Internet http//wwww.aaa.com."
		document.forms["formulaire"].insertBefore(msg, e.nextSibling);
		return false;
	}else if(!e.issetfocus){
		document.forms["formulaire"].removeChild(document.getElementById("msgURL"));
	}
	return true;	
}

function validePass(e){
	var reg = /[A-Z]/;
	var regChiffre = /\d/;
	if((!reg.test(e.value) || !regChiffre.test(e.value) || e.value.length != 8)&& !document.getElementById("msgPass")){
		var msg = document.createElement("label");
		msg.id = "msgPass";
		msg.style.color = "red";
		msg.innerHTML = "* 8 caractere avec une majuscule et un chiffre."
		document.forms["formulaire"].insertBefore(msg, e.nextSibling);
		return false;
	}else if(!e.issetfocus){
		document.forms["formulaire"].removeChild(document.getElementById("msgPass"));
	}
	return true;	
}

function valideDate(e){
	var reg = /^\d\d\/\d\d\/\d{4}$/;
	if(!reg.test(e.value) & !document.getElementById("msgDate")){
		var msg = document.createElement("label");
		msg.id = "msgDate";
		msg.style.color = "red";
		msg.innerHTML = "* jj/MM/.aaaa."
		document.forms["formulaire"].insertBefore(msg, e.nextSibling);
		return false;
	}else if(!e.issetfocus){
		document.forms["formulaire"].removeChild(document.getElementById("msgDate"));
	}
	return true;	
}



function valideLangue(){
	console.log("validation finale")
	if(!document.getElementById("msgLG")
	 && (document.getElementById("ang").checked != true 
	 		|| document.getElementById("fr").checked != true)){
		var msg = document.createElement("label");
		msg.id = "msgLG";
		msg.style.color = "red";
		msg.innerHTML = "* Vous devez choisir une langue au minimum."
		document.getElementById("fieldset").insertBefore(msg, document.getElementById("ang").nextSibling);
		return false;
	}else{
		document.getElementById("fieldset").removeChild(document.getElementById("msgLG"));
		return true;	
	}
}

// informations sur le poste et le navigateur client
function infoClient(){
	var p = document.createElement("p");
	p.innerHTML = 'Nom du navigateur      : '+navigator.appName+' ('+navigator.appCodeName+')';
	document.body.appendChild(p); //<p>Nom du navigateur : ...</p>
	p = document.createElement("p");
	p.innerHTML = 'Version du navigateur  : '+navigator.appVersion;
	document.body.appendChild(p);
	p = document.createElement("p");
	p.innerHTML = 'Système d\'exploitation : '+navigator.platform;
	document.body.appendChild(p);
}

// changer la barre d'état
function changeBarreEtat(){
	window.status = "Le messsage de la barre d'état a été modifié."
	//("Veuillez consulter le texte se trouvant dans la barre d'état.")
}

//apres le timeout une alert s'affiche
function minuterie(){
	//la function est appelée après un délai de 3s
	window.setTimeout(function(){
		alert("Delai expire")
	},3000);
}

// taille du navigateur navigateur client
function tailleNavigateur(){
	var p = document.createElement("p");
	// on affiche la largeur puis la hauteur
	p.innerHTML = 'Le navigateur fait : '+document.body.clientWidth+'x'+document.body.clientHeight+" px.";
	// on peut aussi passer par window.innerWith et window.innerHeight mais ce n'est pas compatible avec IE
	document.body.appendChild(p); 
}

// calculatrice
function calcule(){
	var resultat;
	// on récupère les opérandes
	var op1 = document.getElementById("op1").value;
	var op2 = document.getElementById("op2").value;
	// on vérifie que les opérandes sont bien des nombres
	var regEntier = /^[0-9]+$/;
	var regReel = /^\d+\.\d+$/;
	if((regEntier.test(op1) || regReel.test(op1)) && (regEntier.test(op2) || regReel.test(op2))){
			// transforme une string en nombre
			op1 = parseFloat(op1);
			op2 = parseFloat(op2);
			//on recupere l operateur choisi;
			var select = document.getElementById("operateur");
			var operateur = select.options[select.selectedIndex].value;
			// OU operateur = document.getElementById("operateur").value;
			// on fait le calcul demande
			switch(operateur){
				case "+":
					resultat = op1 + op2;
				break;
				case "-":
					resultat = op1 - op2;
				break;
				case "*":
					resultat = op1 * op2;
				break;
				case "/":
					resultat = op1 / op2;
				break;
			}
	}else{
		resultat= "donnees invalides"
	}
	document.getElementById("resultat").value = resultat;
}
// met le texte à gauche
function gauche(e){
	e.style.textAlign = "left";
}
// met le texte au centre
function centre(e){
	e.style.textAlign = "center";
}
// change les pourcentages
function transformePourcent(e,focus){
	if(focus){
		var valeur = e.value;
		e = valeur * 100;
	}else{
		var valeur = e.value;
		//on verifie que la donnee est bien un nombre
		var reg = /\d+(\.\d+)?$/;
		if(reg.test(valeur)){
			valeur = parseFloat(valeur);
			valeur = valeur/100;
			e.value = valeur;
		}else{
			e.value = "";
		}
	}
}
// change les dates
function transformeDate(e){
	var date = e.value;
	var regSansChangement = /^\d{2}\/\d{2}\/\d{2}$/;
	if(!regSansChangement.test(date)){
		var regDate = /^\d{2}(\/|\.)\d{2}(\/|\.)(\d{2}|\d{4})$/;
		if(regDate.test(date)){
			var newDate = date[0]+date[1]+"/"+date[3]+date[4]+"/"+date[date.length-2]+date[date.length-1];
			e.value = newDate;
		}else{
			e.value = "";
		}
	}
}

// bouger une image horizontalement en aller retour
var positionImg = 0;
var versLaGauche = false;
var img;
var largeur;
function decalement(){
	if(!versLaGauche && parseInt(img.style.left) + 10 < largeur ){
		positionImg = parseInt(img.style.left) + 10;
		img.style.left = "" + positionImg+"px";
	positionImg = 0;
	}else{
		versLaGauche = true;
	}
	if(versLaGauche && parseInt(img.style.left) > 0 ){
		positionImg = parseInt(img.style.left) - 10;
		img.style.left = "" + positionImg+"px";
	}else{
		versLaGauche = false;
	}
}
function imageBouge(){
	// on récupère la taille de la fenêtre
	largeur = document.body.clientWidth;
	// on récupère l'image
	img = document.getElementById("img");
	img.style.position = "absolute";
	img.style.left = "0px";
	setInterval("decalement()",100);
}

// gestion du chronomètre
var timer;
function demarreChrono(){
	// on recupere le nombre de seconde, 0 au chargement de la page
	var sec = document.getElementById("sec").value;
	// on transforme le nombre de secondes en entier
	sec = parseInt(sec);
	// on ajoute une seconde
	sec++;
	// faut-il ajouter une minute?
	if(sec == 60){
		// on récupère le nombres de minutes dans la apge HTML
		var min = document.getElementById("min").value;
		min = parseInt(min);
		// on ajoute une minute
		min++;
		// on affiche le nombre de minute et 0 seconde
		document.getElementById("min").value = min;
		document.getElementById("sec").value = 0;
	}else{			// pas besoin d ajouter une minute, on change juste les secondes
		document.getElementById("sec").value = sec;
	}
	// on rappelle la fonction toutes les secondes
	timer = setTimeout('demarreChrono()',1000);
}

function stopChrono(){
	clearTimeout(timer);
}

function razChrono(){
	stopChrono();
	document.getElementById("sec").value = 0;
	document.getElementById("min").value = 0;
}

// faire disparaitre ou apparaitre un element dont l'id est id
var efface = true;
function disparaitOuApparait(id,bouton){
	var e = document.getElementById(id);
	if(efface){
		// laisse la place de la div
		//e.style.visibility = "hidden";
		// enlève totalement la div de l'affichage
		e.style.display = "none";
		bouton.value="+";
		efface = false;
	}else{
		//e.style.visibility = "visible";
		e.style.display = "block";
		bouton.value="-";
		efface = true;
	}
}
// Test : vérifier la création d'un membre et la méthode toString
function membre(id, nom){
				// pour dire que c'est un objet : this = objet courant
				this.id = id
				this.nom = nom
				this.toString = function(){
					return "ID : "+this.id+" - Nom : "+this.nom;
				}
			}
// fonction pour créerun objet équipe
	export function equipe(nom){
					this.nom = nom;
					this.membres = new Array();
					this.toString = function(){
						var str = "Equipe : "+ this.nom+ " / ";
						for(i = 0; i < this.membres.length; i++){
							str += this.membres[i].toString() + " / ";
						}
						return str;
					}
				}

function createEquipe(){

			// création des membres
			var marine = new membre(1,"marine");
			var cathy = new membre(2,"cathy");
			var ludivine = new membre(3,"ludivine");

			console.log(marine.toString());
			console.log(cathy.toString());
			console.log(ludivine.toString());

			// création de l'équipe
			var formationJS = new equipe("javascript");
			formationJS.membres.push(marine);
			formationJS.membres.push(cathy);
			formationJS.membres.push(ludivine);
}


