package br.com.lab.impacta.account.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lab.impacta.account.AccountApplication;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class BalanceController {
    
    private final AccountApplication accountApplication;
}
