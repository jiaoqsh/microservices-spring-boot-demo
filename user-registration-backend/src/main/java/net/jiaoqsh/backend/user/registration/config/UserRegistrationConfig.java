package net.jiaoqsh.backend.user.registration.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRegistrationConfig {

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        template.setMessageConverter(jsonConverter);
        return template;
    }

    @Bean
    Queue userRegistrationsQueue(){
        return new Queue(MessagingNames.QUEUE_ANME, false);
    }

    @Bean
    TopicExchange userRegistrationsExchange(){
        return new TopicExchange(MessagingNames.EXCHANGE_ANME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(MessagingNames.QUEUE_ANME);
    }
}
