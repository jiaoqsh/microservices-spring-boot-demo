package net.jiaoqsh.backend.mail.service;

import net.jiaoqsh.backend.mail.domain.RegisteredUser;
import net.jiaoqsh.backend.mail.utils.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static final String MAIL_SUBJECT = "Welcome test!";
    private static final String MAIL_CONTENT = "Thanks register!";

    private static Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private MailService mailService;

    @Value("${mail.from}")
    private String from;

    public void receiveMessage(byte[] message) {
        logger.info(String.format("Received message <%s>", new String(message)));
        //TODO mapper message to RegisteredUser Object
        RegisteredUser user = JsonMapper.nonDefaultMapper().fromJson(new String(message), RegisteredUser.class);
        //TODO send mail
        mailService.sendMail(user.getEmail(), from, MAIL_SUBJECT, MAIL_CONTENT);
    }
}
