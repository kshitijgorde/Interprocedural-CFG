// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class DOCINFO
{
    public int cbSize;
    public int lpszDocName;
    public int lpszOutput;
    public int lpszDatatype;
    public int fwType;
    public static final int sizeof;
    
    static {
        sizeof = OS.DOCINFO_sizeof();
    }
}
