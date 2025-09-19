# Système de Gestion Bancaire

## Description du projet

Application console Java 8 pour la gestion complète des comptes bancaires. Ce système permet de créer et gérer des comptes courants et épargne, effectuer des opérations bancaires (versements, retraits, virements), et consulter l'historique des transactions.

## Technologies utilisées

- Java 8 (JDK 1.8)
- Java Time API
- Collections Framework
- UUID
- Stream API
- Optional
- Expressions Lambda

## Structure du projet

```
banck/
├── app/
│   ├── Main.java
│   ├── Client.java
│   ├── Compte.java
│   ├── CompteCourant.java
│   ├── CompteEpargne.java
│   ├── Operation.java
│   ├── Versement.java
│   └── Retrait.java
└── util/
    └── ValidationUtils.java
```

## Fonctionnalités implémentées

- Gestion des clients avec identifiant auto-incrémenté
- Création de comptes avec format CPT-XXXXX validé
- Comptes courants avec découvert autorisé
- Comptes épargne avec taux d'intérêt annuel
- Versements avec source spécifiée
- Retraits avec destination spécifiée
- Virements entre comptes avec rollback automatique
- Historique détaillé de toutes les opérations
- Calcul des intérêts mensuels pour comptes épargne
- Validation stricte des formats et montants
- Interface colorée avec codes ANSI

## Installation et exécution

### Prérequis
- JDK 8 (version 1.8.x)
- Terminal supportant les couleurs ANSI

### Vérification de l'installation
```bash
java -version
javac -version
```

### Compilation
```bash
mkdir bin
javac -d bin -cp . banck/app/*.java banck/util/*.java
```

### Création du JAR
```bash
jar cfe BanqueApp.jar banck.app.Main -C bin .
```

### Exécution
```bash
# Via le JAR
java -jar BanqueApp.jar

# Ou directement
java -cp bin banck.app.Main
```

## Menu principal

```
╔══════════════════════════════════╗
║        GESTION BANCAIRE          ║
╠══════════════════════════════════╣
║ 1. Créer un client               ║
║ 2. Créer un compte               ║
║ 3. Effectuer un versement        ║
║ 4. Effectuer un retrait          ║
║ 5. Effectuer un virement         ║
║ 6. Consulter solde               ║
║ 7. Voir historique opérations    ║
║ 8. Appliquer intérêts (Épargne)  ║
║ 9. Lister tous les comptes       ║
║ 10. Quitter                      ║
╚══════════════════════════════════╝
```

## Règles métier

### Compte Courant
- Retrait autorisé jusqu'au découvert maximum
- Pas d'intérêts calculés
- Solde peut être négatif dans la limite du découvert

### Compte Épargne
- Retrait uniquement si solde suffisant
- Intérêts mensuels calculés sur le solde
- Taux d'intérêt annuel personnalisable

### Validation des données
- Format code compte : CPT-XXXXX (5 chiffres)
- Montants doivent être positifs
- Client doit exister pour créer un compte
- Gestion des erreurs de saisie

## Architecture

L'application respecte les principes SOLID et une architecture en couches :

1. **Couche de présentation** : Main.java
2. **Couche métier** : Classes Compte, Operation, Client
3. **Couche utilitaire** : ValidationUtils.java

## Exemples d'utilisation

### Création d'un compte épargne
```
=== CRÉATION COMPTE ===
Code compte (CPT-XXXXX) : CPT-00123
Solde initial : 5000
Taux d'intérêt annuel (%) : 3.5
✅ Compte créé avec succès
```

### Virement entre comptes
```
=== VIREMENT ===
Compte source : CPT-00123 - Solde: 5000.0 - Client: John Doe
Compte destination : CPT-00124 - Solde: 3000.0 - Client: Jane Smith
Montant : 1000
✅ Virement de 1000.0 DH effectué avec succès
```

## Support

Pour toute question :
- Consulter cette documentation
- Vérifier les prérequis d'installation
- S'assurer d'utiliser Java 8

## Licence

Projet développé à des fins pédagogiques dans le cadre d'une formation Java.