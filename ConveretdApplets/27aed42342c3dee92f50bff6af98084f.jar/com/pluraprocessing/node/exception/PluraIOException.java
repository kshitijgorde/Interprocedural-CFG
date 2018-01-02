// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.exception;

public class PluraIOException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public PluraIOException() {
        super("Error communicating with Plura server.");
    }
}
