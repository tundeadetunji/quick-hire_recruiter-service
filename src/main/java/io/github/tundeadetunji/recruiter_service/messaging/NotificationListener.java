package io.github.tundeadetunji.recruiter_service.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tundeadetunji.recruiter_service.shared.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    //this is only for local dev/testing

    /*@RabbitListener(queues = "#{recruiterQueue.name}")
    public void receive(String notificationMessage) {
        //in production, send to recruiter/stakeholders' email

        try {
            NotificationMessage message = NotificationMessage.fromJson(notificationMessage);
            log.info("Notification received in recruiter-service");
            log.info("Subject: {}", message.getSubject());
            log.info("Body: {}", message.getBody());
        } catch (Exception e) {
            log.error("Failed to parse notification message: {}", e.getMessage());
        }
    }*/
}
