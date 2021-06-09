package com.lembrete.exceptions.handler;

import com.lembrete.exceptions.RegistroNaoEncotradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({RegistroNaoEncotradoException.class})
    public ResponseEntity<?> handleRegistroNaoEncotradoException(RegistroNaoEncotradoException ex) {

        ProblemDetail problemDetail = createProblemDetail(ProblemDetailEnum.REGISTRO_NAO_LOCALIZADO);
        problemDetail.getDetail().add(ex.getMessage());
        return createResponseEntity(problemDetail);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemDetail problemDetail = createProblemDetail(ProblemDetailEnum.INFORMACAO_INVALIDA);
        //ex.getBindingResult().getAllErrors().forEach(e -> problemDetail.getDetail().add(e.getDefaultMessage()));
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            String error = messageSource.getMessage( fieldError , LocaleContextHolder.getLocale());
            System.out.println("ERROR: "+error);
        }

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> problemDetail.getDetail().add(messageSource.getMessage( fieldError , LocaleContextHolder.getLocale())));
        return createResponseEntity(problemDetail, ex, headers, request);

    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ProblemDetail problemDetail = createProblemDetail(ProblemDetailEnum.INFORMACAO_INVALIDA);
        problemDetail.getDetail().add(ex.getMessage());
        return createResponseEntity(problemDetail);
    }

    private ProblemDetail createProblemDetail(ProblemDetailEnum problemDetailEnum){
        ProblemDetail problemDetail = new ProblemDetail();
        problemDetail.setTitle(problemDetailEnum.title);
        problemDetail.setType(problemDetailEnum.type);
        problemDetail.setStatus(problemDetailEnum.status.value());
        return problemDetail;
    }

    private ResponseEntity<?> createResponseEntity(ProblemDetail detail){
       return ResponseEntity.status(detail.getStatus()).contentType(MediaType.APPLICATION_PROBLEM_JSON).body(detail);
    }

    private ResponseEntity<Object> createResponseEntity(ProblemDetail detail, MethodArgumentNotValidException ex, HttpHeaders headers,WebRequest request){
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return handleExceptionInternal(ex, detail, headers, HttpStatus.valueOf(detail.getStatus()), request);
    }

}