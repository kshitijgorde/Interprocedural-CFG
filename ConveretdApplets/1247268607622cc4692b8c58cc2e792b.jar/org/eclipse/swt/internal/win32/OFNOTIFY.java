// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class OFNOTIFY extends NMHDR
{
    public int lpOFN;
    public int pszFile;
    public static int sizeof;
    
    static {
        OFNOTIFY.sizeof = OS.OFNOTIFY_sizeof();
    }
}
