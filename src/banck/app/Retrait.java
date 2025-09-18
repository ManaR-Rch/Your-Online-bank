package banck.app;

public class Retrait extends Operation {
    private String lieuRetrait;

    public Retrait(double montant, String destination) {
        super(montant);
        this.lieuRetrait = destination;
    }

    @Override
    public void afficherDetailsOperation() {
        System.out.println("\n=== OPÉRATION DE RETRAIT ===");
        super.afficherDetailsOperation();
        System.out.println("📍 Lieu: " + lieuRetrait);
        System.out.println("📋 Type: Retrait");
    }

    public String getLieuRetrait() { return lieuRetrait; }
}