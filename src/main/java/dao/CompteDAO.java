package dao;

import model.Compte;
import java.util.List;

public interface CompteDAO {
    Compte getByNumero(String numero);
    List<Compte> getAll();
    void updateSolde(String numero, double nouveauSolde);
    void ajouterCompte(Compte c);
    void supprimerCompte(String numero);
}