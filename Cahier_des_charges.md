# Cahier des charges

## Description
Le projet consiste à réaliser une application mobile visant à améliorer l'expérience des cyclistes. Le principe est de pouvoir tracer un trajet pour pouvoir le reproduire ou le partager. L'application permettra donc de créer des trajets et consulter les statistiques liées à ceux-ci. L'idée est d'avoir une application modulaire, avec des fonctionnalités pas indispensables mais qu'on peut ajouter à l'application de base. Ainsi, le minimum est la création et la consultation de trajets et les fonctionnalités sont l'ajout de statistiques sur les trajets, une gestion utilisateur, la transformation en réseau social, ...

## Tâches à réaliser
### 1 - Récupération des points GPS et affichage simple (~20h)
La base de l'application. Créer une interface simple permettant de démarrer / arrêter un trajet, d'en récupérer les points GPS et de les afficher très simplement (tout ça en local). Cette partie inclut aussi les temps de découverte et de prise en main des outils.

### 2 - Ajout de carte pour l'affichage des trajets
Ajouter la carte (de style google map) pour afficher les trajets.

### 3 - Stockage des utilisateurs et de leurs trajets dans une base de donnée
Mise en place d'une base de données permettant de stocker les utilisateurs et leurs trajets.

### 4 - Création d'une interface utilisateur avancée
Création du design de l'application (menus, affichage des trajets, du compte, ...).

### 5 - Gestion avancée des utilisateurs
Mise en place d'une réelle interface utilisateur avec compte personnel (login, mot de passe, statistiques, ...).

### 6 - Ajout d'autres statistiques
Utilisation de tous les outils du téléphone pour agrémenter les statistiques des trajets.
- Dénivelé
- Météo
- Vitesse
- Temps (heures, dates)
- Allure / Dépense (exemple: nombre de coups de pédale)

Certaines statistiques seront simples et rapides à implémenter (comme la vitesse ou le dénivelé) et d'autres seront trop compliquées ou longues à mettre en place et on se contentera de rechercher les moyens de le faire (comme l'allure / dépenses).

### 7 - Affichage de statistiques avancé
Affichage des statistiques des trajets via des graphiques, des courbes

### 8 - Evolution en (pseudo) réseau social