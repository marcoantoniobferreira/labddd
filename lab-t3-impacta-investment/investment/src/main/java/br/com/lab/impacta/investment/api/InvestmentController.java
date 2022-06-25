package br.com.lab.impacta.investment.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lab.impacta.investment.application.InvestmentApplication;
import br.com.lab.impacta.investment.application.dto.request.InvestmentRequest;
import br.com.lab.impacta.investment.application.dto.response.InvestmentResponse;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class InvestmentController {
    
    private final InvestmentApplication investmentApplication;

    public ResponseEntity<InvestmentResponse> invest(
        @PathVariable long accountId,
        @RequestBody InvestmentRequest investmentRequest
    ){
        InvestmentResponse investmentResponse = investmentApplication.invest(accountId, investmentRequest);
        return ResponseEntity.ok(investmentResponse);
    }
}
