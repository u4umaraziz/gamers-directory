package com.bestsellers.assignment.gamersdirectory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

    private String code;
    private String title;
    private String message;

    List<ValidatorErrorDTO> validatorErrorDTOList;
}
