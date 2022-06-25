package br.com.lab.impacta.investment.integration.impl;

import org.springframework.stereotype.Component;

import br.com.lab.impacta.investment.infraestructure.http.AccountClient;
import br.com.lab.impacta.investment.integration.AccountIntegration;
import br.com.lab.impacta.investment.integration.dto.request.DebitAccountRequest;
import br.com.lab.impacta.investment.integration.valueObject.AccountBalanceVO;
import br.com.lab.impacta.investment.integration.valueObject.DebitAccountVO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountIntegrationImpl implements AccountIntegration {
    
    private final AccountClient accountClient;

    @Override    
    public AccountBalanceVO getAccountBalanceById(Long accountId) {
        AccountBalanceVO accountBalanceVO = accountClient.getAccountBalance(accountId); 
        return accountBalanceVO;
    }

    @Override
    public boolean debitAccount(long accountId, double valueOfDebit) {
        DebitAccountVO debitAccountVO = accountClient.debit(accountId, new DebitAccountRequest(valueOfDebit));
        return debitAccountVO.isDebited();
    }
    
}
