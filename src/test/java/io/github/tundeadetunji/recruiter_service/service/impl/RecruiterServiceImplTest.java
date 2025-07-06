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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}
