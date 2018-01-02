// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.exception;

public class PluraParameterException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public PluraParameterException() {
        super("Invalid parameter input. Please check acceptable parameter values.");
    }
}
