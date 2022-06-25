package br.com.lab.impacta.investment.application.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvestmentResponse {
    
    private Long id;

    private Double value;

    private Date creation;
}
