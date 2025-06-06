package dao;

import model.Transaction;
import java.util.List;

public interface TransactionDAO {
    void ajouterTransaction(Transaction t);
    List<Transaction> getTransactionsParCompte(String numeroCompte);
}