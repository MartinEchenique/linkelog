package com.echenique.linkelog.security;

import org.springframework.security.core.context.SecurityContextHolder;

public abstract class LoggedUserHelper {
    public static int getLoggedId(){
        return ((UserSecurity) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())
                .getUserId();
    }
}
