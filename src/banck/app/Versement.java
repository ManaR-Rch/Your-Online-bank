package banck.app;

public class Versement extends Operation {
    private String sourceVersement;

    public Versement(double montant, String origine) {
        super(montant);
        this.sourceVersement = origine;
    }

    @Override
    public void afficherDetailsOperation() {
        System.out.println("\n=== OPÉRATION DE VERSEMENT ===");
        super.afficherDetailsOperation();
        System.out.println("📥 Source: " + sourceVersement);
        System.out.println("📋 Type: Versement");
    }

    public String getSourceVersement() { return sourceVersement; }
}