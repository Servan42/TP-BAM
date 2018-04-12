# Projet AR - Bus à Agents mobiles

## Descritpion
L'objectif de ce TP est une introduction aux systèmes à agents mobiles, il complète aussi la mise en place d’applications réparties en utilisant la communication basique par TCP et le modèle RMI d’appel distant. Il permet aussi d’aborder succinctement l’aspect méta-modèle qu’une telle programmation peut nécessiter. Il consiste à programmer une application d’agents mobiles élémentaire.

## Membres
* AUBERT Vincent
* CHANET Zoran
* CHARLOT Servan
* CORDAT-AUCLAIR Julien

## Utilisation

### Partie RMI
Il faut d'abord se rendre sur la [branche RMI](https://github.com/Servan42/TP-BAM/tree/RMI) ou télécharger la release [Objectif 1](https://github.com/Servan42/TP-BAM/releases/tag/Objectif1).
Dans cette partie, les fichiers intéressants sont dans le package **source** et pas dans **src**.
Les classes principales qu'il faut lancer sont */source/***LookForHotel.java** (le client) et */source/***Server.java**, avec les configurations suivantes :
* Server : `Configurations/hostel.server1.xml Configurations/hostel.server2.xml DataStore/Annuaire.xml`
* Client : `Paris`

### Partie BAM
La classe principale qu'il faut lancer est [Starter](src/jus/aor/mobilagent/kernel/Starter.java), et ce avec les **4 configurations** suivantes et **dans cet ordre precis** :
1. Serveur d'Annuaire : `Configurations/hostel.server3.xml Server3` 
2. Serveur 1 : `Configurations/hostel.server1.xml Server1`
3. Serveur 2 : `Configurations/hostel.server2.xml Server2`
4. Client : `Configurations/hostel.client1.xml Client1`

Pour chacun, mettre églament les arguments de JVM suivants : `-Djava.security.policy="/home/drzed/Documents/Polytech/S8/AR/TP-BAM/BAM-policy" -Dsun.lang.ClassLoader.allowArraySyntax=true`