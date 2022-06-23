package ma.farouk.ebankingbackend.services;

import emsi.ma.ebankingbackend.dtos.*;
import emsi.ma.ebankingbackend.entities.BankAccount;
import emsi.ma.ebankingbackend.entities.CurrentAccount;
import emsi.ma.ebankingbackend.entities.Customer;
import emsi.ma.ebankingbackend.entities.SavingAccount;
import emsi.ma.ebankingbackend.exceptions.BalanceNotSufficientException;
import emsi.ma.ebankingbackend.exceptions.BankAccountNotFoundException;
import emsi.ma.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.farouk.ebankingbackend.dtos.BankAccountDTO;

import java.util.List;

public interface BankAccountService {
     CustomerDTO saveCustomer(CustomerDTO customerDTO);
     CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
     SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
     List<CustomerDTO> listCustomer();
     BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
     void debit(String accountId , double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
     void credit(String accountId , double amount,String description) throws BankAccountNotFoundException;
     void transfer(String accountIdSource,String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

     List<BankAccountDTO> bankAccountList();

     CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

     CustomerDTO updateCustomer(CustomerDTO customerDTO);

     void deleteCustomer(Long customerId);

     List<AccountOperationDTO> accountHistory(String accountId);

     AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

     List<CustomerDTO> searchCustomers(String keyword);

     List<BankAccountDTO> getCustomerAccounts(Long customerId)throws CustomerNotFoundException;


}
