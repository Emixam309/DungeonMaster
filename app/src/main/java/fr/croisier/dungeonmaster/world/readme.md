# Package `world`

Ce package contient les classes représentant le monde du jeu, y compris les donjons et les pièces.

## Classes

### `Dungeon`

- **Chemin** : [`Dungeon.java`](Dungeon.java)
- **Description** : Représente un donjon dans le jeu.
- **Fonctionnalités** :
  - Gère la création et l'initialisation des pièces du donjon.
  - Place des objets et des ennemis de manière aléatoire dans les pièces du donjon.
  - Fournit des méthodes pour obtenir et définir les attributs du donjon, tels que les pièces, les rangées, les colonnes et le niveau.
  - Permet de récupérer une pièce spécifique du donjon.
  - Calcule le nombre total de pièces et le nombre de pièces non visitées dans le donjon.
  - Fournit une représentation textuelle du donjon.

### `Room`

- **Chemin** : [`Room.java`](Room.java)
- **Description** : Représente une pièce dans le donjon.
- **Fonctionnalités** :
  - Gère l'entité présente dans la pièce (qui peut être un ennemi ou un objet).
  - Fournit des méthodes pour obtenir et définir l'entité dans la pièce.
  - Gère l'état de visite de la pièce.
  - Fournit une représentation textuelle de la pièce.

## Utilisation

Ces classes sont utilisées pour représenter et gérer le monde du jeu, en particulier les donjons et les pièces que le joueur explore. Elles interagissent avec d'autres classes du projet pour fournir une expérience de jeu cohérente et immersive.
