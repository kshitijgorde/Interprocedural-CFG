// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class EXCEPINFO
{
    public short wCode;
    public short wReserved;
    public int bstrSource;
    public int bstrDescription;
    public int bstrHelpFile;
    public int dwHelpContext;
    public int pvReserved;
    public int pfnDeferredFillIn;
    public int scode;
    public static final int sizeof;
    
    static {
        sizeof = COM.EXCEPINFO_sizeof();
    }
}
