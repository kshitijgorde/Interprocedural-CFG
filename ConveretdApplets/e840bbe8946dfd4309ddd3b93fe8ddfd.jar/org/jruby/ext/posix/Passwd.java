// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

public interface Passwd
{
    String getLoginName();
    
    String getPassword();
    
    long getUID();
    
    long getGID();
    
    int getPasswdChangeTime();
    
    String getAccessClass();
    
    String getGECOS();
    
    String getHome();
    
    String getShell();
    
    int getExpire();
}
