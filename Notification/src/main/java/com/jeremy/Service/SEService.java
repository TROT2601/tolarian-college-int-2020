package com.jeremy.Service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j//lombok
@Service //servicio
public class SEService {

    //initializer and constructor
    private final JavaMailSender jms;
    private final String emailF;
    private final String emailS;
    private final String emailT;

    public SEService(JavaMailSender jms,
                     @Value("${app.notification.email.to}") String emailF,
                     @Value("${app.notification.email.subject}") String emailS,
                     @Value("${app.notification.email.text}") String emailT){
        this.jms = jms;
        this.emailF = emailF;
        this.emailS = emailS;
        this.emailT = emailT;
    }

    public void sEmailLoan(String emailTo){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(emailF);
        msg.setTo(emailTo);
        msg.setSubject(emailS);
        msg.setText(emailT);
        log.info(msg.toString());
        jms.send(msg);
    }

}
