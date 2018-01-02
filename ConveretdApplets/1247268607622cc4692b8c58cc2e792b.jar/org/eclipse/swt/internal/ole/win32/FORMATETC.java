// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class FORMATETC
{
    public int cfFormat;
    public int ptd;
    public int dwAspect;
    public int lindex;
    public int tymed;
    public static final int sizeof;
    
    static {
        sizeof = COM.FORMATETC_sizeof();
    }
}
