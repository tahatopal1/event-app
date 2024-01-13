package com.project.userservice.constants;

import java.util.Set;

public class ApplicationConstants {

    public static final String LOGIN = "/login";
    public static final Set<String> PERMITTED_ENDPOINTS = Set.of(LOGIN, "/signup");

}
