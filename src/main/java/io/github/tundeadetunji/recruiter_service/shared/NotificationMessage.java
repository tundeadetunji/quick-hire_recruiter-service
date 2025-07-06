package io.github.tundeadetunji.recruiter_service.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.github.tundeadetunji.recruiter_service.constants.ExceptionMessages.NOTIFICATION_MESSAGE_PARSE_FAILURE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
    private String subject;
    private String body;

    public static NotificationMessage fromJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, NotificationMessage.class);
        } catch (Exception e) {
            throw new RuntimeException(NOTIFICATION_MESSAGE_PARSE_FAILURE, e);
        }
    }

}
