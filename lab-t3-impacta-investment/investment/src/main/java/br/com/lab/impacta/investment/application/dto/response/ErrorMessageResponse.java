package br.com.lab.impacta.investment.application.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageResponse {
    private Date timestamp;
    private String message;
    private String description;
}
