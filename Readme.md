# Projet BDD API Banque
### Par Arnaud JUILIEN et Antoine BURY

---
Compte rendu des ajouts et modifications depuis la présentation.

### Agence

* Ajout d'une requête avec querry param
  * Cette requête renvoie les agences ayants la même 
  adresse ou le même nom qu'indiqué dans la querry
* Modification du chemin pour récupérer toutes les agances
  * Avant /agences : maintenant /agences/all

### Compte

* Retrait des éspaces dans l'IBAN

### Carte

* Retrait des tirets dans le numero de carte

### Ameliorations envisagées

* Utiliser des DTO de réponse au lieu de renvoyer les entités
* Ajouter plus de requêtes avec querry param