package br.com.lab.impacta.investment.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lab.impacta.investment.domain.model.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long>{
    
}
