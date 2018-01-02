// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.exception;

public class PluraLoadException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public PluraLoadException(final Throwable e) {
        super("Error loading Plura JAR and/or editing classpath.", e);
    }
}
