# Projet BDD API Banque
### Par Arnaud JUILIEN et Antoine BURY

---
Compte rendu des ajouts et modifications depuis la présentation.

### Agence

* Ajout d'une requête avec querry param
  * Cette requête renvoie les agences contenant l'adresse, le nom ou la ville indiqué dans la querry
  * Il est possible de modifier le type de cette requête, par exemple si on souhaite récupérer les agence dont le nom et la ville et l'adresse match ceux donnés en querry dans le JPA
* Modification du chemin pour récupérer toutes les agances
  * Avant /agences : maintenant /agences/all

### Compte

* Retrait des éspaces dans l'IBAN

### Carte

* Retrait des tirets dans le numero de carte

### Ameliorations envisagées

* Utiliser des DTO de réponse au lieu de renvoyer les entités
* Ajouter plus de requêtes avec querry param