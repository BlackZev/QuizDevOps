┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────
└─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘


Bonjour Ludivine.
J'ai réalisé ces points :
  - Création back des quiz avec persistance
  - Création back pour répondre aux quiz
  - Calcul automatique de la note du quiz

Ce point est en cours : Mise en production sur DockerHub (CD ici)
--> La CI fail car je n'arrive pas à me login à DockerHub.


┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────
└─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘


# Voici mon shéma :

               ┌─────────────────────┐
               │     Développeur     │
               └─────────┬───────────┘
                         │
                         ▼
         ┌────────────────────────────────┐
         │     Application Full Stack     │
         │       - Front (Angular)        │
         │       - Back (Spring)          │
         │       - BDD (MySQL             │
         └────────────────┬───────────────┘
                          │
                          ▼
              ┌─────────────────────┐
              │     Dockerfiles     │  ← 1 pour front, 1 pour back
              └─────────┬───────────┘
                        │
                        ▼
          ┌────────────────────────────┐
          │     docker-compose.yml     │ ← Regroupe et orchestre les conteneurs
          └────────────┬───────────────┘
                       │
                       ▼
          ┌───────────────────────────┐
          │     Environnement local   │ ← Lancement en local pour dev/test
          └──────────────┬────────────┘
                         │
                         ▼
             ┌─────────────────────┐
             │     GitHub Push     │ ← Commit + push déclenche CI
             └───────────┬─────────┘
                         ▼
      ┌─────────────────────────────────────┐
      │        GitHub Actions (CI)          │
      │         (Test intégration)          │
      │ - Build de l’app                    │
      │ - Exécution des tests               │
      │   • Tests unitaires (Back + Front)  │
      │   • Tests fonctionnels              │
      │ - Création image Docker             │
      │ - Push sur Docker Hub / Déploiement │
      └─────────────────┬───────────────────┘
                        ▼
              ┌────────────────────┐
              │     Production     │ ← Déploiement automatique (CD)
              └────────────────────┘


┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────
└─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘


# Questions :
  - Comment définiriez-vous le Devops ?
Pour moi, Devops est une philosophie de développement accès sur la collaboration des parties prenantes d'un projet informatique (développeurs, architectes réseaux, product owner, etc.)
Permet d'automatiser et fluidité les processus de leur initialisation à leur mise en production

  - Qu’impose le Devops ?
Le DevOps impose aux collaborateurs d'automatiser leurs tâches, développement et intégration continus 

  - Quels sont les inconvénients ou les faiblesses du Devops ?
Inconvénients :
a. Complexe à mettre en place pour des néophites.
b. Nécéssite un projet de taille suffisante (ni trop gros, ni trop petit)
c. Nécessite une communication effective entre les collaborateurs

  - Quel est votre avis sur le Devops ?
Pour moi, c'est une bonne philosophie de programmation à mettre en place, si elle bien utilisé.
Je pense cependant qu’il est nécessaire d’avoir un "référent" afin de guider les gens au travers de cette méthode de travail. Cela évite de perdre du temps ou de mal appliquer les principes.

  - Quels sont les tests primordiaux pour toute application ?
Tests unitaires : pour vérifier que chaque fonction ou méthode fait bien ce qu’on attend. (Mockito par ex (dans le cas de JAVA))
Tests d’intégration : pour s’assurer que les différentes parties de l’application fonctionnent bien ensemble. (Pipeline CI/CD)
Tests fonctionnels : pour simuler les actions de l’utilisateur et tester les cas réels d’utilisation. (Selenium)
Tests de sécurité : pour détecter les failles possibles comme les injections ou les accès non autorisés. (JWT)


┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────
└─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘
