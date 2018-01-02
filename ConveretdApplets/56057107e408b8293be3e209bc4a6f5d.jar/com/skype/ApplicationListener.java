// 
// Decompiled by Procyon v0.5.30
// 

package com.skype;

public interface ApplicationListener
{
    void connected(final Stream p0) throws SkypeException;
    
    void disconnected(final Stream p0) throws SkypeException;
}
