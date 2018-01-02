// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class INPUT
{
    public int type;
    public static final int sizeof;
    
    static {
        sizeof = OS.INPUT_sizeof();
    }
}
