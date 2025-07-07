package io.github.tundeadetunji.recruiter_service.service.impl;

import io.github.tundeadetunji.recruiter_service.domain.model.Recruiter;
import io.github.tundeadetunji.recruiter_service.dto.CreateRecruiterDto;
import io.github.tundeadetunji.recruiter_service.messaging.NotificationProducer;
import io.github.tundeadetunji.recruiter_service.shared.NotificationMessage;
import io.github.tundeadetunji.recruiter_service.strategy.RecruiterStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static io.github.tundeadetunji.recruiter_service.constants.ExceptionMessages.NOTIFICATION_SERVICE_DOWN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecruiterServiceImplTest {

    @Mock
    private RecruiterStrategy recruiterStrategy;

    @Mock
    private NotificationProducer notificationProducer;

    @InjectMocks
    private RecruiterServiceImpl recruiterService;

    @Test
    void registerRecruiter_shouldPersistRecruiterAndNotifyAdmin() {
        // Arrange
        CreateRecruiterDto dto = CreateRecruiterDto.builder()
                .firstName("Amy").lastName("Doe").email("amy@quickhire.com")
                .phones(List.of("+2348012345678")).department("Tech").build();

        Recruiter savedRecruiter = Recruiter.from(dto);
        savedRecruiter.setId(1L);

        when(recruiterStrategy.saveRecruiter(any(Recruiter.class))).thenReturn(savedRecruiter);

        // Act
        Recruiter result = recruiterService.registerRecruiter(dto);

        // Assert
        assertEquals("Amy", result.getFirstName());
        verify(recruiterStrategy).saveRecruiter(any(Recruiter.class));
        verify(notificationProducer).notifyAdmin(any(NotificationMessage.class));
    }

    @Test
    void registerRecruiter_shouldRollback_whenNotificationFails() {
        // Arrange
        CreateRecruiterDto dto = CreateRecruiterDto.builder()
                .firstName("Jill").lastName("Baker").email("jillb@quickhire.com")
                .phones(List.of("+2348012345678")).department("IT").build();

        Recruiter savedRecruiter = Recruiter.from(dto);
        savedRecruiter.setId(2L);

        when(recruiterStrategy.saveRecruiter(any())).thenReturn(savedRecruiter);
        doThrow(new RuntimeException(NOTIFICATION_SERVICE_DOWN)).when(notificationProducer).notifyAdmin(any());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> recruiterService.registerRecruiter(dto));

        verify(recruiterStrategy).saveRecruiter(any());

    }

}
