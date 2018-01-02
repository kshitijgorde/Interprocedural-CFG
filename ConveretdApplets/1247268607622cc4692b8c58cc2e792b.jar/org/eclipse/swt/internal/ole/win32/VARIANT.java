// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class VARIANT
{
    public short vt;
    public short wReserved1;
    public short wReserved2;
    public short wReserved3;
    public int lVal;
    public static final int sizeof;
    
    static {
        sizeof = COM.VARIANT_sizeof();
    }
}
