package view;

import dao.CompteDAOImpl;
import dao.TransactionDAOImpl;
import model.Compte;
import model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionView extends JFrame {
    private JTextField txtNumero, txtMontant;
    private JComboBox<String> cbType;
    private DefaultTableModel model;
    private JTable table;

    private CompteDAOImpl compteDAO = new CompteDAOImpl();
    private TransactionDAOImpl transactionDAO = new TransactionDAOImpl();

    public TransactionView() {
        setTitle("Gestion Transactions");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        txtNumero = new JTextField();
        txtMontant = new JTextField();
        cbType = new JComboBox<>(new String[]{"DEBIT", "CREDIT"});
        JButton btnValider = new JButton("Effectuer");

        topPanel.add(new JLabel("Numéro Compte:"));
        topPanel.add(txtNumero);
        topPanel.add(new JLabel("Montant:"));
        topPanel.add(txtMontant);
        topPanel.add(new JLabel("Type:"));
        topPanel.add(cbType);
        topPanel.add(btnValider);

        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"ID", "Type", "Montant", "Date"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnValider.addActionListener((ActionEvent e) -> {
            String numero = txtNumero.getText();
            String type = (String) cbType.getSelectedItem();
            double montant = Double.parseDouble(txtMontant.getText());

            Compte c = compteDAO.getByNumero(numero);
            if (c != null) {
                double nouveauSolde = c.getSolde();
                if (type.equals("DEBIT")) {
                    nouveauSolde -= montant;
                } else {
                    nouveauSolde += montant;
                }
                compteDAO.updateSolde(numero, nouveauSolde);
                Transaction t = new Transaction(numero, type, montant, LocalDateTime.now());
                transactionDAO.ajouterTransaction(t);
                chargerTransactions(numero);
            } else {
                JOptionPane.showMessageDialog(this, "Compte introuvable.");
            }
        });

        JButton btnHistorique = new JButton("Afficher Historique");
        btnHistorique.addActionListener((ActionEvent e) -> {
            String numero = txtNumero.getText();
            chargerTransactions(numero);
        });

        add(btnHistorique, BorderLayout.SOUTH);
    }

    private void chargerTransactions(String numero) {
        model.setRowCount(0);
        List<Transaction> list = transactionDAO.getTransactionsParCompte(numero);
        for (Transaction t : list) {
            model.addRow(new Object[]{t.getId(), t.getType(), t.getMontant(), t.getDateOperation()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TransactionView().setVisible(true));
    }
}