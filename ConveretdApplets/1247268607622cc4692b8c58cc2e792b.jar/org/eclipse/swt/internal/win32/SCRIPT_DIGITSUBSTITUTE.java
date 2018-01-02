// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SCRIPT_DIGITSUBSTITUTE
{
    public short NationalDigitLanguage;
    public short TraditionalDigitLanguage;
    public byte DigitSubstitute;
    public int dwReserved;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_DIGITSUBSTITUTE_sizeof();
    }
}
