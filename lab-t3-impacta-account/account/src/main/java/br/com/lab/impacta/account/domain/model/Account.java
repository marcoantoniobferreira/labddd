package br.com.lab.impacta.account.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person customer;
    
    private Double balance;

    public boolean debit(Double valueOfDebit){
        if (this.getBalance() < valueOfDebit) {
            return false;
        }
        
        Double debitedAmount = this.getBalance() - valueOfDebit;

        this.setBalance(debitedAmount);

        return true;
    }
}
