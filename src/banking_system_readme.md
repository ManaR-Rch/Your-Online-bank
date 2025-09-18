# Système de Gestion de Comptes Bancaires

## Description du projet

Ce projet est une application console développée en Java 8 pour automatiser la gestion des comptes bancaires. Elle permet aux utilisateurs de créer et gérer différents types de comptes (courant et épargne), d'effectuer des opérations bancaires (versements, retraits, virements) et de consulter l'historique des transactions.

L'application respecte une architecture en couches et implémente les principes SOLID pour garantir un code maintenable et extensible.

## Technologies utilisées

- **Java 8** (JDK 8)
- **Java Time API** pour la gestion des dates
- **Collections Framework** (ArrayList, HashMap)
- **UUID** pour les identifiants uniques
- **Git** pour le contrôle de version
- **Eclipse IDE** pour le développement
- **JIRA** pour la gestion de projet
- **MySQL & JDBC** (bonus - persistance des données)

## Structure du projet

```
src/
├── presentation/
│   ├── MainMenu.java
│   └── ConsoleUtils.java
├── metier/
│   ├── entities/
│   │   ├── Compte.java (classe abstraite)
│   │   ├── CompteCourant.java
│   │   ├── CompteEpargne.java
│   │   ├── Operation.java (classe abstraite)
│   │   ├── Versement.java
│   │   └── Retrait.java
│   └── services/
│       ├── CompteService.java
│       └── OperationService.java
├── utilitaire/
│   ├── ValidationUtils.java
│   ├── FormatUtils.java
│   └── Constants.java
└── Main.java
```

### Architecture en couches

1. **Couche de présentation** : Gestion de l'interface utilisateur et des menus
2. **Couche métier** : Logique métier et entités du domaine
3. **Couche utilitaire** : Classes d'aide et utilitaires

## Fonctionnalités principales

### Gestion des comptes
- ✅ Création de comptes courants avec découvert autorisé
- ✅ Création de comptes épargne avec taux d'intérêt
- ✅ Validation du format de code compte (CPT-XXXXX)
- ✅ Consultation du solde

### Opérations bancaires
- ✅ Versements avec source (ex: "Salaire", "Virement externe")
- ✅ Retraits avec destination (ex: "Distributeur ATM", "Chèque")
- ✅ Virements entre comptes internes
- ✅ Calcul automatique des intérêts pour les comptes épargne

### Consultation et historique
- ✅ Affichage du solde d'un compte
- ✅ Historique complet des opérations par compte
- ✅ Détails des transactions avec dates et montants

### Règles métier
- **Compte Courant** : Retrait possible jusqu'à la limite du découvert
- **Compte Épargne** : Retrait uniquement si solde suffisant
- **Validation** : Montants positifs, format des codes, saisies utilisateur

## Fonctionnalités bonus

- 🔄 **Stream API** pour le filtrage des comptes et opérations
- 💾 **Persistance MySQL** avec JDBC
- 🔧 **Expressions Lambda** pour les traitements fonctionnels
- ⚡ **Optional** pour la gestion des valeurs nulles

## Prérequis

### Logiciels requis
- **JDK 8** (obligatoire - aucune autre version)
- **Eclipse IDE**
- **Git**
- **MySQL** (pour la persistance - bonus)

### Installation de Java 8
```bash
# Vérifier la version Java
java -version
javac -version

# La sortie doit afficher Java 1.8.x
```

## Installation et exécution

### 1. Cloner le repository
```bash
git clone https://github.com/votre-username/banking-system.git
cd banking-system
```

### 2. Compilation
```bash
# Compilation des sources
javac -d bin src/**/*.java

# Ou utiliser le script de compilation
./compile.sh
```

### 3. Création du JAR
```bash
# Créer le fichier JAR exécutable
jar cfm banking-system.jar MANIFEST.MF -C bin .
```

### 4. Exécution
```bash
# Exécuter l'application
java -jar banking-system.jar

# Ou directement
java -cp bin Main
```

## Utilisation

### Menu principal
```
=== SYSTÈME DE GESTION BANCAIRE ===
1. Créer un compte
2. Effectuer un versement
3. Effectuer un retrait
4. Effectuer un virement
5. Consulter le solde
6. Consulter l'historique des opérations
7. Quitter
```

### Exemples d'utilisation

#### Création d'un compte
- Choisir le type (Courant/Épargne)
- Le code sera généré automatiquement (format : CPT-XXXXX)
- Définir le solde initial
- Pour compte courant : définir le découvert autorisé
- Pour compte épargne : définir le taux d'intérêt

#### Opérations bancaires
- Saisir le code du compte
- Indiquer le montant (validation automatique)
- Spécifier la source/destination selon l'opération

## Captures d'écran

### Menu principal
```
=== SYSTÈME DE GESTION BANCAIRE ===
1. Créer un compte
2. Effectuer un versement
3. Effectuer un retrait
4. Effectuer un virement
5. Consulter le solde
6. Consulter l'historique des opérations
7. Quitter
Votre choix : 
```

### Création de compte
```
=== CRÉATION DE COMPTE ===
1. Compte Courant
2. Compte Épargne
Choisissez le type de compte : 1
Code généré : CPT-12345
Solde initial : 1000.00
Découvert autorisé : 500.00
✅ Compte courant créé avec succès !
```

### Historique des opérations
```
=== HISTORIQUE DU COMPTE CPT-12345 ===
ID: 550e8400-e29b-41d4-a716-446655440001
Date: 2025-09-18 14:30:25
Type: VERSEMENT
Montant: +500.00 €
Source: Salaire
Solde après opération: 1500.00 €
----------------------------------------
```

## Bonnes pratiques implémentées

### Principes SOLID
- ✅ **S**ingle Responsibility : Chaque classe a une responsabilité unique
- ✅ **O**pen/Closed : Extension possible sans modification
- ✅ **L**iskov Substitution : Les sous-classes respectent le contrat
- ✅ **I**nterface Segregation : Interfaces spécialisées
- ✅ **D**ependency Inversion : Dépendances vers les abstractions

### Anti-patterns évités
- ❌ God Class
- ❌ Mélange logique métier/affichage
- ❌ Couplage fort
- ❌ Violation de l'encapsulation

## Structure Git

Le projet suit une approche Git avec des commits réguliers et descriptifs :
- `feat:` nouvelles fonctionnalités
- `fix:` corrections de bugs
- `refactor:` refactorisation du code
- `docs:` documentation

## Tests et validation

- ✅ Validation des formats de saisie
- ✅ Gestion des exceptions
- ✅ Tests des règles métier
- ✅ Vérification des contraintes bancaires

## Contributions

Ce projet est développé dans le cadre d'une formation. Les contributions externes ne sont pas acceptées pour le moment.

## Licence

Ce projet est développé à des fins pédagogiques.

## Auteur

**Votre Nom**
- Formation : [Nom de la formation]
- Date : 16-19 Septembre 2025
- Contact : votre.email@example.com

---

**Note** : Ce projet respecte les exigences du cahier des charges et démontre la maîtrise des concepts Java 8, de l'architecture logicielle et des bonnes pratiques de développement.