// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class DISPPARAMS
{
    public int rgvarg;
    public int rgdispidNamedArgs;
    public int cArgs;
    public int cNamedArgs;
    public static final int sizeof;
    
    static {
        sizeof = COM.DISPPARAMS_sizeof();
    }
}
