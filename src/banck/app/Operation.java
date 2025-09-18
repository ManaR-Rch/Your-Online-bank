package banck.app;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public abstract class Operation {
    private UUID identifiantUnique;
    private LocalDateTime dateHeureOperation;
    private double montantOperation;

    public Operation(double montant) {
        this.identifiantUnique = UUID.randomUUID();
        this.dateHeureOperation = LocalDateTime.now();
        this.montantOperation = montant;
    }

    public UUID getIdentifiantUnique() { return identifiantUnique; }
    public LocalDateTime getDateHeureOperation() { return dateHeureOperation; }
    public double getMontantOperation() { return montantOperation; }

    public void afficherDetailsOperation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("🔢 ID: " + identifiantUnique.toString().substring(0, 8));
        System.out.println("📅 Date: " + dateHeureOperation.format(formatter));
        System.out.println("💰 Montant: " + montantOperation + " DH");
    }
}