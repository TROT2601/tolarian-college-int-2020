package com.jeremy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    Logger log = LoggerFactory.getLogger(Sender.class);

    private final JmsTemplate jms;

    public Sender(JmsTemplate jms){
        this.jms = jms;
    }

    public void SMessage(String queue, String msg){
        log.info("send message: " + msg,"to:" + queue);
        jms.convertAndSend(queue,msg);
    }
}
