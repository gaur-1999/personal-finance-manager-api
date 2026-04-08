package com.prince.Personal.Finance.Manager.API.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequest {
    private String title;
    private Double amount;
    private String type;
    private String category;
    private LocalDate date;
}
