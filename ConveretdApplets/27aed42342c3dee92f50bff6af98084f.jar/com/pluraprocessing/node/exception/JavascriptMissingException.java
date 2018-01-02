// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.exception;

public class JavascriptMissingException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public JavascriptMissingException() {
        super("Required Javascript does not exist on page.");
    }
}
