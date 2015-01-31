package net.jiaoqsh.backend.user.registration.web;


import net.jiaoqsh.backend.user.registration.config.MessagingNames;
import net.jiaoqsh.backend.user.registration.domain.RegisteredUser;
import net.jiaoqsh.backend.user.registration.repository.RegisteredUserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRegistrationController {
    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/rest/user", method = RequestMethod.POST)
    public void registerUser(@RequestBody RegisteredUser registeredUser){
        registeredUserRepository.save(registeredUser);
        rabbitTemplate.convertAndSend(MessagingNames.EXCHANGE_ANME, MessagingNames.ROUTING_KEY, registeredUser);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "duplicate email address")
    @ExceptionHandler(DuplicateKeyException.class)
    public void duplicateEmail(){
    }
}
