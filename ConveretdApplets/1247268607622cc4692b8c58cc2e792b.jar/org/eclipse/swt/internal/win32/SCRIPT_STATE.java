// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SCRIPT_STATE
{
    public short uBidiLevel;
    public boolean fOverrideDirection;
    public boolean fInhibitSymSwap;
    public boolean fCharShape;
    public boolean fDigitSubstitute;
    public boolean fInhibitLigate;
    public boolean fDisplayZWG;
    public boolean fArabicNumContext;
    public boolean fGcpClusters;
    public boolean fReserved;
    public short fEngineReserved;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_STATE_sizeof();
    }
}
