package br.com.lab.impacta.account.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.lab.impacta.account.domain.exception.AccountNotFoundException;
import br.com.lab.impacta.account.domain.exception.AccountWithoutBalanceException;
import br.com.lab.impacta.account.domain.model.Account;
import br.com.lab.impacta.account.domain.service.AccountService;
import br.com.lab.impacta.account.infraestructure.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Value("${lab.account.exceptions.account-dont-exists-message}")
    private String messageExceptionNotFound;

    @Value("${lab.account.exceptions.account-dont-exists-description}")
    private String descriptionExceptionNotFound;

    @Value("${lab.account.exceptions.account-without-balance-description}")
    private String descriptionExceptionWithoutBalanceMessage;

    @Value("${lab.account.exceptions.account-without-balance-message}")
    private String descriptionExceptionWithoutBalanceDescription;

    @Override
    public Account find(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            throw new AccountNotFoundException(messageExceptionNotFound, descriptionExceptionNotFound);
        }
        return account.get();
    }

    @Override
    public void debit(Long accountId, Double valueOfDebit) {
        Account account = this.find(accountId);
        // boolean debited = account.debit(valueOfDebit);
        // if (!debited) {
        //     throw new AccountWithoutBalanceException(descriptionExceptionWithoutBalanceMessage, descriptionExceptionWithoutBalanceDescription);
        // }
        account.debit(valueOfDebit);

        accountRepository.save(account);
    }
    
}
