// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SCRIPT_CONTROL
{
    public int uDefaultLanguage;
    public boolean fContextDigits;
    public boolean fInvertPreBoundDir;
    public boolean fInvertPostBoundDir;
    public boolean fLinkStringBefore;
    public boolean fLinkStringAfter;
    public boolean fNeutralOverride;
    public boolean fNumericOverride;
    public boolean fLegacyBidiClass;
    public int fReserved;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_CONTROL_sizeof();
    }
}
