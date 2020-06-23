package com.jeremy.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jeremy.model.LoanMessage;
import com.jeremy.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lib")
public class LoanResource {

    private final LoanService loanService;

    public LoanResource(LoanService loanService){
        this.loanService = loanService;
    }

    @PostMapping("/loans")
    public ResponseEntity performLoan(@RequestBody LoanMessage loanMessage) throws JsonProcessingException{
        boolean success = loanService.performLoan(loanMessage);
        if(!success){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(loanMessage, HttpStatus.CREATED);
    }


}
