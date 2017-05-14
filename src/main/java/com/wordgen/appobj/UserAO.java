package com.wordgen.appobj;

import com.wordgen.model.gen.User;

public class UserAO extends User {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}