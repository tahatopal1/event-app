package com.project.ticketservice.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

import static com.project.ticketservice.constants.SecurityConstants.CLAIM_ID;

public class GeneralUtil {

    public static Long getUserIdFromRequest() {
        HttpServletRequest request = getRequest();
        String idStr = String.valueOf(request.getAttribute(CLAIM_ID));
        return Long.valueOf(idStr);
    }

    public static String getAttributeFromRequest(String key) {
        HttpServletRequest request = getRequest();
        Object attribute = request.getAttribute(key);
        if (Objects.isNull(attribute)) {
            throw new RuntimeException("There's no attribute named: " + key);
        }
        return String.valueOf(attribute);
    }

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}
