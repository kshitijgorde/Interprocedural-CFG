// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

public interface Group
{
    String getName();
    
    String getPassword();
    
    long getGID();
    
    String[] getMembers();
}
