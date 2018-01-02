// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.exception;

public class PluraStateException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public PluraStateException() {
        super("IPluraConnector.stop() must be invoked before this method may be invoked.");
    }
}
