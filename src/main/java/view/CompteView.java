package view;

import model.Compte;
import dao.CompteDAOImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CompteView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtNumero, txtNom, txtSolde;
    private CompteDAOImpl dao;

    public CompteView() {
        dao = new CompteDAOImpl();

        setTitle("Gestion des Comptes Bancaires");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        
        model = new DefaultTableModel(new Object[]{"Numéro", "Nom", "Solde"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel Formulaire
        JPanel formPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        txtNumero = new JTextField();
        txtNom = new JTextField();
        txtSolde = new JTextField();

        formPanel.add(new JLabel("Numéro:"));
        formPanel.add(txtNumero);
        formPanel.add(new JLabel("Nom Client:"));
        formPanel.add(txtNom);
        formPanel.add(new JLabel("Solde:"));
        formPanel.add(txtSolde);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnSupprimer = new JButton("Supprimer");

        formPanel.add(btnAjouter);
        formPanel.add(btnSupprimer);

        add(formPanel, BorderLayout.SOUTH);

        // Actions
        btnAjouter.addActionListener((ActionEvent e) -> {
            String numero = txtNumero.getText();
            String nom = txtNom.getText();
            double solde = Double.parseDouble(txtSolde.getText());

            Compte c = new Compte(numero, nom, solde);
            dao.ajouterCompte(c);
            chargerComptes();
        });

        btnSupprimer.addActionListener((ActionEvent e) -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String numero = (String) model.getValueAt(row, 0);
                dao.supprimerCompte(numero);
                chargerComptes();
            }
        });

        chargerComptes();
    }

    private void chargerComptes() {
        model.setRowCount(0);
        List<Compte> comptes = dao.getAll();
        for (Compte c : comptes) {
            model.addRow(new Object[]{c.getNumero(), c.getNomClient(), c.getSolde()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CompteView().setVisible(true));
    }
}