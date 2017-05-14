package com.wordgen.appobj;

import com.wordgen.model.gen.Action;

public class ActionAO extends Action {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String moduleName;
    
    private boolean authed = false;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isAuthed() {
        return authed;
    }

    public void setAuthed(boolean authed) {
        this.authed = authed;
    }

    
    
}