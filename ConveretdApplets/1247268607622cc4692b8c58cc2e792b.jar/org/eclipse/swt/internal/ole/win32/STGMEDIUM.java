// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class STGMEDIUM
{
    public int tymed;
    public int unionField;
    public int pUnkForRelease;
    public static final int sizeof;
    
    static {
        sizeof = COM.STGMEDIUM_sizeof();
    }
}
