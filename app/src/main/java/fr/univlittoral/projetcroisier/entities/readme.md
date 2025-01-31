# Package `entities`

Ce package contient les classes représentant les entités du jeu, y compris les ennemis, les objets et le joueur.

## Classes

### `Entity`

- **Chemin** : [`Entity.java`](Entity.java)
- **Description** : Classe abstraite représentant une entité générique dans le jeu.

### `Enemy`

- **Chemin** : [`Enemy.java`](Enemy.java)
- **Description** : Représente un ennemi dans le jeu.
- **Fonctionnalités** :
  - Hérite de la classe `Entity`.
  - Gère la puissance de l'ennemi.
  - Fournit des méthodes pour obtenir et définir la puissance de l'ennemi.
  - Initialise la puissance et le nom de l'ennemi de manière aléatoire en fonction de la difficulté et du niveau.
  - Fournit une représentation textuelle de l'ennemi.

### `Item`

- **Chemin** : [`Item.java`](Item.java)
- **Description** : Représente un objet dans le jeu.
- **Fonctionnalités** :
  - Hérite de la classe `Entity`.
  - Gère le type de l'objet.
  - Fournit des méthodes pour obtenir et définir le type de l'objet.
  - Permet d'utiliser l'objet sur un joueur pour modifier ses attributs (santé ou puissance).
  - Fournit une représentation textuelle de l'objet.

### `Player`

- **Chemin** : [`Player.java`](Player.java)
- **Description** : Représente un joueur dans le jeu.
- **Fonctionnalités** :
  - Implémente l'interface `Serializable`.
  - Gère le nom, la santé et la puissance du joueur.
  - Fournit des méthodes pour obtenir et définir les attributs du joueur (nom, santé, puissance).
  - Initialise les attributs du joueur en fonction du niveau de difficulté.
  - Fournit une représentation textuelle du joueur.

## Utilisation

Ces classes sont utilisées pour représenter les différentes entités du jeu, telles que les ennemis, les objets et le joueur. Elles interagissent avec d'autres classes du projet pour fournir une expérience de jeu cohérente et immersive.
