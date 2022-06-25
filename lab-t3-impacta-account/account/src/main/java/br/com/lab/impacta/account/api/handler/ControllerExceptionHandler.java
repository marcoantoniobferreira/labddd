package br.com.lab.impacta.account.api.handler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.lab.impacta.account.application.dto.response.ErrorMessageResponse;
import br.com.lab.impacta.account.domain.exception.AccountNotFoundException;
import br.com.lab.impacta.account.domain.exception.AccountWithoutBalanceException;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String MESSAGE_INTERNAL_SERVER_ERROR = "Erro desconhecido no servidor";
    private static final String DESCRIPTION_INTERNAL_SERVER_ERROR = "Não foi possível processar a requisição, procure o administrador do sistema";

    @Value("${lab.account.exceptions.account-without-balance-description}")
    private String descriptionExceptionWithoutBalanceMessage;

    @Value("${lab.account.exceptions.account-without-balance-message}")
    private String descriptionExceptionWithoutBalanceDescription;

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> accountNotFoundException(AccountNotFoundException exception){
        return getErrorResponse(exception.getMessage(), exception.getDescription(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> withoutBalanceException(){
        return getErrorResponse(descriptionExceptionWithoutBalanceDescription, descriptionExceptionWithoutBalanceMessage, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorMessageResponse> errorGeneral(RuntimeException exception){
        return getErrorResponse(MESSAGE_INTERNAL_SERVER_ERROR, DESCRIPTION_INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorResponse(String message, 
                                                                 String description, 
                                                                 HttpStatus httpStatus){
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
            new Date(),
            message,
            description
        );

        return new ResponseEntity(errorMessageResponse, httpStatus);
    }
    
}
