package com.bestsellers.assignment.gamersdirectory.exception;

import com.bestsellers.assignment.gamersdirectory.model.ErrorResponse;
import com.bestsellers.assignment.gamersdirectory.model.ValidatorErrorDTO;
import com.bestsellers.assignment.gamersdirectory.util.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageHelper messageHelper;

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(final BaseException exception) {
        log.error("Exception occurred: ", exception);

        return ResponseEntity.status(exception.getHttpStatus()).body(prepareErrorResponse(exception));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(final Exception exception) {
        log.error("Unexpected exception occurred: ", exception);

        final SystemException systemException = new SystemException();
        final ErrorResponse errorResponse = prepareErrorResponse(systemException);

        return ResponseEntity.status(systemException.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(final MethodArgumentNotValidException exception) {
        log.error("Validation exception occurred: ", exception);

        final BadRequestException badRequestException = new BadRequestException();
        final ErrorResponse errorResponse = prepareErrorResponse(badRequestException);

//        return ResponseEntity.status(badRequestException.getHttpStatus()).body(errorResponse);

        List<ValidatorErrorDTO> validatorErrorDTOList = new ArrayList<>();
        exception.getFieldErrors().forEach(fieldError -> {
            ValidatorErrorDTO validatorErrorDTO = new ValidatorErrorDTO();
            validatorErrorDTO.setField(fieldError.getField());
            validatorErrorDTO.setMessage(fieldError.getDefaultMessage());
            validatorErrorDTOList.add(validatorErrorDTO);
        });
        errorResponse.setValidatorErrorDTOList(validatorErrorDTOList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

    private ErrorResponse prepareErrorResponse(final BaseException exception) {
        final ErrorResponse bfsErrorResponse = new ErrorResponse();

        bfsErrorResponse.setCode(exception.getCode());
        bfsErrorResponse.setTitle(messageHelper.getMessage(exception.getTitleKey()));
        String message = messageHelper.getMessage(exception.getMessageKey());
        bfsErrorResponse.setMessage(message);

        return bfsErrorResponse;
    }
}
