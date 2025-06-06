package dao;

import model.Transaction;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public void ajouterTransaction(Transaction t) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO transaction (numeroCompte, type, montant, dateOperation) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, t.getNumeroCompte());
            stmt.setString(2, t.getType());
            stmt.setDouble(3, t.getMontant());
            stmt.setTimestamp(4, Timestamp.valueOf(t.getDateOperation()));
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getTransactionsParCompte(String numeroCompte) {
        List<Transaction> liste = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM transaction WHERE numeroCompte = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numeroCompte);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                liste.add(new Transaction(
                    rs.getInt("id"),
                    rs.getString("numeroCompte"),
                    rs.getString("type"),
                    rs.getDouble("montant"),
                    rs.getTimestamp("dateOperation").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
}