// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMLVODSTATECHANGE extends NMHDR
{
    public int iFrom;
    public int iTo;
    public int uNewState;
    public int uOldState;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMLVODSTATECHANGE_sizeof();
    }
}
