package io.github.tundeadetunji.recruiter_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/recruiter/messages")
@Tag(name = "Messaging", description = "RabbitMQ message log")
@Slf4j
public class MessageController {

    private final List<String> messages = new ArrayList<>();

    @RabbitListener(queues = "#{recruiterQueue.name}")
    public void handleMessage(String message) {
        messages.add(message);
        log.info("Received from queue: {}", message);
    }

    @Operation(summary = "Get all received RabbitMQ messages")
    @GetMapping
    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    @Operation(summary = "Clear all received messages (for testing)")
    @DeleteMapping
    public void clearMessages() {
        messages.clear();
    }
}
