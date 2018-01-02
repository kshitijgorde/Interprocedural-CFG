// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal;

public class Platform
{
    public static final String PLATFORM = "win32";
    public static final Lock lock;
    
    static {
        lock = new Lock();
    }
}
