// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class GESTURECONFIG
{
    public int dwID;
    public int dwWant;
    public int dwBlock;
    public static final int sizeof;
    
    static {
        sizeof = OS.GESTURECONFIG_sizeof();
    }
}
