package br.com.lab.impacta.investment.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWithoutBalanceException;
import br.com.lab.impacta.investment.domain.exception.InvestmentAccountWithoutBalanceForProductPrivateInvestmentException;
import lombok.Data;

@Data
@Entity
public class Investment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long accountId;

    private Double value;

    @CreationTimestamp
    private Date creation;

    private boolean privateInvestment;

    public Investment(Long productId, Long accountId, Double value){
        this.productId = productId;
        this.accountId = accountId;
        this.value = value;
    }

    public void checkSufficientBalanceForInvestment(Double accountBalance){
        if (this.value > accountBalance) {
            throw new InvestmentAccountWithoutBalanceException();
        }
    }

    public void verifyProductPrivateOfDefaultForInvestment(Double accountBalance, Product product){
        if (!product.isPrivateInvestment()) {
            this.privateInvestment = false;
        } else if(product.isPrivateInvestment() && (accountBalance >= product.getMinimumValueForInvestment())){
            this.privateInvestment = true;
        } else {
            throw new InvestmentAccountWithoutBalanceForProductPrivateInvestmentException();
        }
    }
}
