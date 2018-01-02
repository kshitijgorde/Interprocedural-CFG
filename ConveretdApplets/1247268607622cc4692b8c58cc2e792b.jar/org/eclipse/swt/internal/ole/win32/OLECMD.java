// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class OLECMD
{
    public int cmdID;
    public int cmdf;
    public static final int sizeof;
    
    static {
        sizeof = COM.OLECMD_sizeof();
    }
}
