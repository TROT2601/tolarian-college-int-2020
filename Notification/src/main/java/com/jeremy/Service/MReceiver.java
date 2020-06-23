package com.jeremy.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeremy.model.LoanMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MReceiver {

    private final SEService seService;

    public MReceiver(SEService seService){
        this.seService = seService;
    }

    @JmsListener(destination = "library.app.queue")
    public void loanMessageReceiver(String msg){
        log.info(msg);
        ObjectMapper om = new ObjectMapper();

        try {
            LoanMessage loanMessage = om.readValue(msg, LoanMessage.class);
            seService.sEmailLoan(loanMessage.getExternalMember().getEmailAddress());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
