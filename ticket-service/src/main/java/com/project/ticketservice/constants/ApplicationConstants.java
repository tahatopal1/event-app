package com.project.ticketservice.constants;

import java.util.Set;

public class ApplicationConstants {

    public static final String EVENT_SERVICE_URL = "http://localhost:9081/api/v1/events/decrease/{id}?count={count}";
    public static final String AUTH_SERVICE_URL = "http://localhost:9080/login";
    public static final String AUTOMATION_KEY = "automationJWT";

}
