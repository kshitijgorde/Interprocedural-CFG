// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class DWM_BLURBEHIND
{
    public int dwFlags;
    public boolean fEnable;
    public int hRgnBlur;
    public boolean fTransitionOnMaximized;
    public static final int sizeof;
    
    static {
        sizeof = OS.DWM_BLURBEHIND_sizeof();
    }
}
