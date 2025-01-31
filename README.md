# Dungeon Master

## Description

Dungeon Master est un jeu où le joueur explore un donjon, combat des ennemis, trouve des objets et améliore sa puissance.
Le jeu est développé en Java pour Android.

## Fonctionnalités

- **Exploration de Donjon** : Le joueur explore un donjon constitué de plusieurs pièces. Chaque pièce peut contenir un ennemi ou un objet.
- **Combat** : Le joueur peut engager des combats avec des ennemis présents dans les pièces. Les combats sont résolus en fonction de la puissance du joueur et de l'ennemi, ainsi que de facteurs aléatoires.
- **Objets** : Le joueur peut trouver des objets tels que des potions de soin et des charmes de puissance qui peuvent être utilisés pour améliorer ses attributs.
- **Niveaux** : Le jeu comporte plusieurs niveaux de difficulté croissante. Le joueur peut passer au niveau suivant après avoir exploré toutes les pièces d'un niveau.
- **Scores** : Les scores des joueurs sont sauvegardés et affichés dans la liste des scores.
- **Configuration** : Le joueur peut configurer les paramètres du jeu, tels que la puissance initiale, les points de vie et la difficulté.

## Structure du Projet

Le projet est organisé en plusieurs packages :

- **activities** : Contient les activités Android pour l'interface utilisateur, telles que `BattleActivity`, `ConfigurationActivity`, `DungeonActivity`, `ScoresActivity`, et `ScoresAdapter`.
- **entities** : Contient les classes représentant les entités du jeu, telles que `Enemy`, `Entity`, `Item`, et `Player`.
- **enums** : Contient les énumérations utilisées dans le jeu, telles que `Difficulty` et `ItemType`.
- **game** : Contient les classes gérant la logique du jeu, telles que `Battle`, `Game`, et `Score`.
- **intents** : Contient les constantes pour les extras des intents utilisés dans les activités.
- **viewmodels** : Contient les ViewModels pour gérer l'état des données de l'application.
- **world** : Contient les classes représentant le monde du jeu, telles que `Dungeon` et `Room`.

## Installation et Exécution

1. Clonez le dépôt du projet.
2. Ouvrez le projet dans Android Studio.
3. Compilez et exécutez l'application sur un émulateur ou un appareil Android.

## Documentation

- [Javadoc](./doc/index.html)