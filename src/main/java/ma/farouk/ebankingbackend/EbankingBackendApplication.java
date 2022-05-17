package ma.farouk.ebankingbackend;

import ma.farouk.ebankingbackend.dtos.BankAccountDTO;
import ma.farouk.ebankingbackend.dtos.CurrentAccountDTO;
import ma.farouk.ebankingbackend.dtos.CustomerDTO;
import ma.farouk.ebankingbackend.dtos.SavingAccountDTO;
import ma.farouk.ebankingbackend.entities.*;
import ma.farouk.ebankingbackend.enums.AccountStatus;
import ma.farouk.ebankingbackend.enums.OperationType;
import ma.farouk.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.farouk.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.farouk.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.farouk.ebankingbackend.repositories.AccountOperationRepository;
import ma.farouk.ebankingbackend.repositories.BankAccountRepository;
import ma.farouk.ebankingbackend.repositories.CustomerRepository;
import ma.farouk.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Hassan","Imane","Mohamed").forEach(name->{
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setName(name);
                customerDTO.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customerDTO);
            });
            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*9000,9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*12000,5.5, customer.getId());
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount:bankAccounts){
                for (int i = 0; i < 10; i++) {
                    String accountId;
                    if (bankAccount instanceof SavingAccountDTO){
                        accountId = ((SavingAccountDTO) bankAccount).getId();
                    }else{
                        accountId= ((CurrentAccountDTO) bankAccount).getId();
                    }
                    try {
                        bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
                        bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
                    } catch (BalanceNotSufficientException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        };
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Hassan","Yassine","Aicha").forEach(
                    name->{
                        Customer customer = new Customer();
                        customer.setName(name);
                        customer.setEmail(name+"@gmail.com");
                        customerRepository.save(customer);
                    });
            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*9000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*9000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(customer);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(bankAccount -> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*12000);
                    accountOperation.setType(Math.random()>0.5?
                            OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(bankAccount);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }
}
