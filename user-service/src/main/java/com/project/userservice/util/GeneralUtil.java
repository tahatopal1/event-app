package com.project.userservice.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class GeneralUtil {

    public static String extractUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
