# Jour 1 (12/11/19):
## Mathieu + Jérémy (30min) : 
	Création du cahier des charges et estimation des temps nécessaires à chaque étape.
## Mathieu (30min) :
	Fin du cahier des charges : ajout de détails sur l'explication des fonctionnalités et mise en page.

# Jour 2 (19/11/19):
## Mathieu + Jérémy (1h30) : 
	Installation d'android studio et activation ADB.
	Découverte de la doc : Activiy, Intent.

# Jour 3 (20/11/19):
## Mathieu + Jérémy (1h) :
	Design de l'interface sur papier

# Jour 4 (25/11/19)
## Mathieu (3h)
	Mise en place des "activity" de l'application pour démarrer le menu de navigation latéral (beaucoup de consultation de docs sur la création d'un navigation viewer et des bases de kotlin (null safety, class, ...))

# Jour 5 (27/11/19)
## Mathieu (2h)
	Test de création du menu de navigation, utilisation de androidx alors que la version d'android utilisée est 7. Du coup tout doit être refait pour ne pas utiliser androidx

# jour 7 (21/01/20)
## Mathieu (4h)
	Ajout de la google map, du layout et du bouton dans l'activité newTravel. Ajout des affichages des chemins sur la map (à tester).

# jour 8 (25/01/20)
## Mathieu (3h)
	Modification de la structure des éléments dans newTravel -> utilisation de 2 fragments dont 1 spécial pour la carte du trajet
	+ 1er test sur une 15aine de kilomètres (cf capture écran) -> problème du GPS qui détecte des points loins sans raison

# jour 9 (30/01/20)
## Mathieu (2h)
	Ajout du "lissage" des points fantômes en utilisant la vitesse
	+ tests sur une vm d'android studio pour faire des tests de GPS sans devoir bouger

# jour 10 (04/02/20)
## Mathieu (2h)
	Mise en place de la lib de Jérémy dans l'application
	Fix du bug du premier point

# jour 11 (06/02/20)
## Mathieu (4h)
	Choix du nom du trajet fini avec un pop up  et ajout dans l'historique
	affichage basique de l'historique

# jour 12 (07/02/20)
## Mathieu (2h)
	modification historique pour affichre les 10 derniers trajets
	Tests pour la sérialisation et l'enregistrement dans des fichiers des trajets

# jour 13 (13/02/20)
## Mathieu (3h)
	Ajout des cards pour afficher les compactedTravel
	Prise de screen lors de la création d'un travel et affichage dans les cards