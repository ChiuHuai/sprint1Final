package com.example.Sprint1MGN.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CashiId implements Serializable {
    private String Id;
    private String accNo;
    private String ccy;
}
