// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMLINK extends NMHDR
{
    public int mask;
    public int iLink;
    public int state;
    public int stateMask;
    public char[] szID;
    public char[] szUrl;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMLINK_sizeof();
    }
    
    public NMLINK() {
        this.szID = new char[48];
        this.szUrl = new char[2084];
    }
}
