package ma.farouk.ebankingbackend.dtos;

import lombok.Data;

@Data
public class TransferRequestDTO {
    private String accountSource;
    private String accountDest;
    private double amount;
    private String description;
}
