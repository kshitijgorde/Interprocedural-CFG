// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

import java.io.Serializable;

public class GenericResponse implements Serializable
{
    public boolean successful;
    public Object handle;
    public String message;
    public int errorCode;
    
    public GenericResponse() {
        this.successful = false;
        this.handle = null;
        this.message = null;
        this.errorCode = 0;
    }
    
    public String toString() {
        return "successful=" + this.successful + " message=" + this.message + " errorCode=" + this.errorCode + " handle=" + this.handle;
    }
}
