// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.exception;

public class PluraIntervalException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public PluraIntervalException() {
        super("Not enough time has elapsed to perform this operation.");
    }
}
