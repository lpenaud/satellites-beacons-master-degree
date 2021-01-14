======================
Satellites et Balises
======================

:authors: - Penaud Loïc 
          - Metz Maxime
:lang: fr

Le paquetage ``edu.ubo`` contient le code source du projet il est découpé en 2 grandes parties :

- ``edu.ubo.graphicLayer`` : Bibliothèque graphique qu'on ne détaillera pas ici.
- ``edu.ubo.satelllitebeacons.main`` : Code de source de la simulation.

.. contents::
  :depth: 3
  :backlinks: top

----------------------
Gestion d'événements
----------------------
La partie création et logique des événements se trouve dans le paquetage ``edu.ubo.satelllitebeacons.main.event``.

Événements
============
Touts les événements héritent de la classe ``java.util.EventObject``.

- ``DestinationReachEvent`` est émis lorsque qu'un movement atteint sa destination.
- ``FullCapacityEvent`` est émis lorsque la mémoire de la balise arrive à saturation.
- ``MessageEvent`` est émis lorsqu'un message est envoyé par un port de communication.
- ``PositionChangedEvent`` est émis par un élément mobile (satellite et balise) lorsque celui-ci change de position.
- ``StartSyncEvent`` est émis par un élément mobile (satellite et balise) lorsque celui-ci commence une synchronisation avec un autre élément mobile.
- ``StopSyncEvent`` est émis par un élément mobile (satellite et balise) lorsque celui-ci vient de finir une synchronisation avec un autre élément mobile.

Gestionnaire d'événement
===========================
Pour faciliter l'émission d'événement, ainsi que l'ajout et la suppression des écouteurs il existe la classe ``EventManager``.

.. caution::

  Le manager référence les écouteurs en référence forte.
  Donc tout objet référencé par un écouteur ne sera pas supprimé même après le passage du ramasse-miettes.

Port de communication
========================
La classe ``chanel.Port`` permet d'envoyer un message du type ``<T>`` à destination des écouteurs.
Celle-ci est inspirée par `l'API de canal de messagerie en JavaScript`_.

.. _`l'API de canal de messagerie en JavaScript`: https://developer.mozilla.org/en-US/docs/Web/API/Channel_Messaging_API

------------------
Élements mobiles
------------------
Les éléments mobiles se trouvent dans le paquetage ``movable``.
Toutes les classes qui réprésentent un élément mobile héritent de la classe abstraite ``Movable``.

Balise
========
La classe ``Beacon`` a pour but de simuler le comportement du balise.

Une balise est équipé de différents capteurs qui enregistrent des informations sur sa mémoire.
Lorsque sa mémoire est pleine la balise doit retourner au niveau de la mer pour transférer son contenue à un satellite.

Une balise est mis au courant des déplacements des satellites lorsque celle-ci écoute le port de communication ``Port<Satellite>``.
En effet un satellite doit être suffisament près en axe `y` de la balise pour pouvoir assurer un transfère de données.

Satellite
===========


Movement
==========


----------------------------
Représentation de l'espace
----------------------------


------------
Simulation
------------


Composants graphique
======================


Constantes
============


------------
Conclusion
------------
Bogues connus, gestion d'événements, amélioration de la conception de mouvement.
