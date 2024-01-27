package com.project.ticketservice.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.project.ticketservice.constants.ApplicationConstants.AUTH_SERVICE_URL;
import static com.project.ticketservice.constants.ApplicationConstants.AUTOMATION_KEY;

@Slf4j
public class AutomationFilter extends OncePerRequestFilter {

    private final RestTemplate restTemplate;

    @Value("${service.auth.username}")
    private String username;

    @Value("${service.auth.password}")
    private String password;

    public AutomationFilter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String automationJWT = this.authenticate();
        request.setAttribute(AUTOMATION_KEY, automationJWT);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getMethod().equals("POST");
    }

    private String authenticate() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AUTH_SERVICE_URL);
        HttpHeaders headers = createBasicAuthHeaders(username, password);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                requestEntity,
                String.class);
        String jwt = responseEntity.getHeaders().getFirst("Authorization");
        if (Strings.isBlank(jwt)) {
            log.error("No JWT Token found in response headers");
        }
        return jwt;
    }

    private HttpHeaders createBasicAuthHeaders(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }
}
