package br.com.lab.impacta.account.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lab.impacta.account.application.AccountApplication;
import br.com.lab.impacta.account.application.dto.request.DebitAccountRequest;
import br.com.lab.impacta.account.application.dto.response.DebitAccountResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class DebitController {
    
    private final AccountApplication accountApplication;

    public ResponseEntity<DebitAccountResponse> debit(
        @PathVariable long accountId,
        @RequestBody DebitAccountRequest debitAccountRequest
    ){
        DebitAccountResponse debitAccountResponse = accountApplication.debit(accountId, debitAccountRequest);
        return ResponseEntity.ok(debitAccountResponse);
    }
}
