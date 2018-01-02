// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.exception;

public class PluraInitializationException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public PluraInitializationException() {
        super("init(String, double, double, String, int) must be called on an IPluraConnector object and completed before any other IPluraConnector method may be invoked.");
    }
}
