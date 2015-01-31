package net.jiaoqsh.backend.user.registration;


import junit.framework.Assert;
import net.jiaoqsh.backend.user.registration.config.MessagingNames;
import net.jiaoqsh.backend.user.registration.domain.RegisteredUser;
import net.jiaoqsh.backend.user.registration.repository.RegisteredUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserRegistrationBackendApplication.class)
@WebAppConfiguration
public class UserRegistrationBackendApplicationTests {

	@Autowired
	private RegisteredUserRepository registeredUserRepository;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void contextLoads() {
	}

	private RegisteredUser saveUserToMongodb(){
		String email = "jiaoqsh@126.com";
		String password = "secret";
		RegisteredUser registeredUser = new RegisteredUser(email, password);

		return registeredUserRepository.save(registeredUser);
	}

	@Test
	public void registeredUserToMongodb() {

		RegisteredUser registeredUser = saveUserToMongodb();

		String email = "foo@bar.com";
		registeredUser = registeredUserRepository.findByEmail(email);

		Assert.assertEquals(email, registeredUser.getEmail());

	}

	@Test
	public void registeredUserToRabbitmq() {

		RegisteredUser registeredUser = saveUserToMongodb();

		rabbitTemplate.convertAndSend(MessagingNames.EXCHANGE_ANME, MessagingNames.ROUTING_KEY, registeredUser);
	}
}
