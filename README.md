# ProjetCroisier

Liste des tâches à faire :

- [x] le joueur possède deux attributs : une puissance (initialisée à 100 par exemple, mais devant être facilement modifiable ...) et des points de vie (initialisés à 10 par exemple, mais devant également être facilement modifiables ...) ;
- [x] un adversaire présent dans le donjon ne disposera que d'un attribut de puissance ;
- [ ] le donjon sera constitué de 16 pièces, initialisées avec un adversaire de puissance aléatoire comprise entre 1 et 150 au lancement de l'application (voir la rubrique 3 ci-après) ;
- [x] dans le cadre de l'activité de combat, le joueur voit ses propres attributs et la puissance de l'adversaire qui se trouve dans la pièce choisie. Il a le choix entre le combattre ou fuir :
  - [x] en cas de fuite, le joueur perd 1 point de vie et l'adversaire reste présent dans sa pièce ;
  - [x] en cas d'attaque et de défaite, le joueur perd 3 points de vie et l'adversaire reste présent dans sa pièce ;
  - [x] en cas d'attaque et de victoire, le joueur gagne 10 points de puissance et la pièce explorée devient vide ;
  - [x] la formule de calcul permettant de déterminer le résultat du combat sera la suivante : res = Pj × Randj − Pa × Randa dans laquelle, Pj représente la puissance du joueur, Pa représente la puissance de l'adversaire, Randx représente un nombre aléatoire compris entre 0 et 1 (voir la rubrique 3 ci-après). Un résultat négatif signifie une défaite du joueur, un résultat positif ou nul une victoire ;
  - [x] l'utilisation du bouton retour de cette activité doit être considérée comme une fuite.
- Dans le cadre de l'activité principale :
  - [x] le joueur doit savoir s'il a gagné, perdu ou fui son combat, par l'affichage d'un message en conséquence ;
  - [x] le libellé d'une pièce doit changer dès que l'adversaire qui s'y trouvait a été battu (cf les X visibles sur les différentes captures d'écran présentes dans la rubrique 4) ;
  - [x] le choix d'une pièce vide (adversaire battu) doit déclencher l'affichage d'un message, sans lancement de l'activité de combat ;
  - [x] lorsque le nombre de points de vie devient ≤ 0, le joueur a perdu, un message s'affiche (cf rubrique 4) et le joueur ne peut plus jouer tant qu'il n'a pas relancé le jeu via le menu ;
  - [ ] lorsque le joueur a vaincu tous ses adversaires, il a gagné la partie et il ne peut plus jouer tant qu'il n'a pas relancé le jeu via le menu ;

Cette seconde partie consite à améliorer la première version du jeu, en tenant compte des demandes qui suivent :

- [x] au niveau de l'interface, les chiffres correspondant à chaque pièce devront être remplacés par des images illustrant l'état de chaque pièce (non explorée, explorée et terminée, explorée mais non terminée) ;
- [x] dans l'interface de combat, une image et un nom permettront d'illustrer l'apparence et l'identité du monstre à combattre ;
- [x] parmi les pièces à explorer, deux contiendront un bonus, placé aléatoirement : soit une potion magique, qui restitue aléatoirement de 1 à 3 points de vie ; soit un charme de puissance, qui augmente celle-ci aléatoirement de 5 à 10 points. Vous envisagerez une manière d'afficher ce bonus lorsque l'utilisateur a la chance de le trouver ;
- [ ] vous prévoirez une option supplémentaire au menu, permettant de changer les paramètres du jeu en début de partie (ce qui pourra être pratique pour les tests), tels que la puissance initiale du joueur, son nombre de points de vie initiaux, la valeur maximale de puissance d'un adversaire, la difificulté de la partie (voir ci-dessous), etc.
- [ ] vous ajouterez également une notion de niveau, le joueur pouvant passer au niveau supérieur dès qu'il a terminé victorieusement le niveau courant (dans ce cas, on ne change pas la difficulté qui est choisie en début de partie). Notez qu'en cas de passage au niveau suivant, la puissance du joueur a été augmentée d'une certaine quantité. Il faut donc que les monstres des niveaux suivants voient leur puissance également augmenter. Le niveau courant devra être affiché dans l'activité principale ;
- [ ] enfin vous ajouterez une sauvegarde des 10 meilleurs scores (nom du joueur, niveau, puissance atteinte, date) pour chaque difficulté.
