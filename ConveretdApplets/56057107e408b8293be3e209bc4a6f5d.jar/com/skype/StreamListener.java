// 
// Decompiled by Procyon v0.5.30
// 

package com.skype;

public interface StreamListener
{
    void textReceived(final String p0) throws SkypeException;
    
    void datagramReceived(final String p0) throws SkypeException;
}
