// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMTBHOTITEM extends NMHDR
{
    public int idOld;
    public int idNew;
    public int dwFlags;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMTBHOTITEM_sizeof();
    }
}
