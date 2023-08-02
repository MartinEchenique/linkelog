package com.echenique.linkelog.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

public abstract class LoggedUserHelper {
    public static int getLoggedId(){
        return ((UserSecurity) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())
                .getUserId();
    }
    public static boolean isLoggedId(int id){
        return id == getLoggedId();
    }
}
