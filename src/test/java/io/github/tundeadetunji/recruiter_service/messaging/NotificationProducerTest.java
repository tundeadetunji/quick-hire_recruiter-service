package io.github.tundeadetunji.recruiter_service.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tundeadetunji.recruiter_service.shared.NotificationMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificationProducerTest {

    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;
    private NotificationProducer notificationProducer;

    @BeforeEach
    void setUp() {
        rabbitTemplate = mock(RabbitTemplate.class);
        objectMapper = new ObjectMapper();
        notificationProducer = new NotificationProducer(rabbitTemplate, objectMapper);
    }

    @Test
    void shouldSendMessageToRabbitMQ() throws JsonProcessingException {
        // given
        NotificationMessage message = NotificationMessage.builder()
                .subject("To Admin")
                .body("Admin needs this.")
                .build();

        // when
        notificationProducer.notifyAdmin(message);

        // then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(rabbitTemplate, times(1)).convertAndSend(eq("app.exchange"), eq("recruiter.notify"), captor.capture());
        verify(rabbitTemplate, times(1)).convertAndSend(eq("app.exchange"), eq("admin.notify"), anyString());

        String sentJson = captor.getValue();
        NotificationMessage sent = objectMapper.readValue(sentJson, NotificationMessage.class);
        assertEquals("To Admin", sent.getSubject());
        assertEquals("Admin needs this.", sent.getBody());
    }
}
