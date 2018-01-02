// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

public interface ErrorReporter
{
    void setErrorMessage(final Controller p0, final String p1);
    
    void clearErrorMessage();
    
    String getErrorMessage();
}
