// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class BROWSEINFO
{
    public int hwndOwner;
    public int pidlRoot;
    public int pszDisplayName;
    public int lpszTitle;
    public int ulFlags;
    public int lpfn;
    public int lParam;
    public int iImage;
    public static final int sizeof;
    
    static {
        sizeof = OS.BROWSEINFO_sizeof();
    }
}
