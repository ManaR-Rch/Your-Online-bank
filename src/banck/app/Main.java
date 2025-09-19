package banck.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {
    private static final String RESET = "\u001B[0m";
    private static final String ROUGE = "\u001B[31m";
    private static final String VERT = "\u001B[32m";
    private static final String JAUNE = "\u001B[33m";
    private static final String BLEU = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";

    private static Map<String, Compte> registreComptes = new HashMap<>();
    private static Map<Integer, Client> registreClients = new HashMap<>();
    private static Scanner lecteur = new Scanner(System.in);

    private static int lireEntierSecurise(String message) {
        while (true) {
            try {
                System.out.print(message);
                return lecteur.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ROUGE + "❌ Veuillez entrer un nombre valide" + RESET);
                lecteur.nextLine();
            }
        }
    }

    private static double lireDoubleSecurise(String message) {
        while (true) {
            try {
                System.out.print(message);
                return lecteur.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(ROUGE + "❌ Veuillez entrer un nombre valide" + RESET);
                lecteur.nextLine();
            }
        }
    }

    public static boolean validerFormatCode(String code) {
        return code.matches("^CPT-\\d{5}$");
    }

    public static void afficherMenuPrincipal() {
        System.out.println(CYAN + "\n╔══════════════════════════════════╗");
        System.out.println("║        GESTION BANCAIRE          ║");
        System.out.println("╠══════════════════════════════════╣" + RESET);
        System.out.println(JAUNE + "║ 1. Créer un client                 ║");
        System.out.println("║ 2. Créer un compte                ║");
        System.out.println("║ 3. Effectuer un versement         ║");
        System.out.println("║ 4. Effectuer un retrait           ║");
        System.out.println("║ 5. Effectuer un virement          ║");
        System.out.println("║ 6. Consulter solde                ║");
        System.out.println("║ 7. Voir historique opérations     ║");
        System.out.println("║ 8. Appliquer intérêts (Épargne)   ║");
        System.out.println("║ 9. Lister tous les comptes        ║");
        System.out.println(ROUGE + "║ 10. Quitter                        ║");
        System.out.println(CYAN + "╚══════════════════════════════════╝" + RESET);
        System.out.print(JAUNE + "Votre choix : " + RESET);
    }

    public static void main(String[] args) {
        int choix;

        do {
            afficherMenuPrincipal();
            choix = lireEntierSecurise("");
            lecteur.nextLine();

            try {
                switch (choix) {
                    case 1:
                        creerNouveauClient();
                        break;
                    case 2:
                        creerNouveauCompte();
                        break;
                    case 3:
                        effectuerOperationVersement();
                        break;
                    case 4:
                        effectuerOperationRetrait();
                        break;
                    case 5:
                        effectuerVirement();
                        break;
                    case 6:
                        consulterSoldeCompte();
                        break;
                    case 7:
                        consulterHistoriqueOperations();
                        break;
                    case 8:
                        appliquerInteretsCompte();
                        break;
                    case 9:
                        listerTousComptes();
                        break;
                    case 10:
                        System.out.println(VERT + "👋 Au revoir !" + RESET);
                        break;
                    default:
                        System.out.println(ROUGE + "❌ Choix invalide" + RESET);
                }
            } catch (Exception e) {
                System.out.println(ROUGE + "❌ Une erreur inattendue s'est produite: " + e.getMessage() + RESET);
            }
        } while (choix != 10);
    }

    private static void creerNouveauClient() {
        System.out.println(CYAN + "\n=== CRÉATION CLIENT ===" + RESET);
        System.out.print("Nom complet : ");
        String nom = lecteur.nextLine();

        Client nouveauClient = new Client(nom);
        registreClients.put(nouveauClient.getIdentifiant(), nouveauClient);

        System.out.println(VERT + "✅ Client créé - ID: " + nouveauClient.getIdentifiant() + RESET);
    }

    private static void creerNouveauCompte() {
        if (registreClients.isEmpty()) {
            System.out.println(ROUGE + "❌ Aucun client existant" + RESET);
            return;
        }

        System.out.println(CYAN + "\n=== CRÉATION COMPTE ===" + RESET);
        
        
        System.out.println("Clients disponibles:");
        for (Client client : registreClients.values()) {
            System.out.println("ID: " + client.getIdentifiant() + " - " + client.getNomComplet());
        }
        
        System.out.print("ID client : ");
        int idClient = lecteur.nextInt();
        lecteur.nextLine();

        Client client = registreClients.get(idClient);
        if (client == null) {
            System.out.println(ROUGE + "❌ Client introuvable" + RESET);
            return;
        }

        
        System.out.print("Code compte (CPT-XXXXX) : ");
        String code = lecteur.nextLine();
        
        if (!validerFormatCode(code)) {
            System.out.println(ROUGE + "❌ Format invalide" + RESET);
            return;
        }
        if (registreComptes.containsKey(code)) {
            System.out.println(ROUGE + "❌ Compte déjà existant" + RESET);
            return;
        }

      
        System.out.print("Solde initial : ");
        double solde = lecteur.nextDouble();
        lecteur.nextLine();

      
        System.out.println("Type de compte:");
        System.out.println("1. Compte Courant");
        System.out.println("2. Compte Épargne");
        System.out.print("Choix : ");
        int type = lecteur.nextInt();
        lecteur.nextLine();

        Compte nouveauCompte = null;
        
        switch (type) {
            case 1:
                System.out.print("Découvert autorisé : ");
                double decouvert = lecteur.nextDouble();
                nouveauCompte = new CompteCourant(code, solde, client, decouvert);
                break;
            case 2:
                System.out.print("Taux d'intérêt annuel (%) : ");
                double taux = lecteur.nextDouble();
                nouveauCompte = new CompteEpargne(code, solde, client, taux);
                break;
            default:
                System.out.println(ROUGE + "❌ Type invalide" + RESET);
                return;
        }

        registreComptes.put(code, nouveauCompte);
        System.out.println(VERT + "✅ Compte créé avec succès" + RESET);
    }

    private static Compte selectionnerCompte() {
        if (registreComptes.isEmpty()) {
            System.out.println(ROUGE + "❌ Aucun compte existant" + RESET);
            return null;
        }

        System.out.println("Comptes disponibles:");
        for (Compte compte : registreComptes.values()) {
            System.out.println("Code: " + compte.getCodeCompte() + 
                             " - Solde: " + compte.getSoldeCompte() + 
                             " - Client: " + compte.getProprietaire().getNomComplet());
        }

        System.out.print("Code compte : ");
        String code = lecteur.nextLine();

        Compte compte = registreComptes.get(code);
        if (compte == null) {
            System.out.println(ROUGE + "❌ Compte introuvable" + RESET);
        }
        return compte;
    }

    private static void effectuerOperationVersement() {
        Compte compte = selectionnerCompte();
        if (compte == null) return;

        System.out.print("Montant : ");
        double montant = lecteur.nextDouble();
        lecteur.nextLine();
        
        System.out.print("Source : ");
        String source = lecteur.nextLine();

        compte.effectuerVersement(montant, source);
    }

    private static void effectuerOperationRetrait() {
        Compte compte = selectionnerCompte();
        if (compte == null) return;

        System.out.print("Montant : ");
        double montant = lecteur.nextDouble();
        lecteur.nextLine();
        
        System.out.print("Destination : ");
        String destination = lecteur.nextLine();

        compte.effectuerRetrait(montant, destination);
    }

    private static void consulterSoldeCompte() {
        Compte compte = selectionnerCompte();
        if (compte == null) return;

        compte.afficherDetailsCompte();
    }

    private static void consulterHistoriqueOperations() {
        Compte compte = selectionnerCompte();
        if (compte == null) return;

        System.out.println(CYAN + "\n=== HISTORIQUE DES OPÉRATIONS ===" + RESET);
        if (compte.getHistoriqueOperations().isEmpty()) {
            System.out.println("Aucune opération enregistrée");
        } else {
            for (Operation operation : compte.getHistoriqueOperations()) {
                operation.afficherDetailsOperation();
            }
        }
    }

    private static void appliquerInteretsCompte() {
        try {
            Compte compte = selectionnerCompte();
            if (compte == null) return;

            if (compte instanceof CompteEpargne) {
                ((CompteEpargne) compte).appliquerInterets();
                System.out.println(VERT + "✅ Intérêts appliqués avec succès" + RESET);
            } else {
                System.out.println(ROUGE + "❌ Cette fonctionnalité est réservée aux comptes épargne" + RESET);
            }
        } catch (Exception e) {
            System.out.println(ROUGE + "❌ Erreur lors de l'application des intérêts: " + e.getMessage() + RESET);
        }
    }

    private static void listerTousComptes() {
        if (registreComptes.isEmpty()) {
            System.out.println(ROUGE + "❌ Aucun compte existant" + RESET);
            return;
        }

        System.out.println(CYAN + "\n=== LISTE DES COMPTES ===" + RESET);
        for (Compte compte : registreComptes.values()) {
            compte.afficherDetailsCompte();
            System.out.println("-----------------------------");
        }
    }

    private static void effectuerVirement() {
        System.out.println("=== VIREMENT ===");
        
        System.out.println("Compte source :");
        Compte source = selectionnerCompte();
        if (source == null) return;
        
        System.out.println("Compte destination :");
        Compte destination = selectionnerCompte();
        if (destination == null) return;
        
        System.out.print("Montant : ");
        double montant = lecteur.nextDouble();
        lecteur.nextLine();
        
        if (montant <= 0) {
            System.out.println("❌ Montant invalide");
            return;
        }
        
        
        source.effectuerRetrait(montant, "Virement vers " + destination.getCodeCompte());
        
        
        destination.effectuerVersement(montant, "Virement de " + source.getCodeCompte());
        
        System.out.println("✅ Virement effectué avec succès");
    }
}