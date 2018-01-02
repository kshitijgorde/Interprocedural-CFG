// 
// Decompiled by Procyon v0.5.30
// 

package com.eaio.nativecall;

public interface Verifier
{
    String getDefaultModule();
    
    boolean supports() throws SecurityException;
    
    String verifyModuleName(final String p0) throws NullPointerException, IllegalArgumentException;
    
    String verifyFunctionName(final String p0) throws NullPointerException, IllegalArgumentException;
    
    Object handleString(final String p0, final String p1, final String p2);
}
