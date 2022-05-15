package ma.farouk.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.farouk.ebankingbackend.enums.OperationType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private BankAccount bankAccount;
}