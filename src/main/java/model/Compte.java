package model;

public class Compte {
    private String numero;
    private String nomClient;
    private double solde;

    public Compte() {}

    public Compte(String numero, String nomClient, double solde) {
        this.numero = numero;
        this.nomClient = nomClient;
        this.solde = solde;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }
    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }

    @Override
    public String toString() {
        return numero + " - " + nomClient + " : " + solde + "€";
    }
}