package ma.farouk.ebankingbackend.dtos;

import lombok.Data;
import ma.farouk.ebankingbackend.enums.AccountStatus;

import java.util.Date;


@Data
public class CurrentAccountDTO extends  BankAccountDTO{
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}
