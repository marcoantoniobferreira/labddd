package br.com.lab.impacta.account.domain.service;

import br.com.lab.impacta.account.domain.model.Account;

/**
 * A classe de serviço é o protetor do domínio
 * Não traga DTOs para dentro do domínio!
 */
public interface AccountService {
    
    Account find(Long accountId);

    void debit(Long accountId, Double valueOfDebit);
}
