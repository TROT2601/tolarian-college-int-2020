package com.jeremy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeremy.model.BorrowedBook;
import com.jeremy.model.LoanMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LoanService {

    private final RestTemplate restTemplate;
    private final Sender sender;

    public LoanService(RestTemplate restTemplate, Sender sender){
        this.restTemplate = restTemplate;
        this.sender = sender;
    }

    public boolean performLoan(LoanMessage loanMessage) throws JsonProcessingException {
        List<BorrowedBook> borrowedBooks = loanMessage.getBorrowedBooks();
        boolean EVI = verifyAndUpdate(borrowedBooks);

        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(loanMessage);

        sender.SMessage("library.app.queue",msg);

        return EVI;
    }

    private boolean verifyAndUpdate(List<BorrowedBook>borrowedBooks) {
        for (BorrowedBook borrowedBook:borrowedBooks){
            HttpEntity<BorrowedBook> request = new HttpEntity<>(borrowedBook);
            ResponseEntity<BorrowedBook> bookResponseEntity = restTemplate
                    .exchange("http://localhost:8082/lib/books/verifyAndUpdate", HttpMethod.POST, request, BorrowedBook.class);
            if(bookResponseEntity.getStatusCode().is2xxSuccessful()){
                BorrowedBook bookResponse = bookResponseEntity.getBody();
                System.out.println("bookResponse = " + bookResponse);
            }else{
                return false;
            }
        }
        return true;
    }
}
