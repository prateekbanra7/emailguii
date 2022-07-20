package com.email.com.email.controller;


import com.email.com.email.model.EmailRequest;
import com.email.com.email.model.EmailResponse;
import com.email.com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome()
    {
        return "hello this is my email api";
    }

    //api to send email

    @RequestMapping(value = "/sendemail",method = RequestMethod.POST )
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request)
    {
        System.out.println(request);
        boolean result=this.emailService.sendEmail(request.getSubject(),request.getMessage(),request.getTo());
        if(result)
        {
            return ResponseEntity.ok(new EmailResponse("Email is sent successfully..."));
        } else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent"));
        }

        //return ResponseEntity.ok("Email is sent successfully...");
    }
}
