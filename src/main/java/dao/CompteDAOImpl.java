package dao;

import model.Compte;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAOImpl implements CompteDAO {

    @Override
    public Compte getByNumero(String numero) {
        Compte compte = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM compte WHERE numero = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                compte = new Compte(
                    rs.getString("numero"),
                    rs.getString("nomClient"),
                    rs.getDouble("solde")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compte;
    }

    @Override
    public List<Compte> getAll() {
        List<Compte> comptes = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM compte";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                comptes.add(new Compte(
                    rs.getString("numero"),
                    rs.getString("nomClient"),
                    rs.getDouble("solde")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comptes;
    }

    @Override
    public void updateSolde(String numero, double nouveauSolde) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE compte SET solde = ? WHERE numero = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, nouveauSolde);
            stmt.setString(2, numero);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ajouterCompte(Compte c) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO compte (numero, nomClient, solde) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getNumero());
            stmt.setString(2, c.getNomClient());
            stmt.setDouble(3, c.getSolde());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerCompte(String numero) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM compte WHERE numero = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numero);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}