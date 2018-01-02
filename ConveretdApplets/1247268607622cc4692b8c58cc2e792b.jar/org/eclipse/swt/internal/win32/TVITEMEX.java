// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TVITEMEX extends TVITEM
{
    public int iIntegral;
    public static final int sizeof;
    
    static {
        sizeof = OS.TVITEMEX_sizeof();
    }
}
