// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class LICINFO
{
    public int cbLicInfo;
    public boolean fRuntimeKeyAvail;
    public boolean fLicVerified;
    public static final int sizeof;
    
    static {
        sizeof = COM.LICINFO_sizeof();
    }
}
