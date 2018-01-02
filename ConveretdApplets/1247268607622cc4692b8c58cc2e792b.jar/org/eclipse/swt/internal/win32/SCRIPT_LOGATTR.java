// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SCRIPT_LOGATTR
{
    public boolean fSoftBreak;
    public boolean fWhiteSpace;
    public boolean fCharStop;
    public boolean fWordStop;
    public boolean fInvalid;
    public byte fReserved;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_LOGATTR_sizeof();
    }
}
