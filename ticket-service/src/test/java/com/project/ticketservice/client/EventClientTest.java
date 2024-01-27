package com.project.ticketservice.client;

import com.project.ticketservice.util.GeneralUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static com.project.ticketservice.constants.ApplicationConstants.AUTOMATION_KEY;
import static com.project.ticketservice.constants.ApplicationConstants.EVENT_SERVICE_URL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class EventClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private EventClient eventClient;

    @Test
    void decreaseAmount_ShouldCallRestTemplate() {
        try (MockedStatic<GeneralUtil> mocked = Mockito.mockStatic(GeneralUtil.class)) {
            mocked.when(() -> GeneralUtil.getAttributeFromRequest(AUTOMATION_KEY)).thenReturn("testToken");

            Long eventId = 1L;
            Integer count = 10;

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth("testToken");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Void> mockResponse = new ResponseEntity<>(HttpStatus.OK);

            when(restTemplate.exchange(
                    eq(EVENT_SERVICE_URL),
                    eq(HttpMethod.PUT),
                    any(HttpEntity.class),
                    eq(Void.class),
                    eq(eventId),
                    eq(count))
            ).thenReturn(mockResponse);

            eventClient.decreaseAmount(eventId, count);

            verify(restTemplate, times(1)).exchange(
                    eq(EVENT_SERVICE_URL),
                    eq(HttpMethod.PUT),
                    any(HttpEntity.class),
                    eq(Void.class),
                    eq(eventId),
                    eq(count)
            );
        }
    }
}
