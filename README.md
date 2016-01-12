# Projet pédagogique 4A IAM

## Gestion évènementielle
-------------------------

L’application permettra de gérer différents types d'évènements (conférences, meetups, workshops, ...). Chaque évènement a comme information principale son titre, sa description, sa date un logo associé.

- Un évènement *Event* est lié à une catégorie *Category*, un createur *User* et a des participants *User*
- Une Category est composée d'une chaine de caratere *name*.
- Un *User* est représenter par un nom, prénom, email, password.
  - le password devra être hash et ne jamais être transmis dans les échanges clients/serveur. Excepté lors de sa création.  

## Consignes
-------------
### Serveur 
Le serveur NodeJS/ExpressJS utilisera une base MongoDB avec l'ODM Mongoose pour stocker les données.
Il devra être capable de répondre aux requêtes HTTP faire par un autre client que celui développé dans ce projet.

### Client 
>#### (FO)
>- Le client (de votre choix, mobile, brower ou desktop) communiquera via une API REST.
>- La page affichera les évènements, paginés, avec 
>- la capacité de trier par chronologie 
>- la possibilité de filtrer par category ou par date (Passé / A venir / All).

>#### (BO)
>- Créer une category (authentification requise)
>- Modifier/supprimer une catégorie (uniquement si la catégory n'a pas d'evenement attaché)
>- Créer un evenement (authentification requise)
>- Modifier/Supprimer un evenement, seul le créateur en est capable
>- S'enregistrer comme participation à un evenement (l'auteur participe par default)
>- Se désinscrire d'un evenement (impossible pour l'auteur).


Une documentation sera fournie pour détailler installation l'application et le lancement/configuraiton du client et du serveur.  
Une présentation ( 5 slides ) devra être également fourni en début de soutenance pour présenter le travaille de chacun et les difficultés rencontrées.  

##Notation
-----------
- Respect des fonctionnalités 10pts
- Respect des normes REST 6pts
- Qualité de code/commit/présentation 4pts


#### Happy Coding! © julien-sarazin teaching @ esgi 2015-2016
