package io.github.tundeadetunji.recruiter_service.service;

import io.github.tundeadetunji.recruiter_service.dto.CreateRecruiterDto;
import io.github.tundeadetunji.recruiter_service.domain.model.Recruiter;
import io.github.tundeadetunji.recruiter_service.service.impl.RecruiterServiceImpl;
import io.github.tundeadetunji.recruiter_service.shared.NotificationMessage;
import io.github.tundeadetunji.recruiter_service.messaging.NotificationProducer;
import io.github.tundeadetunji.recruiter_service.strategy.RecruiterStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static io.github.tundeadetunji.recruiter_service.constants.InlineStrings.NEW_RECRUITER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecruiterServiceTest {

    private RecruiterStrategy recruiterStrategy;
    private NotificationProducer notificationProducer;
    private RecruiterService recruiterService;

    @BeforeEach
    void setUp() {
        recruiterStrategy = mock(RecruiterStrategy.class);
        notificationProducer = mock(NotificationProducer.class);
        recruiterService = new RecruiterServiceImpl(recruiterStrategy, notificationProducer);
    }

    @Test
    void shouldRegisterRecruiterAndNotifyAdmin() {
        // given
        CreateRecruiterDto dto = CreateRecruiterDto.builder()
                .firstName("Ada")
                .middleName("Ifeoma")
                .lastName("Eze")
                .email("ada.eze@example.com")
                .phones(java.util.List.of("+2348012345678"))
                .department("HR")
                .build();

        Recruiter expected = Recruiter.from(dto);
        when(recruiterStrategy.saveRecruiter(any(Recruiter.class))).thenReturn(expected);

        // when
        Recruiter result = recruiterService.registerRecruiter(dto);

        // then
        assertEquals(expected, result);

        ArgumentCaptor<NotificationMessage> captor = ArgumentCaptor.forClass(NotificationMessage.class);
        verify(notificationProducer, times(1)).notifyAdmin(captor.capture());

        NotificationMessage sentMessage = captor.getValue();
        assertEquals(NEW_RECRUITER, sentMessage.getSubject());
        assertEquals("Recruiter Ada Eze just got created.", sentMessage.getBody());
    }
}
