## Structure du Projet

Le projet est organisé en plusieurs packages :

- [**activities**](activities) : Contient les activités Android pour l'interface utilisateur :
  - [`BattleActivity`](activities/BattleActivity.java) : Gère les combats entre le joueur et les ennemis.
  - [`ConfigurationActivity`](activities/ConfigurationActivity.java) : Permet de configurer les paramètres du jeu avant de commencer.
  - [`DungeonActivity`](activities/DungeonActivity.java) : Gère l'exploration du donjon par le joueur.
  - [`ScoresActivity`](activities/ScoresActivity.java) : Affiche la liste des scores des joueurs.
  - [`ScoresAdapter`](activities/ScoresAdapter.java) : Adaptateur pour afficher les scores dans un RecyclerView.

- [**entities**](entities) : Contient les classes représentant les entités du jeu :
  - [`Enemy`](entities/Enemy.java) : Représente un ennemi dans le jeu.
  - [`Entity`](entities/Entity.java) : Classe de base pour toutes les entités du jeu.
  - [`Item`](entities/Item.java) : Représente un objet que le joueur peut trouver et utiliser.
  - [`Player`](entities/Player.java) : Représente le joueur dans le jeu.

- [**enums**](enums) : Contient les énumérations utilisées dans le jeu :
  - [`Difficulty`](enums/Difficulty.java) : Enumération des niveaux de difficulté du jeu.
  - [`ItemType`](enums/ItemType.java) : Enumération des types d'objets que le joueur peut trouver.

- [**game**](game) : Contient les classes gérant la logique du jeu :
  - [`Battle`](game/Battle.java) : Gère les combats entre le joueur et les ennemis.
  - [`Game`](game/Game.java) : Représente l'état et la logique du jeu.
  - [`Score`](game/Score.java) : Gère les scores des joueurs.

- [**intents**](intents) : Contient les constantes pour les extras des intents utilisés dans les activités.
  - [`BattleIntents`](intents/BattleIntents.java) : Constantes pour les extras des intents utilisés dans `BattleActivity`.
  - [`DungeonIntents`](intents/DungeonIntents.java) : Constantes pour les extras des intents utilisés dans `DungeonActivity`.

- [**viewmodels**](viewmodels) : Contient les ViewModels pour gérer l'état des données de l'application.
  - [`RoomViewModel`](viewmodels/RoomViewModel.java) : ViewModel pour gérer l'état d'une pièce du donjon.

- [**world**](world) : Contient les classes représentant le monde du jeu :
  - [`Dungeon`](world/Dungeon.java) : Représente un donjon dans le jeu.
  - [`Room`](world/Room.java) : Représente une pièce dans le donjon.

### Diagramme de Classe

![Diagramme de Classe](/doc/project-structure.png)