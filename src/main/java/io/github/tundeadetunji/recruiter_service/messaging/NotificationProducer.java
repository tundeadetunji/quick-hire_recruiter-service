package io.github.tundeadetunji.recruiter_service.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tundeadetunji.recruiter_service.config.RabbitMqConfig;
import io.github.tundeadetunji.recruiter_service.shared.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "app.exchange";
    private static final String ROUTING_KEY = "recruiter.notify";
    private final ObjectMapper objectMapper;

    public void notifyAdmin(NotificationMessage message) {

        try {
            String json = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(EXCHANGE, "recruiter.notify", json); // keep for recruiter
            rabbitTemplate.convertAndSend(EXCHANGE, "admin.notify", json);     // send to admin
        } catch (JsonProcessingException e) {
            //in production, log this
        }
    }
}
