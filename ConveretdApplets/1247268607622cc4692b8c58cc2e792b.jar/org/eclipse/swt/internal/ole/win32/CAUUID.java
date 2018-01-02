// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class CAUUID
{
    public int cElems;
    public int pElems;
    public static final int sizeof;
    
    static {
        sizeof = COM.CAUUID_sizeof();
    }
}
