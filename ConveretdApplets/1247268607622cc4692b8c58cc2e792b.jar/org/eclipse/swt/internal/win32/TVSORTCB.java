// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TVSORTCB
{
    public int hParent;
    public int lpfnCompare;
    public int lParam;
    public static final int sizeof;
    
    static {
        sizeof = OS.TVSORTCB_sizeof();
    }
}
