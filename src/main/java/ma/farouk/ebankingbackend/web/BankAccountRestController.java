package ma.farouk.ebankingbackend.web;

import ma.farouk.ebankingbackend.services.BankAccountService;
import ma.farouk.ebankingbackend.services.BankAccountServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountRestController {
    private BankAccountServiceImpl bankAccountService;

    public BankAccountRestController(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
}
