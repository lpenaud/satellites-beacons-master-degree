======================
Satellite et Balises
======================

:author:  - Penaud Loïc 
          - Metz Maxime
:lang: fr

Le paquet `edu.ubo` contient le code source du projet il est découpé en 2 grandes parties :

- `edu.ubo.graphicLayer` : Bibliothèque graphique qu'on ne détaillera pas ici.
- `edu.ubo.satelllitebeacons.main` : Code de source de la simulation.

.. contents::
  :depth: 3
  :backlinks: top

------------------------------
Programmation événementielle
------------------------------
La partie création et logique des événements se trouve dans le paquet `edu.ubo.satelllitebeacons.main.event`.

Événements
============

- `DestinationReachEvent` est émis lorsque qu'un movement atteint sa destination.
- `FullCapacityEvent` est émis lorsque la mémoire de la balise arrive à saturation.
- `MessageEvent` est émis lorsqu'un message est envoyé par un port de communication.
- `PositionChangedEvent` est émis par un élément mobile (satellite et balise) lorsque celui-ci change de position.
- `StartSyncEvent` est émis par un élément mobile (satellite et balise) lorsque celui-ci commence une synchronisation avec un autre élément mobile.
- `StopSyncEvent` est émis par un élément mobile (satellite et balise) lorsque celui-ci vient de finir une synchronisation avec un autre élément mobile.

Gestionnaire d'événement
===========================
Pour faciliter l'émission d'événement, ainsi que l'ajout et la suppression des écouteurs il existe la classe `EventManager`.

.. caution::

  Le manager référence les écouteurs en référence forte.
  Donc tout objet référencé par un écouteur ne sera pas supprimé même après le passage du ramasse-miettes.

Port de communication
========================
La classe `chanel.Port` permet d'envoyer un message du type `<T>` à destination des écouteurs.
Celle-ci est inspirée par l'`API de canal de messagerie en JavaScript`_.

.. _API: https://developer.mozilla.org/en-US/docs/Web/API/Channel_Messaging_API

---------
À faire
--------
Events
Référence méthodes
Parallels

Amélioration possible
Référence faible listener ?
Cadrage Movement

