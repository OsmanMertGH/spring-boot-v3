package com.cydeo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    
    private String model;
    private String make;
    private int year;
    private BigDecimal km;
    private boolean guarantee;
}
