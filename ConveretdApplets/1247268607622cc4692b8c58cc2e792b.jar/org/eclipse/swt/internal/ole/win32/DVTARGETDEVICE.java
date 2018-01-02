// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class DVTARGETDEVICE
{
    public int tdSize;
    public short tdDriverNameOffset;
    public short tdDeviceNameOffset;
    public short tdPortNameOffset;
    public short tdExtDevmodeOffset;
    public byte[] tdData;
    public static final int sizeof;
    
    static {
        sizeof = COM.DVTARGETDEVICE_sizeof();
    }
    
    public DVTARGETDEVICE() {
        this.tdData = new byte[1];
    }
}
