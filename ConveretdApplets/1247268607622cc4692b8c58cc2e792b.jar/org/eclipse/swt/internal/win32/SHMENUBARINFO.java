// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SHMENUBARINFO
{
    public int cbSize;
    public int hwndParent;
    public int dwFlags;
    public int nToolBarId;
    public int hInstRes;
    public int nBmpId;
    public int cBmpImages;
    public int hwndMB;
    public static final int sizeof;
    
    static {
        sizeof = (OS.IsSP ? 36 : OS.SHMENUBARINFO_sizeof());
    }
}
