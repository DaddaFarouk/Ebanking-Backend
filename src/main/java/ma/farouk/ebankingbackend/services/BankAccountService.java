package ma.farouk.ebankingbackend.services;

import ma.farouk.ebankingbackend.entities.BankAccount;
import ma.farouk.ebankingbackend.entities.CurrentAccount;
import ma.farouk.ebankingbackend.entities.Customer;
import ma.farouk.ebankingbackend.entities.SavingAccount;
import ma.farouk.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.farouk.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.farouk.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<Customer> listCustomers();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BalanceNotSufficientException, BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDest, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
    List<BankAccount> bankAccountList();
}
