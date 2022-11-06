package com.bestsellers.assignment.gamersdirectory.model;

import lombok.Data;

@Data
public class ValidatorErrorDTO {
    private String field;
    private String message;
}
