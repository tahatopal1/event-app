package com.project.ticketservice.client;

import com.project.ticketservice.util.GeneralUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

import static com.project.ticketservice.constants.ApplicationConstants.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventClient {

    private final RestTemplate restTemplate;

    public void decreaseAmount(Long eventId, Integer count) {
        String automationJWT = GeneralUtil.getAttributeFromRequest(AUTOMATION_KEY);

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(eventId));
        params.put("paramValue", String.valueOf(count));

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(automationJWT);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(EVENT_SERVICE_URL, HttpMethod.PUT, entity, Void.class, eventId, count);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error: decreasing is not possible");
        }
    }


}
