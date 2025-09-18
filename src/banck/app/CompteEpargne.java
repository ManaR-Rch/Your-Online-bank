package banck.app;

public class CompteEpargne extends Compte {
    private double tauxInteretAnnuel;

    public CompteEpargne(String codeCompte, double soldeInitial, Client client, double tauxInteret) {
        super(codeCompte, soldeInitial, client);
        this.tauxInteretAnnuel = tauxInteret;
    }

    @Override
    public void effectuerRetrait(double montant, String destination) {
        if (montant <= 0) {
            System.out.println("✗ Montant de retrait invalide");
            return;
        }

        if (montant <= soldeCompte) {
            soldeCompte -= montant;
            Retrait operation = new Retrait(montant, destination);
            historiqueOperations.add(operation);
            System.out.println("✓ Retrait de " + montant + " effectué avec succès");
        } else {
            System.out.println("✗ Solde insuffisant pour effectuer le retrait");
        }
    }

    @Override
    public double calculerInterets() {
        return soldeCompte * (tauxInteretAnnuel / 100) / 12; // Intérêts mensuels
    }

    public void appliquerInterets() {
        double interets = calculerInterets();
        soldeCompte += interets;
        System.out.println("💰 Intérêts appliqués: " + interets + " DH");
    }

    @Override
    public void afficherDetailsCompte() {
        System.out.println("\n=== COMPTE ÉPARGNE ===");
        System.out.println("🏦 Code: " + codeCompte);
        System.out.println("💳 Solde: " + soldeCompte + " DH");
        System.out.println("📈 Taux d'intérêt: " + tauxInteretAnnuel + "% annuel");
        System.out.println("💰 Intérêts mensuels: " + calculerInterets() + " DH");
        System.out.println("👤 Propriétaire: " + proprietaire.getNomComplet());
        System.out.println("📊 Nombre d'opérations: " + historiqueOperations.size());
    }

    public double getTauxInteretAnnuel() { return tauxInteretAnnuel; }
}