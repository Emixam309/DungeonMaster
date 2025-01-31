# Package `activities`

Ce package contient les activités Android pour l'interface utilisateur, y compris les activités de combat, de configuration, d'exploration de donjon et d'affichage des scores.

## Classes

### `DungeonActivity`

- **Chemin** : [`DungeonActivity.java`](DungeonActivity.java)
- **Description** : Représente l'activité d'exploration du donjon dans le jeu.
- **Fonctionnalités** :
  - Affiche la grille des pièces du donjon et met à jour les icônes des boutons en fonction de l'état des pièces.
  - Gère les interactions avec les pièces, y compris la découverte d'objets et les combats avec les ennemis.
  - Affiche des dialogues pour les objets trouvés et les résultats des combats.
  - Vérifie si le niveau du donjon est terminé et met à jour l'interface utilisateur en conséquence.

### `BattleActivity`

- **Chemin** : [`BattleActivity.java`](BattleActivity.java)
- **Description** : Représente l'activité de combat entre le joueur et un ennemi.
- **Fonctionnalités** :
  - Affiche les détails du joueur et de l'ennemi.
  - Gère les actions de combat, y compris les attaques et les fuites.
  - Met à jour l'état du jeu en fonction du résultat du combat.
  - Gère les retours en arrière pour annuler l'action de combat.
  - Récupère et affiche une image de l'ennemi en fonction de son nom.

### `ConfigurationActivity`

- **Chemin** : [`ConfigurationActivity.java`](ConfigurationActivity.java)
- **Description** : Permet de configurer les paramètres du jeu avant de commencer l'exploration du donjon.
- **Fonctionnalités** :
  - Permet de saisir le nom du joueur et de sélectionner le niveau de difficulté.
  - Affiche des options de configuration supplémentaires pour la difficulté personnalisée.
  - Valide les champs de saisie et initialise les attributs du joueur.
  - Lance l'activité `DungeonActivity` avec les paramètres configurés.

### `ScoresActivity`

- **Chemin** : [`ScoresActivity.java`](ScoresActivity.java)
- **Description** : Affiche la liste des scores des joueurs.
- **Fonctionnalités** :
  - Charge les scores à partir d'un fichier et les affiche dans un `RecyclerView`.
  - Utilise l'adaptateur `ScoresAdapter` pour afficher les scores triés par ordre décroissant.

### `ScoresAdapter`

- **Chemin** : [`ScoresAdapter.java`](ScoresAdapter.java)
- **Description** : Adaptateur pour afficher les scores dans un `RecyclerView`.
- **Fonctionnalités** :
  - Lit les scores à partir d'un fichier et les trie par ordre décroissant.
  - Fournit des méthodes pour créer et lier les vues des éléments de score.
  - Gère les vues des éléments de score, y compris la date/heure, le nom du joueur, le score, la difficulté et le niveau.

## Utilisation

Ces classes sont utilisées pour gérer les différentes activités de l'application, y compris la configuration du jeu, l'exploration du donjon, les combats et l'affichage des scores. Elles interagissent avec d'autres classes du projet pour fournir une expérience de jeu cohérente et immersive.