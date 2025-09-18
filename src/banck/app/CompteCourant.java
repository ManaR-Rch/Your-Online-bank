package banck.app;

public class CompteCourant extends Compte {
    private double autorisationDecouvert;

    public CompteCourant(String codeCompte, double soldeInitial, Client client, double decouvertAutorise) {
        super(codeCompte, soldeInitial, client);
        this.autorisationDecouvert = decouvertAutorise;
    }

    @Override
    public void effectuerRetrait(double montant, String destination) {
        if (montant <= 0) {
            System.out.println("✗ Montant de retrait invalide");
            return;
        }

        double soldeApresRetrait = soldeCompte - montant;
        
        if (soldeApresRetrait >= -autorisationDecouvert) {
            soldeCompte = soldeApresRetrait;
            Retrait operation = new Retrait(montant, destination);
            historiqueOperations.add(operation);
            System.out.println("✓ Retrait de " + montant + " effectué avec succès");
        } else {
            System.out.println("✗ Opération refusée : dépassement du découvert autorisé");
            System.out.println("Découvert maximum: " + autorisationDecouvert);
        }
    }

    @Override
    public double calculerInterets() {
        return 0; // Compte courant sans intérêts
    }

    @Override
    public void afficherDetailsCompte() {
        System.out.println("\n=== COMPTE COURANT ===");
        System.out.println("🏦 Code: " + codeCompte);
        System.out.println("💳 Solde: " + soldeCompte + " DH");
        System.out.println("📉 Découvert autorisé: " + autorisationDecouvert + " DH");
        System.out.println("👤 Propriétaire: " + proprietaire.getNomComplet());
        System.out.println("📊 Nombre d'opérations: " + historiqueOperations.size());
    }

    public double getAutorisationDecouvert() { return autorisationDecouvert; }
}