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

# Jour 4 (25/11/19):
## Jérémy (1h30) :
	Premier lancement de la VM
	Installation de mySQL sur le serveur
	Création de USER et découverte du fonctionnement de la BDD.
## Mathieu (3h)
	Mise en place des "activity" de l'application pour démarrer le menu de navigation latéral (beaucoup de consultation de docs sur la création d'un navigation viewer et des bases de kotlin (null safety, class, ...))

# Jour 5 (26/11/19):
## Jérémy (1h) :
	Début de recherche sur l'organisation de la BDD.
	Début de rapport sur la BDD, avec ajout de bibliographie

# Jour 6 (27/11/19):
## Jérémy (1h
)
	Choix de l'organisation de la BDD
	Schema fais sur draw.io pour résumé la BDD
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

# jour 14 (17/02/20)
## Mathieu (3h)
	Ajout de l'activity lorsqu'on clique sur une card

# jour 15 (21/02/20)
## Mathieu (1h)
	Modification du format des temps inscrit dans les points générés par un trajet

# jour 16 (24/02/2020)
## Mathieu (4h)
	Premiers textes de rapport (prise en main de l'appli + analyse)

# Jour 7 (18/12/19):
## Mathieu (2h)
 	Rebasement du projet à partir d'un exemple Android Studio pour le navigation drawer (+ compréhension de l'exemple)
## Jérémy (2h)
	Code approximatif d'un serveur, permettant de se connecter.
	Code d'un client qui envoie un message sur le serveur pour vérifier son fonctionnement. 
	Problème de connection à distance (fonctionne en localhost)

# Jour 8 (19/12/19):
## Jérémy (2h)
	Correction de bug d'affichage d'erreur sur le serveur lors de la connection du client.
	Correction des ports de la VM qui bloquaiellnt l'accès au port 47503.

# Jour 9 (20/12/19):
## Jérémy (2h)
	Ajout de l'accès à la base de donnée au serveur. (Problème lors de l'utilisation de connector : ajout du .jar au classpath).

# Jour 10 (10/01/20):
## Jérémy (1h)
	Amélioration du serveur. (Problème d'accès à la VM : éteinte + pareil le weekend...).

# Jour 11 (13/01/20):
## Jérémy (1h30)
	Création du protocole de communication entre l'application et le serveur.

# Quelques jours du ~15 au ~19
## Jérémy (4h)
	Implémentation de plusieurs commandes côté serveur : history, nouveau projet -> Non testé (en attente d'une appli pour facilité l'insertion des données)

# Jour 12 & 13 (21-22/01/20):
## Jérémy 2h
	Essaie de connexion entre l'application et le serveur : Echec
	Diagnostique de l'erreur : le téléphone n'a pas internet lors d'une connexion au VPN
# Jour 14 (23/01/20):
## Jérémy 1h
	Rédaction rapport
# Jour 15 (26/01/20):
## Jérémy 2h
	Problème VPN sous android
	Rapport