package banck.app;

public class Client {
    private int identifiant;
    private String nomComplet;
    private static int compteurClients = 1;

    public Client(String nomComplet) {
        this.identifiant = compteurClients++;
        this.nomComplet = nomComplet;
    }

    public int getIdentifiant() { return identifiant; }
    public String getNomComplet() { return nomComplet; }

    public void afficherInformations() {
        System.out.println("👤 Client ID: " + identifiant);
        System.out.println("📛 Nom: " + nomComplet);
    }
}