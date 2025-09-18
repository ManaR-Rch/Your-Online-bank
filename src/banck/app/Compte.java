package banck.app;
import java.util.List;
import java.util.ArrayList;

public abstract class Compte {
    protected String codeCompte;
    protected double soldeCompte;
    protected List<Operation> historiqueOperations;
    protected Client proprietaire;

    public Compte(String codeCompte, double soldeInitial, Client client) {
        this.codeCompte = codeCompte;
        this.soldeCompte = soldeInitial;
        this.historiqueOperations = new ArrayList<>();
        this.proprietaire = client;
    }

    public String getCodeCompte() { return codeCompte; }
    public double getSoldeCompte() { return soldeCompte; }
    public Client getProprietaire() { return proprietaire; }
    public List<Operation> getHistoriqueOperations() { return historiqueOperations; }

    protected void modifierSolde(double nouveauSolde) { 
        this.soldeCompte = nouveauSolde; 
    }

    public void effectuerVersement(double montant, String origine) {
        if (montant > 0) {
            soldeCompte += montant;
            Versement operation = new Versement(montant, origine);
            historiqueOperations.add(operation);
            System.out.println("✓ Versement de " + montant + " effectué avec succès");
        } else {
            System.out.println("✗ Montant de versement invalide");
        }
    }

    public abstract void effectuerRetrait(double montant, String destination);
    public abstract double calculerInterets();
    public abstract void afficherDetailsCompte();
}