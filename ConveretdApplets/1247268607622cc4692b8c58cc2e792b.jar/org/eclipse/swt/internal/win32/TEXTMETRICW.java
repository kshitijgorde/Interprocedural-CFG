// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TEXTMETRICW extends TEXTMETRIC
{
    public char tmFirstChar;
    public char tmLastChar;
    public char tmDefaultChar;
    public char tmBreakChar;
    public static final int sizeof;
    
    static {
        sizeof = OS.TEXTMETRICW_sizeof();
    }
}
