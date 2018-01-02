// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMLVFINDITEM extends NMHDR
{
    public int iStart;
    public int flags;
    public int psz;
    public int lParam;
    public int x;
    public int y;
    public int vkDirection;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMLVFINDITEM_sizeof();
    }
}
