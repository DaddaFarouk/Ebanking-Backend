package ma.farouk.ebankingbackend.dtos;


import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDTO {
    private List<AccountOperationDTO> accountOperationDTOS;
    private String accountId;
    private double balance;
    private int currentPage;
    private int totalPages;
    private int pageSize;
}
