package com.email.com.email.service;


import org.springframework.stereotype.Service;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static java.lang.System.getProperties;


@Service
public class EmailService {

    public boolean sendEmail(String subject,String message,String to)
    {
        //rest of the code...

        boolean f=false;

        String from="prateekbanra@gmail.com";

        //variable for gmail
        String host="smtp.gmail.com";

        //get the system properties
        Properties properties= getProperties();
        System.out.println("PROPERTIES"+properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.host.port","465");//465
        properties.put("mail.smtp.host.ssl.enable","true");
        properties.put("mail.smtp.host.auth","true");

        //Step 1: to get the session object..
        Session session= Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("prateekbanra@gmail.com","***");
            }
        });

        session.setDebug(true);

        //Step 2:compose the message [text,multi media]
        MimeMessage m=new MimeMessage(session);

        try {

            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);

            //adding text to message
            m.setText(message);

            //send

            //Step 3:send the message using Transport class
            Transport.send(m);

            System.out.println("Sent success..............");
            f=true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
