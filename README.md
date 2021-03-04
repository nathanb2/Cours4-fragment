# Cours4-fragment
Fragments et Bundle pour gstion de sauvegarde de donnees en cas de destruction et recreation automatique d'activity/de fragment


Un Fragment est:

- Un "sous" controler
- Il herite de la classe Fragment de la library Android
- Il a son propre Layout XML
- Il permet de gerer un component de UI (afficher un layout et manipuler ses vues selon la logique voulu)
- Il est toujours supporte par une Activity et se loge dans la vue container que l'on lui attribue dans l'activity (avec un FrameLayout dans XML de l'activity)
- L'activity peut gerer ses fragments (ajouter, remplacer, suprimer..) grace au Fragment manager accessible via getSupportFragmentmanger() dasn l'activity
- Il est supporte par l'activity donc dependant de son lyfeCycle

Un Fragment est compose de:

- Une fonction static newInstance() qui permet de creer une instance et la retourner et eventuellement de sauvegarder des parametres dans les Arguments du frgament voir ci-dessous
- Une fonction onCreateView ou l'on instancie le layout du fragment et recuperons donc un objet View qui est notre layout et que la fonction retourne
- Une fonction oncreateView qui recoit cette view en parametre est peut donc acceder a toutes les views qui composent ce layout avec findViewById()

(Pour sauvegarder des donnees eventuellement passees en parametre de newinstance a la creation au dela de la destruction de l'instance dans le cas d'une rotation (Android dans ce cas detruit puis recrer automatiquement le fragment et nous perdons donc toute nos variables car l'instance n'est plus la meme
-On utilise un Bundle 

Un Bundle est:

- Un objet qui fait office de valise
- Il accepte uniquement des primitifs
- On insert toutjours les donnees sous forme de pairs Key/value afin de pouvoir identifier les donnees dans la valise et pouvoire les recuperer 

Dans le cas d'un fragment:

- Dans newInstance on cree l'nstance du fragment puis on cree une instance de Bundle
- On insert nos parametres recu dans newInstance dans le bundle sous forme de Key/value
- On insert notre bundle dans le fragment avec la fonction setArguments(Bundle bundle) (fonction setter de la variable mArguments de la class mere fragment et que Androis sait faire survivre au changement d'instance)
- Dans le onCeate ou le onVwCeated on recupere nos argugments grace a getArguments() et en recuperons nos donnees grace a leurs Key

ressources: slide 1.1
https://drive.google.com/drive/folders/1MRqvBGEDtNtpDyKd8sulMJreFCz1JxgC?usp=sharing
