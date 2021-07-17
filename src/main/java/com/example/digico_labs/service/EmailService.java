package com.example.digico_labs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Service
public class EmailService {

    @Value("${mail.host.address}")
    private String host;
    @Value("${mail.host.port}")
    private int port;
    @Value("${mail.host.username}")
    private String username;
    @Value("${mail.from.address}")
    private String fromAddress;
    @Value("${mail.host.password}")
    private String password;

    private JavaMailSenderImpl sender;



    @PostConstruct
    void initSender() {
        log.info("Configuring email client");
        sender = new JavaMailSenderImpl();
        sender.setHost(this.host);
        sender.setPort(this.port);
        sender.setUsername(this.username);
        sender.setPassword(this.password);
        sender.setProtocol("smtp");
        sender.setDefaultEncoding("UTF-8");

        log.info("Email sender configuration complete. Configuring mail properties...");
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.ssl.trust", this.host);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.auth", true);

        sender.setJavaMailProperties(javaMailProperties);

        log.info("Mail property configuration completed.");
    }

    public boolean sendMail(String toEmail, String subject, String message) throws Exception {
        try {
            MimeMessage mailMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, false);

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.setFrom(fromAddress);

            sender.send(mailMessage);
            log.info("Email is sent to: [{}]: ", toEmail);
            return true;
        } catch (Exception e) {
            log.error("Failed email sending. ", e.getCause());
            throw new Exception();
        }
    }
}
