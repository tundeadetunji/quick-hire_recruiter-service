package io.github.tundeadetunji.recruiter_service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE = "app.exchange";

    public static final String RECRUITER_QUEUE = "recruiter.notifications";
    public static final String RECRUITER_ROUTING_KEY = "recruiter.notify";

    public static final String ADMIN_ROUTING_KEY = "admin.notify";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue recruiterQueue() {
        return new Queue(RECRUITER_QUEUE);
    }

    @Bean
    public Binding recruiterBinding() {
        return BindingBuilder.bind(recruiterQueue()).to(topicExchange()).with(RECRUITER_ROUTING_KEY);
    }
}
