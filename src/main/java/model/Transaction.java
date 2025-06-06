package model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private String numeroCompte;
    private String type;
    private double montant;
    private LocalDateTime dateOperation;

    public Transaction() {}

    public Transaction(String numeroCompte, String type, double montant, LocalDateTime dateOperation) {
        this.numeroCompte = numeroCompte;
        this.type = type;
        this.montant = montant;
        this.dateOperation = dateOperation;
    }

    public Transaction(int id, String numeroCompte, String type, double montant, LocalDateTime dateOperation) {
        this.id = id;
        this.numeroCompte = numeroCompte;
        this.type = type;
        this.montant = montant;
        this.dateOperation = dateOperation;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNumeroCompte() { return numeroCompte; }
    public void setNumeroCompte(String numeroCompte) { this.numeroCompte = numeroCompte; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    public LocalDateTime getDateOperation() { return dateOperation; }
    public void setDateOperation(LocalDateTime dateOperation) { this.dateOperation = dateOperation; }
}