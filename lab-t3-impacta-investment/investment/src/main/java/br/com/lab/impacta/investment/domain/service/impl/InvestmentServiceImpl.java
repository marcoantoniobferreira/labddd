package br.com.lab.impacta.investment.domain.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lab.impacta.investment.domain.exception.InvestimentAccountIsNotDebitedException;
import br.com.lab.impacta.investment.domain.exception.InvestmentProductNotFoundException;
import br.com.lab.impacta.investment.domain.model.Investment;
import br.com.lab.impacta.investment.domain.model.Product;
import br.com.lab.impacta.investment.domain.service.InvestmentService;
import br.com.lab.impacta.investment.infraestructure.repository.InvestmentRepository;
import br.com.lab.impacta.investment.infraestructure.repository.ProductRepository;
import br.com.lab.impacta.investment.integration.AccountIntegration;
import br.com.lab.impacta.investment.integration.valueObject.AccountBalanceVO;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    private ProductRepository productRepository;

    private InvestmentRepository investmentRepository;

    private AccountIntegration accountIntegration;

    @Override
    public Investment invest(Long productId, Long accountId, Double valueOfInvestment) {
        Optional<Product> product = productRepository.findById(productId);
        
        if (product.isEmpty()) {
            throw new InvestmentProductNotFoundException();
        }

        Investment investment = new Investment(productId, accountId, valueOfInvestment);
        
        AccountBalanceVO accountBalanceVO = accountIntegration.getAccountBalanceById(accountId);

        investment.checkSufficientBalanceForInvestment(accountBalanceVO.getBalance());
        
        investment.verifyProductPrivateOfDefaultForInvestment(accountBalanceVO.getBalance(), product.get());
       
        boolean isDebited = accountIntegration.debitAccount(accountId, investment.getValue()); 
        
        if (!isDebited) {
            throw new InvestimentAccountIsNotDebitedException();
        }

        investmentRepository.save(investment);
        
        return investment;
    }
    
}
