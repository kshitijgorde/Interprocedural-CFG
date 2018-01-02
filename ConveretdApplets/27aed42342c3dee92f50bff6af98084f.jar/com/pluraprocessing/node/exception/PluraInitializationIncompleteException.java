// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.exception;

public class PluraInitializationIncompleteException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public PluraInitializationIncompleteException() {
        super("Plura has not completed initialization. Try this operation again in a few moments.");
    }
}
