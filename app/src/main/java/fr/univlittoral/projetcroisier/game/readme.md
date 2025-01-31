# Package `game`

Ce package contient les classes principales qui gèrent la logique du jeu, y compris les combats, l'état du jeu et les scores.

## Classes

### `Battle`

- **Chemin** : [`Battle.java`](Battle.java)
- **Description** : Représente un combat entre le joueur et un ennemi dans une pièce.
- **Fonctionnalités** :
  - Gère la logique des attaques, des victoires, des défaites et des fuites.
  - Calcule le résultat du combat en fonction de la puissance du joueur et de l'ennemi.
  - Met à jour le score du jeu en fonction du résultat du combat.

### `Game`

- **Chemin** : [`Game.java`](Game.java)
- **Description** : Représente l'état et la logique globale du jeu.
- **Fonctionnalités** :
  - Gère le joueur, la difficulté, le donjon, le score et le niveau actuel.
  - Permet d'augmenter le score en fonction des actions du joueur.
  - Fournit des méthodes pour obtenir et définir les attributs du jeu, tels que le joueur, la difficulté, le donjon, le score et le niveau.

### `Score`

- **Chemin** : [`Score.java`](Score.java)
- **Description** : Gère les scores des joueurs.
- **Fonctionnalités** :
  - Sauvegarde le score actuel du jeu dans un fichier.
  - Lit les scores à partir d'un fichier et les charge dans une liste.

## Utilisation

Ces classes sont utilisées pour gérer les aspects principaux du jeu, tels que les combats, l'état du jeu et la gestion des scores. Elles interagissent avec d'autres classes du projet pour fournir une expérience de jeu cohérente et fluide.
